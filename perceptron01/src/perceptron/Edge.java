package perceptron;

public class Edge {
  AbstractNeuron fromNeuron;
  AbstractNeuron toNeuron;
  Integer weight;

  public Edge(AbstractNeuron fromNeuron, AbstractNeuron toNeuron, Integer weight) {
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

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public Integer processEdge(Integer input) {
    return input * weight;
  }
}
