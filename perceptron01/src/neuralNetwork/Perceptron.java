package neuralNetwork;

public class Perceptron extends AbstractNeuron {


  public Perceptron(Double bias) {
    this.bias = bias;
  }

  @Override
  public void calculateResult() {
    output = bias + input > 0 ? 1.0 : 0.0;
  }

}
