package perceptron;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LogicGates {

  public static AbstractNeuralNetwork NandGate() {
    AbstractNeuralNetwork network = new AbstractNeuralNetwork(1) {

      @Override
      protected void setupInput(List<Integer> dataList) {
        network.get(0).addInputEdge(new Edge(null, network.get(0), -2 * dataList.get(0)));
        network.get(0).addInputEdge(new Edge(null, network.get(0), -2 * dataList.get(1)));
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
    network.addNeuron(0, new Perceptron(3));
    return network;
  }

  public static AbstractNeuralNetwork AndGate() {
    AbstractNeuralNetwork network = new AbstractNeuralNetwork(3) {

      @Override
      protected void setupInput(List<Integer> dataList) {
        network.get(0).addInputEdge(new Edge(null, network.get(0), 1 * dataList.get(0)));
        network.get(0).addInputEdge(new Edge(null, network.get(0), 1 * dataList.get(1)));
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
    network.addNeuron(0, new Perceptron(-1));
    return network;
  }

  public static AbstractNeuralNetwork OrGate() {
    AbstractNeuralNetwork network = new AbstractNeuralNetwork(3) {

      @Override
      protected void setupInput(List<Integer> dataList) {
        network.get(0).addInputEdge(new Edge(null, network.get(0), 1 * dataList.get(0)));
        network.get(0).addInputEdge(new Edge(null, network.get(0), 1 * dataList.get(1)));
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
    network.addNeuron(0, new Perceptron(0));
    return network;
  }

  public static AbstractNeuralNetwork SumGate() {
    AbstractNeuralNetwork network = new AbstractNeuralNetwork(5) {

      @Override
      protected void setupInput(List<Integer> dataList) {
        network.get(0).addInputEdge(new Edge(null, network.get(0), -2 * dataList.get(0)));
        network.get(0).addInputEdge(new Edge(null, network.get(0), -2 * dataList.get(1)));
        network.get(1).addInputEdge(new Edge(null, network.get(1), -2 * dataList.get(0)));
        network.get(2).addInputEdge(new Edge(null, network.get(2), -2 * dataList.get(1)));
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
    network.addEdge(0, 1, -2);
    network.addEdge(0, 2, -2);
    network.addEdge(0, 3, -2);
    network.addEdge(0, 3, -2);
    network.addEdge(1, 4, -2);
    network.addEdge(2, 4, -2);
    return network;
  }
}
