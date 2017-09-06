package neuralNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeuronLayer {

  List<AbstractNeuron> neurons;
  List<Double> input;
  List<Edge> adjacencyList;

  public NeuronLayer(int inputSize, int outputSize) {
    neurons = new ArrayList<AbstractNeuron>(outputSize);
    input = new ArrayList<Double>(outputSize);
    adjacencyList = new ArrayList<Edge>();
    for (int i = 0; i < outputSize; ++i) {
      input.add(i, null);
    }
    for (int i = 0; i < outputSize; ++i) {
      neurons.add(i, null);
    }
  }

  public void setNeuron(int position, AbstractNeuron neuron) {
    neurons.set(position, neuron);
  }

  public AbstractNeuron getNeuron(int position) {
    return neurons.get(position);
  }

  public void addEdge(int fromNeuron, int toNeuron, Double weight) {
    adjacencyList.add(new Edge(fromNeuron, toNeuron, weight));
  }


  public List<Edge> getAdjacencyList() {
    return adjacencyList;
  }

  public Edge getEdge(int fromNeuron, int toNeuron) {
    for (Edge e : adjacencyList) {
      if (e.fromNeuron == fromNeuron && e.toNeuron == toNeuron) {
        return e;
      }
    }
    return null;
  }

  public List<Double> getInput() {
    return input;
  }

  public List<Double> getOutput() {
    List<Double> ans = new ArrayList<Double>();
    for (AbstractNeuron neuron : neurons) {
      ans.add(neuron.getOutput());
    }
    return ans;
  }

  public void feedLayer(List<Double> data) {
    int ind = 0;
    for (int i = 0; i < input.size(); i++) {
      input.set(i, 0.0);
    }
    for (Edge edge : adjacencyList) {
      edge.setInput(data.get(edge.getFromNeuron()) > 0.5 ? 1.0 : 0.0);
      input.set(edge.getToNeuron(), input.get(edge.getToNeuron()) + edge.processEdge());
    }
    for (Double d : input) {
      AbstractNeuron neuron = neurons.get(ind);
      neuron.setInput(d);
      neuron.calculateResult();
      ind++;
    }
  }

  public Double transferDerivative(Double output) {
    return output * (1 - output);
  }

  public void backpropagateOutputLayer(List<Double> expected) {
    int ind = 0;
    for (Double exp : expected) {
      AbstractNeuron neuron = neurons.get(ind);
      Double error = exp - neuron.getOutput();
      Double delta = error * transferDerivative(neuron.getOutput());
      neuron.setDelta(delta);
      ind++;
    }
  }

  public void backpropagateHiddenLayer(NeuronLayer nextLayer) {
    for (int i = 0; i < neurons.size(); ++i) {
      calculateHiddenDelta(i, nextLayer);
    }
  }

  public void calculateHiddenDelta(int neuronIndex, NeuronLayer nextLayer) {
    Double error = 0.0;
    AbstractNeuron neuron = neurons.get(neuronIndex);
    for (Edge edge : nextLayer.getAdjacencyList()) {
      if (edge.getFromNeuron() == neuronIndex) {
        error += edge.getWeight() * nextLayer.getNeuron(edge.getToNeuron()).getDelta();
      }
    }
    Double delta = error * transferDerivative(neuron.getOutput());
    neuron.setDelta(delta);
  }

  public void update(Double learningRate) {
    for (Edge edge : adjacencyList) {
      Double weight = edge.getWeight();
      Double delta = neurons.get(edge.getToNeuron()).getDelta();
      Double input = edge.getInput();
      edge.setWeight(weight + (learningRate * delta * input));
    }
    for (AbstractNeuron neuron : neurons) {
      Double bias = neuron.getBias();
      Double delta = neuron.getDelta();
      neuron.setBias(bias + (learningRate * delta));
    }
  }

  public static NeuronLayer sigmoidLayer(int input, int output) {
    NeuronLayer layer = new NeuronLayer(input, output);
    Random rand = new Random();
    for (int i = 0; i < input; ++i) {
      for (int j = 0; j < output; ++j) {
        layer.addEdge(i, j, rand.nextDouble());
      }
    }
    for (int i = 0; i < output; ++i) {
      layer.setNeuron(i, new SigmoidNeuron(rand.nextDouble()));
    }
    return layer;
  }
}
