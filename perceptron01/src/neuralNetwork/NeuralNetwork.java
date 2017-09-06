package neuralNetwork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NeuralNetwork {
  protected List<NeuronLayer> layers;
  public Double learningRate = 1.0;
  private int totalAttempts = 0;
  private int succesfulAttempts = 0;
  private Double totalError = 0.0;


  public NeuralNetwork(int layersSize) {
    layers = new ArrayList<NeuronLayer>(layersSize);
    for (int i = 0; i < layersSize; ++i) {
      layers.add(i, null);
    }
  }


  public static NeuralNetwork completeNetwork(int inputSize, List<Integer> layerSizes) {
    NeuralNetwork network = new NeuralNetwork(layerSizes.size());
    for (int ind = 0; ind < layerSizes.size(); ++ind) {
      int input = ind == 0 ? inputSize : layerSizes.get(ind - 1);
      int output = layerSizes.get(ind);
      network.setLayer(ind, NeuronLayer.sigmoidLayer(input, output));
    }
    return network;
  }

  public void setLearningRate(Double learningRate) {
    this.learningRate = learningRate;
  }

  public void setLayer(int ind, NeuronLayer layer) {
    layers.set(ind, layer);
  }

  public void simulate(List<Double> inputList) {
    layers.get(0).feedLayer(inputList);
    for (int i = 1; i < layers.size(); ++i) {
      layers.get(i).feedLayer(layers.get(i - 1).getOutput());
    }
  }

  public NeuronLayer getOutputLayer() {
    return layers.get(layers.size() - 1);
  }

  public List<Double> getResult() {
    return getOutputLayer().getOutput();
  }

  public void backpropagation(List<Double> expected) {
    getOutputLayer().backpropagateOutputLayer(expected);
    for (int i = layers.size() - 2; i >= 0; --i) {
      NeuronLayer layer = layers.get(i);
      layer.backpropagateHiddenLayer(layers.get(i + 1));
    }
    for (int i = 0; i < layers.size(); ++i) {
      layers.get(i).update(learningRate);
    }
  }

  public boolean valid(List<Double> obtained, List<Double> expected) {
    boolean ans = true;
    for (int i = 0; i < obtained.size(); ++i) {
      int var1 = obtained.get(i) > 0.5 ? 1 : 0;
      int var2 = expected.get(i) > 0.5 ? 1 : 0;
      ans = ans && var1 == var2;
    }
    return ans;
  }

  public void trainExample(List<Double> input, List<Double> expected) {
    simulate(input);
    backpropagation(expected);
    totalAttempts++;
    if (valid(getResult(), expected)) {
      succesfulAttempts++;
    }
    for (int i = 0; i < expected.size(); ++i) {
      totalError += Math.abs(expected.get(i) - getResult().get(i));
    }
  }

  public void epoch(List<List<Double>> examples, List<List<Double>> expected) {
    Iterator<List<Double>> it1 = examples.iterator();
    Iterator<List<Double>> it2 = expected.iterator();
    totalAttempts = 0;
    succesfulAttempts = 0;
    totalError = 0.0;
    while (it1.hasNext()) {
      trainExample(it1.next(), it2.next());
    }
  }

  public void analysis(int numberOfEpoch, List<List<Double>> examples, List<List<Double>> expected) {
    System.out
        .print("\nStarting training analysis of neural network\n\n-------------------------------------------\n\n");
    System.out.print("Total number of Epoch: " + numberOfEpoch + "\n\n");
    int sumTotalSuccess = 0;
    int sumTotalAttempts = 0;
    Double minimumError = 1e9;
    int atMinimum = 0;
    for (int i = 1; i <= numberOfEpoch; ++i) {
      epoch(examples, expected);
      System.out.println("Epoch number " + i + ": \tPrecision = " + succesfulAttempts + " / "
          + totalAttempts + "\tTotal error = " + totalError);
      sumTotalSuccess += succesfulAttempts;
      sumTotalAttempts += totalAttempts;
      if (totalError < minimumError) {
        minimumError = totalError;
        atMinimum = i;
      }
    }
    System.out.println("\nTotal Precision = " + sumTotalSuccess + " / " + sumTotalAttempts + " ("
        + 100.0 * sumTotalSuccess / sumTotalAttempts + "%)");
    System.out.println("\nMinimum Error at " + atMinimum + " Epoch: " + minimumError);
  }
}
