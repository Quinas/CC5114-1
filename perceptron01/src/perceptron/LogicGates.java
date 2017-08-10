package perceptron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class LogicGates {

  public static AbstractNeuralNetwork genericOneNeuronNetwork(List<Double> weights,
      AbstractNeuron neuron) {
    AbstractNeuralNetwork network = new AbstractNeuralNetwork() {

      @Override
      protected void setupInput(List<Edge> inputEdges, List<Double> dataList) {
        Iterator<Double> iterator = dataList.iterator();
        for (Edge edge : inputEdges) {
          edge.getToNeuron().addInput(edge.processEdge(iterator.next()));
        }
      }

      @Override
      protected void pushFirstLayer(Queue<AbstractNeuron> queue) {
        queue.add(network.get(0));
      }

      @Override
      public List<Double> calculateResult() {
        List<Double> output = new ArrayList<Double>();
        output.add(0, network.get(0).getResult());
        return output;
      }

    };
    network.addNeuron(0, neuron);
    for (Double weight : weights) {
      network.addInputEdge(0, weight);
    }
    return network;
  }


  public static AbstractNeuralNetwork NandGate() {
    List<Double> weights = new ArrayList<Double>();
    weights.add(-2.0);
    weights.add(-2.0);
    return genericOneNeuronNetwork(weights, new Perceptron(3.0));
  }


  public static AbstractNeuralNetwork AndGate() {
    List<Double> weights = new ArrayList<Double>();
    weights.add(1.0);
    weights.add(1.0);
    return genericOneNeuronNetwork(weights, new Perceptron(-1.0));
  }

  public static AbstractNeuralNetwork OrGate() {
    List<Double> weights = new ArrayList<Double>();
    weights.add(1.0);
    weights.add(1.0);
    return genericOneNeuronNetwork(weights, new Perceptron(0.0));
  }

  public static AbstractNeuralNetwork SumGate() {
    AbstractNeuralNetwork network = new AbstractNeuralNetwork() {

      @Override
      protected void setupInput(List<Edge> inputEdges, List<Double> dataList) {
        network.get(0).addInput(-2.0 * dataList.get(0));
        network.get(0).addInput(-2.0 * dataList.get(1));
        network.get(1).addInput(-2.0 * dataList.get(0));
        network.get(2).addInput(-2.0 * dataList.get(1));
      }

      @Override
      protected void pushFirstLayer(Queue<AbstractNeuron> queue) {
        queue.add(network.get(0));
      }

      @Override
      public List<Double> calculateResult() {
        List<Double> output = new ArrayList<Double>();
        output.add(0, network.get(4).getResult());
        output.add(1, network.get(3).getResult());
        return output;
      }

    };
    for (int i = 0; i < 5; ++i) {
      network.addNeuron(i, new Perceptron(3.0));
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
