package perceptron;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNeuron {
  private final List<Edge> input;

  public AbstractNeuron() {
    input = new ArrayList<Edge>();
  }

  public void clearInput() {
    input.clear();
  }

  public Integer getResult() {
    return processData(mergeMultiple(new ArrayList<Edge>(input)));
  }

  public void addInputEdge(Edge edge) {
    input.add(edge);
  }

  public abstract Edge mergeEdges(Edge edge1, Edge edge2);

  public abstract Integer processData(Edge edge);

  public Edge mergeMultiple(List<Edge> list) {
    if (list.size() == 1) {
      return list.get(0);
    } else if (list.size() > 1) {
      Edge merged = mergeEdges(list.get(0), list.get(1));
      list.remove(1);
      list.remove(0);
      list.add(merged);
      return mergeMultiple(list);
    }
    return null;
  }
}
