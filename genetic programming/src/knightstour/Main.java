package knightstour;

public class Main {

  public static int maxIterations = 50;
  public static int executionTime = 1;
  public static int boardSize = 5;
  public static int numberOfToursToFind = 50;
  public static double mutationRate = 0.01;

  public static void main(String[] args) {
    analysis();
  }

  public static void analysis() {
    DfsKnightsTour dfsTour = new DfsKnightsTour(boardSize);
    GeneticKnightsTour geneticTour = new GeneticKnightsTour(boardSize, mutationRate);
    GeneticWithHeuristicKnightsTour genHeuristicTour =
        new GeneticWithHeuristicKnightsTour(boardSize, mutationRate);
    System.out.print("\nAnalysis for solving Knight's Tour Problem with DFS algorithm.\n\n");
    System.out.print("Board Size: " + boardSize + " x " + boardSize + "\n");
    System.out.print("Number of Knight's Tours to find: " + numberOfToursToFind
        + "\n\nExecuting...\n\n");
    long start = System.currentTimeMillis();
    dfsTour.findTour(numberOfToursToFind);
    System.out.println("Execution Time:\t" + ((System.currentTimeMillis() - start) / 1000.0)
        + " s.\n----------------------------\n");
    System.out
        .println("Analysis for solving Knight's Tour Problem with Pure Genetic Algorithm\n\n");
    System.out.print("Board Size: " + boardSize + " x " + boardSize + "\n");
    System.out.print("Max reproduction iterations per search: " + maxIterations + "\n");
    System.out.print("Mutation Rate: " + mutationRate + "\n");
    System.out.print("Execution Time: " + executionTime + " s\n\nExecuting...\n\n");
    start = System.currentTimeMillis();
    int count = 0;
    while ((System.currentTimeMillis() - start) / 1000.0 < executionTime) {
      count += geneticTour.findTour(maxIterations);
    }
    System.out.println("Number of Knight's Tours found: " + count
        + "\n----------------------------\n");
    System.out
        .println("Analysis for solving Knight's Tour Problem with Genetic Algorithm + Warnsdroff's Heuristic\n\n");
    System.out.print("Board Size: " + boardSize + " x " + boardSize + "\n");
    System.out.print("Max reproduction iterations per search: " + maxIterations + "\n");
    System.out.print("Mutation Rate: " + mutationRate + "\n");
    System.out.print("Execution Time: " + executionTime + " s\n\nExecuting...\n\n");
    start = System.currentTimeMillis();
    count = 0;
    while ((System.currentTimeMillis() - start) / 1000.0 < executionTime) {
      count += genHeuristicTour.findTour(maxIterations);
    }
    System.out.println("Number of Knight's Tours found: " + count
        + "\n----------------------------\n");
  }
}
