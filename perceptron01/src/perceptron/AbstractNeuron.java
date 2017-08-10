package perceptron;


public abstract class AbstractNeuron {
  protected Double input;

  public AbstractNeuron() {
    input = 0.0;
  }

  public void clearInput() {
    input = 0.0;
  }

  public abstract Double getResult();

  public void addInput(Double input) {
    this.input += input;
  }

}
