package perceptron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PositionSimulation {
  AbstractNeuralNetwork perceptron;
  double constant;

  public PositionSimulation(double constant) {
    List<Double> weights = new ArrayList<Double>();
    weights.add(1.0);
    weights.add(1.0);
    perceptron = LogicGates.genericPerceptron(weights, 0);
    this.constant = constant;
  }

  public void training(List<Integer> list) {
    Iterator<Integer> iterator = list.iterator();
    while (iterator.hasNext()) {
      Integer x = iterator.next(), y = iterator.next();
      Integer obtained = simulatePoint(x, y);
      Integer expected = Math.abs(y) > Math.abs(x) ? 1 : 0; // Linear equation: y = x
      Edge e1 = perceptron.inputEdges.get(0);
      Edge e2 = perceptron.inputEdges.get(1);
      if (expected == 1 && obtained == 0) {
        e1.setWeight(e1.getWeight() + constant * x);
        e2.setWeight(e2.getWeight() + constant * y);
      } else if (expected == 0 && obtained == 1) {
        e1.setWeight(e1.getWeight() - constant * x);
        e2.setWeight(e2.getWeight() - constant * y);
      }
    }
  }

  public double accuracy(List<Integer> list) {
    double output = 0;
    Iterator<Integer> iterator = list.iterator();
    while (iterator.hasNext()) {
      Integer x = iterator.next(), y = iterator.next();
      Integer obtained = simulatePoint(x, y);
      Integer expected = Math.abs(y) > Math.abs(x) ? 1 : 0;
      if (obtained == expected) {
        output++;
      }
    }
    return 2 * output / list.size();
  }

  public Integer simulatePoint(Integer x, Integer y) {
    List<Integer> input = new ArrayList<Integer>();
    input.add(x);
    input.add(y);
    perceptron.simulate(input);
    return perceptron.calculateResult().get(0);
  }
}
