package word_sequence;


public class Simulation {

  public static final int populationSize = 1000;
  public static final int genesNumber = 50;
  public static final double mutationRate = 0.1;
  public static WordSequenceAlgorithm algorithm;
  public static WordSequence goal;

  public static void main(String args[]) {
    algorithm = new WordSequenceAlgorithm(populationSize, genesNumber, mutationRate);
    goal = new WordSequence(genesNumber);
    analysis();
  }

  public static boolean finished() {
    for (WordSequence seq : algorithm.getMembers()) {
      if (seq.fitness(goal) == genesNumber) {
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
      algorithm.reproduce(algorithm.select(goal));
    }
    System.out.println("Generations until solution:\t" + total);
    System.out.println("Execution Time:\t" + ((System.currentTimeMillis() - start) / 1000.0)
        + " s.");
  }
}
