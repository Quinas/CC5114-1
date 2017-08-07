package perceptron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public abstract class AbstractNeuralNetwork {
  protected List<AbstractNeuron> network;
  protected Map<AbstractNeuron, List<Edge>> adjacencyList;


  public AbstractNeuralNetwork(int size) {
    network = new ArrayList<AbstractNeuron>(size);
    adjacencyList = new HashMap<AbstractNeuron, List<Edge>>(size);
  }

  public void addNeuron(int index, AbstractNeuron neuron) {

    network.add(index, neuron);
    adjacencyList.put(neuron, new ArrayList<Edge>());
  }

  public void addEdge(int fromNeuron, int toNeuron, int weight) {
    adjacencyList.get(network.get(fromNeuron)).add(
        new Edge(network.get(fromNeuron), network.get(toNeuron), weight));
  }

  protected abstract void setupInput(List<Integer> dataList);

  protected abstract void pushFirstLayer(Queue<AbstractNeuron> queue);

  public void simulate(List<Integer> dataList) {
    Map<AbstractNeuron, Boolean> visited = new HashMap<AbstractNeuron, Boolean>();
    for (AbstractNeuron neuron : network) {
      neuron.clearInput();
      visited.put(neuron, false);
    }

    Queue<AbstractNeuron> queue = new LinkedList<AbstractNeuron>();
    setupInput(dataList);
    pushFirstLayer(queue);
    while (!queue.isEmpty()) {
      AbstractNeuron currentNeuron = queue.poll();
      if (visited.get(currentNeuron)) {
        continue;
      }
      visited.put(currentNeuron, true);
      Integer result = currentNeuron.getResult();
      for (Edge edge : adjacencyList.get(currentNeuron)) {
        queue.add(edge.toNeuron);
        edge.toNeuron
            .addInputEdge(new Edge(currentNeuron, edge.toNeuron, edge.processEdge(result)));
      }
    }
  }

  public abstract List<Integer> calculateResult();
}
