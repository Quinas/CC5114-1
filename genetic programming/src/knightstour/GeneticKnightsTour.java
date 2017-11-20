package knightstour;

import java.util.Random;

import bit_sequence.BitSequenceAlgorithm;
import bit_sequence.BitSequenceFitness;

public class GeneticKnightsTour {

  public static final int populationSize = 1000;
  public double mutationRate;
  public int genesNumber;
  int boardSize;
  Random random;
  BitSequenceAlgorithm algorithm;

  public GeneticKnightsTour(int boardSize, double mutationRate) {
    this.boardSize = boardSize;
    this.mutationRate = mutationRate;
    this.genesNumber = 3 * (boardSize * boardSize - 1);
    random = new Random();
    algorithm = new BitSequenceAlgorithm(populationSize, genesNumber, mutationRate);
  }

  public int findTour(int iterations) {
    int found = 0;
    int x = random.nextInt(boardSize);
    int y = random.nextInt(boardSize);
    BitSequenceFitness fitness = new PureGeneticFitness(boardSize, x, y);
    int maxFitness = 0;
    for (int i = 0; i < iterations; ++i) {
      algorithm.reproduce(algorithm.select(null, fitness));
      maxFitness = Math.max(maxFitness, algorithm.maxFitnessValue(null, fitness));
      if (algorithm.maxFitnessValue(null, fitness) == boardSize * boardSize - 1) {
        found++;
      }
    }
    return found;
  }
}
