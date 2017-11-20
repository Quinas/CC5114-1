package bit_sequence;

public class Simulation {

  public static final int populationSize = 1000;
  public static final int genesNumber = 60;
  public static final double mutationRate = 0.1;
  public static BitSequenceAlgorithm algorithm;
  public static BitSequence goal;

  public static void main(String args[]) {
    algorithm = new BitSequenceAlgorithm(populationSize, genesNumber, mutationRate);
    goal = new BitSequence(genesNumber);
    analysis();
  }

  public static boolean finished() {
    BitSequenceFitness fit = new CompareSequenceFitness();
    for (BitSequence seq : algorithm.getMembers()) {
      if (fit.fitness(seq, goal) == genesNumber) {
        return true;
      }
    }
    return false;
  }

  public static void analysis() {
    int total = 0;
    long start = System.currentTimeMillis();
    while (!finished()) {
      total++;
      algorithm.reproduce(algorithm.select(goal, new CompareSequenceFitness()));
    }
    System.out.println("Generations until solution:\t" + total);
    System.out.println("Execution Time:\t" + ((System.currentTimeMillis() - start) / 1000.0)
        + " s.");
  }
}
