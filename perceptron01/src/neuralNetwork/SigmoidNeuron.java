package neuralNetwork;

public class SigmoidNeuron extends AbstractNeuron {

  public SigmoidNeuron(Double bias) {
    this.bias = bias;
  }


  @Override
  public void calculateResult() {
    output = 1.0 / (1.0 + Math.exp(-input - bias));
  }
}
