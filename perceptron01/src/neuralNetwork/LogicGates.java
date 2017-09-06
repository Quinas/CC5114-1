package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class LogicGates {

  public static NeuralNetwork genericOneNeuronNetwork(List<Double> weights, AbstractNeuron neuron) {
    NeuralNetwork network = new NeuralNetwork(1);
    NeuronLayer layer = new NeuronLayer(weights.size(), 1);
    layer.setNeuron(0, neuron);
    int ind = 0;
    for (Double weight : weights) {
      layer.addEdge(ind, 0, weight);
      ind++;
    }
    network.setLayer(0, layer);
    return network;
  }

  public static NeuralNetwork NandGate() {
    List<Double> weights = new ArrayList<Double>();
    weights.add(-2.0);
    weights.add(-2.0);
    return genericOneNeuronNetwork(weights, new Perceptron(3.0));
  }


  public static NeuralNetwork AndGate() {
    List<Double> weights = new ArrayList<Double>();
    weights.add(1.0);
    weights.add(1.0);
    return genericOneNeuronNetwork(weights, new Perceptron(-1.0));
  }

  public static NeuralNetwork OrGate() {
    List<Double> weights = new ArrayList<Double>();
    weights.add(1.0);
    weights.add(1.0);
    return genericOneNeuronNetwork(weights, new Perceptron(0.0));
  }

  public static NeuralNetwork SumGate() {
    NeuralNetwork network = new NeuralNetwork(3);
    NeuronLayer layer1 = new NeuronLayer(2, 3);
    NeuronLayer layer2 = new NeuronLayer(3, 3);
    NeuronLayer layer3 = new NeuronLayer(3, 2);
    layer1.setNeuron(0, new MirrorNeuron());
    layer1.setNeuron(1, new Perceptron(3.0));
    layer1.setNeuron(2, new MirrorNeuron());
    layer2.setNeuron(0, new Perceptron(3.0));
    layer2.setNeuron(1, new Perceptron(3.0));
    layer2.setNeuron(2, new Perceptron(3.0));
    layer3.setNeuron(0, new Perceptron(3.0));
    layer3.setNeuron(1, new MirrorNeuron());
    layer1.addEdge(0, 0, 1.0);
    layer1.addEdge(0, 1, -2.0);
    layer1.addEdge(1, 1, -2.0);
    layer1.addEdge(1, 2, 1.0);
    layer2.addEdge(0, 0, -2.0);
    layer2.addEdge(1, 0, -2.0);
    layer2.addEdge(1, 1, -2.0);
    layer2.addEdge(2, 1, -2.0);
    layer2.addEdge(1, 2, -2.0);
    layer2.addEdge(1, 2, -2.0);
    layer3.addEdge(0, 0, -2.0);
    layer3.addEdge(1, 0, -2.0);
    layer3.addEdge(2, 1, 1.0);
    network.setLayer(0, layer1);
    network.setLayer(1, layer2);
    network.setLayer(2, layer3);
    return network;
  }
}
