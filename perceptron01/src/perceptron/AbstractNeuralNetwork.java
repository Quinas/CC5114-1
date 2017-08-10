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
  protected List<Edge> inputEdges;


  public AbstractNeuralNetwork() {
    network = new ArrayList<AbstractNeuron>();
    adjacencyList = new HashMap<AbstractNeuron, List<Edge>>();
    inputEdges = new ArrayList<Edge>();
  }

  public void addNeuron(int index, AbstractNeuron neuron) {

    network.add(index, neuron);
    adjacencyList.put(neuron, new ArrayList<Edge>());
  }

  public void addEdge(int fromNeuron, int toNeuron, Double weight) {
    adjacencyList.get(network.get(fromNeuron)).add(
        new Edge(network.get(fromNeuron), network.get(toNeuron), weight));
  }

  public void addInputEdge(int toNeuron, Double weight) {
    inputEdges.add(new Edge(null, network.get(toNeuron), weight));
  }

  public void setNeuron(int index, AbstractNeuron neuron) {
    network.set(index, neuron);
    adjacencyList.put(neuron, new ArrayList<Edge>());
  }

  protected abstract void setupInput(List<Edge> inputEdges, List<Double> dataList);

  protected abstract void pushFirstLayer(Queue<AbstractNeuron> queue);

  public void simulate(List<Double> dataList) {
    Map<AbstractNeuron, Boolean> visited = new HashMap<AbstractNeuron, Boolean>();
    for (AbstractNeuron neuron : network) {
      neuron.clearInput();
      visited.put(neuron, false);
    }

    Queue<AbstractNeuron> queue = new LinkedList<AbstractNeuron>();
    setupInput(inputEdges, dataList);
    pushFirstLayer(queue);
    while (!queue.isEmpty()) {
      AbstractNeuron currentNeuron = queue.poll();
      if (visited.get(currentNeuron)) {
        continue;
      }
      visited.put(currentNeuron, true);
      Double result = currentNeuron.getResult();
      for (Edge edge : adjacencyList.get(currentNeuron)) {
        queue.add(edge.toNeuron);
        edge.toNeuron.addInput(edge.processEdge(result));
      }
    }
  }

  public abstract List<Double> calculateResult();

}
