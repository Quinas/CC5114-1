package perceptron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class LogicGates {

  public static AbstractNeuralNetwork genericPerceptron(List<Double> weights, int bias) {
    AbstractNeuralNetwork network = new AbstractNeuralNetwork() {

      @Override
      protected void setupInput(List<Edge> inputEdges, List<Integer> dataList) {
        Iterator<Integer> iterator = dataList.iterator();
        for (Edge edge : inputEdges) {
          edge.getToNeuron().addInputEdge(
              new Edge(null, edge.getToNeuron(), edge.processEdge(iterator.next())));
        }
      }

      @Override
      protected void pushFirstLayer(Queue<AbstractNeuron> queue) {
        queue.add(network.get(0));
      }

      @Override
      public List<Integer> calculateResult() {
        List<Integer> output = new ArrayList<Integer>();
        output.add(0, network.get(0).getResult());
        return output;
      }

    };
    network.addNeuron(0, new Perceptron(bias));
    for (Double weight : weights) {
      network.addInputEdge(0, weight);
    }
    return network;
  }


  public static AbstractNeuralNetwork NandGate() {
    List<Double> weights = new ArrayList<Double>();
    weights.add(-2.0);
    weights.add(-2.0);
    return genericPerceptron(weights, 3);
  }


  public static AbstractNeuralNetwork AndGate() {
    List<Double> weights = new ArrayList<Double>();
    weights.add(1.0);
    weights.add(1.0);
    return genericPerceptron(weights, -1);
  }

  public static AbstractNeuralNetwork OrGate() {
    List<Double> weights = new ArrayList<Double>();
    weights.add(1.0);
    weights.add(1.0);
    return genericPerceptron(weights, 0);
  }

  public static AbstractNeuralNetwork SumGate() {
    AbstractNeuralNetwork network = new AbstractNeuralNetwork() {

      @Override
      protected void setupInput(List<Edge> inputEdges, List<Integer> dataList) {
        network.get(0).addInputEdge(new Edge(null, network.get(0), -2.0 * dataList.get(0)));
        network.get(0).addInputEdge(new Edge(null, network.get(0), -2.0 * dataList.get(1)));
        network.get(1).addInputEdge(new Edge(null, network.get(1), -2.0 * dataList.get(0)));
        network.get(2).addInputEdge(new Edge(null, network.get(2), -2.0 * dataList.get(1)));
      }

      @Override
      protected void pushFirstLayer(Queue<AbstractNeuron> queue) {
        queue.add(network.get(0));
      }

      @Override
      public List<Integer> calculateResult() {
        List<Integer> output = new ArrayList<Integer>();
        output.add(0, network.get(4).getResult());
        output.add(1, network.get(3).getResult());
        return output;
      }

    };
    for (int i = 0; i < 5; ++i) {
      network.addNeuron(i, new Perceptron(3));
    }
    network.addEdge(0, 1, -2.0);
    network.addEdge(0, 2, -2.0);
    network.addEdge(0, 3, -2.0);
    network.addEdge(0, 3, -2.0);
    network.addEdge(1, 4, -2.0);
    network.addEdge(2, 4, -2.0);
    return network;
  }
}
