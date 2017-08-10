package perceptron;

public class Edge {
  AbstractNeuron fromNeuron;
  AbstractNeuron toNeuron;
  Double weight;

  public Edge(AbstractNeuron fromNeuron, AbstractNeuron toNeuron, Double weight) {
    this.fromNeuron = fromNeuron;
    this.toNeuron = toNeuron;
    this.weight = weight;
  }

  public AbstractNeuron getFromNeuron() {
    return fromNeuron;
  }

  public void setFromNeuron(AbstractNeuron fromNeuron) {
    this.fromNeuron = fromNeuron;
  }

  public AbstractNeuron getToNeuron() {
    return toNeuron;
  }

  public void setToNeuron(AbstractNeuron toNeuron) {
    this.toNeuron = toNeuron;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(double d) {
    this.weight = d;
  }

  public Double processEdge(Integer input) {
    return input * weight;
  }
}
