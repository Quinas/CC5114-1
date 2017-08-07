package perceptron;

public class Perceptron extends AbstractNeuron {

  Integer bias;

  public Perceptron(int bias) {
    super();
    this.bias = bias;
  }

  @Override
  public Edge mergeEdges(Edge edge1, Edge edge2) {
    return new Edge(null, this, edge1.weight + edge2.weight);
  }

  @Override
  public Integer processData(Edge edge) {
    return bias + edge.weight > 0 ? 1 : 0;
  }

}
