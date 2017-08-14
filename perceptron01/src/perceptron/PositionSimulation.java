package perceptron;

import java.util.ArrayList;
import java.util.List;


public class PositionSimulation extends AbstractSimulation {
  Double constant;


  @Override
  public void initNetwork() {
    initNetwork(0.01);
  }

  public void initNetwork(Double constant) {
    List<Double> weights = new ArrayList<Double>();
    weights.add(1.0);
    weights.add(1.0);
    network = LogicGates.genericOneNeuronNetwork(weights, new Perceptron(0.0));
    this.constant = constant;
  }

  @Override
  public void makeCorrection(List<Double> obtained, List<Double> expected, List<Double> input) {
    if (!valid(expected, obtained)) {
      Edge e1 = network.inputEdges.get(0);
      Edge e2 = network.inputEdges.get(1);
      if (expected.get(0) == 0.0) {
        e1.setWeight(e1.getWeight() - constant * input.get(0));
        e2.setWeight(e2.getWeight() - constant * input.get(1));
      } else if (expected.get(0) == 1.0) {
        e1.setWeight(e1.getWeight() + constant * input.get(0));
        e2.setWeight(e2.getWeight() + constant * input.get(1));
      }
    }
  }

  @Override
  public List<Double> calculateExpected(List<Double> input) {
    List<Double> expected = new ArrayList<Double>();
    Double x = input.get(0);
    Double y = input.get(1);
    expected.add(y > x ? 1.0 : 0.0); // Linear equation: y = x
    return expected;
  }

  @Override
  public boolean valid(List<Double> obtained, List<Double> expected) {
    return obtained.equals(expected);
  }

}
