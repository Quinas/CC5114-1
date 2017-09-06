package neuralNetwork;


public abstract class AbstractNeuron {
  protected Double input;
  protected Double output;
  protected Double bias;
  protected Double delta;

  public AbstractNeuron() {
    input = 0.0;
  }

  public void clearInput() {
    input = 0.0;
  }

  public abstract void calculateResult();

  public Double getOutput() {
    return output;
  }

  public Double getInput() {
    return input;
  }

  public void setInput(Double input) {
    this.input = input;
  }

  public Double getBias() {
    return bias;
  }

  public void setBias(Double bias) {
    this.bias = bias;
  }

  public Double getDelta() {
    return delta;
  }

  public void setDelta(Double delta) {
    this.delta = delta;
  }

}
