package perceptron;

public class Perceptron extends AbstractNeuron {

  Double bias;

  public Perceptron(Double bias) {
    super();
    this.bias = bias;
  }

  @Override
  public Double getResult() {
    return bias + input > 0 ? 1.0 : 0.0;
  }

}
