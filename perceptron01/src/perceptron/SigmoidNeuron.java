package perceptron;

public class SigmoidNeuron extends AbstractNeuron {

  Double bias;

  public SigmoidNeuron(Double bias) {
    this.bias = bias;
  }


  @Override
  public Double getResult() {
    return 1.0 / (1.0 + Math.exp(-input - bias));
  }

}
