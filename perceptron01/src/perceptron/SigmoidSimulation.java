package perceptron;

import java.util.ArrayList;
import java.util.List;

public class SigmoidSimulation extends AbstractSimulation {

  @Override
  public void initNetwork() {
    initNetwork(0.0);
  }

  public void initNetwork(Double bias) {
    List<Double> weights = new ArrayList<Double>();
    weights.add(1.0);
    weights.add(1.0);
    network = LogicGates.genericOneNeuronNetwork(weights, new SigmoidNeuron(bias));
  }

  @Override
  public void makeCorrection(List<Double> obtained, List<Double> expected, List<Double> input) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Double> calculateExpected(List<Double> input) {
    // TODO Auto-generated method stub
    return null;
  }

}
