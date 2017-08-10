package perceptron;

import java.util.List;

public abstract class AbstractSimulation {

  protected AbstractNeuralNetwork network;

  public abstract void initNetwork();

  public abstract void makeCorrection(List<Double> obtained, List<Double> expected,
      List<Double> input);

  public abstract List<Double> calculateExpected(List<Double> input);

  public List<Double> simulateCase(List<Double> input) {
    network.simulate(input);
    return network.calculateResult();
  }


  public void training(List<List<Double>> testList) {
    for (List<Double> test : testList) {
      makeCorrection(simulateCase(test), calculateExpected(test), test);
    }
  }


  public Double accuracy(List<List<Double>> testList) {
    Double output = 0.0;
    for (List<Double> test : testList) {
      if (simulateCase(test).equals(calculateExpected(test))) {
        output++;
      }
    }
    return output / testList.size();
  }
}
