package neuralNetwork;

public class Edge {
  Integer fromNeuron;
  Integer toNeuron;
  Double weight;
  Double input;

  public Edge(int fromNeuron, int toNeuron, Double weight) {
    this.fromNeuron = fromNeuron;
    this.toNeuron = toNeuron;
    this.weight = weight;
  }

  public Integer getFromNeuron() {
    return fromNeuron;
  }

  public void setFromNeuron(Integer fromNeuron) {
    this.fromNeuron = fromNeuron;
  }

  public Integer getToNeuron() {
    return toNeuron;
  }

  public void setToNeuron(Integer toNeuron) {
    this.toNeuron = toNeuron;
  }

  public Double getWeight() {
    return weight;
  }

  public Double getInput() {
    return input;
  }

  public void setInput(Double input) {
    this.input = input;
  }

  public void setWeight(double d) {
    this.weight = d;
  }

  public Double processEdge() {
    return input * weight;
  }

  @Override
  public String toString() {
    return "(" + fromNeuron + ", " + toNeuron + ", " + weight + ")";
  }
}
