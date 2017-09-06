package neuralNetwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TicTacToe {

  public static List<List<Double>> dataset = new ArrayList<List<Double>>();
  public static List<List<Double>> expected = new ArrayList<List<Double>>();

  public static void main(String[] args) throws IOException {
    readDataSet();
    NeuralNetwork network = getNetwork();
    network.setLearningRate(1.0);
    long seed = System.nanoTime();
    Collections.shuffle(dataset, new Random(seed));
    Collections.shuffle(expected, new Random(seed));
    network.analysis(500, dataset, expected);
  }

  public static void readDataSet() throws IOException {
    BufferedReader reader = null;
    try {
      File file = new File("tic-tac-toe.data");
      reader = new BufferedReader(new FileReader(file));
      String line;
      while ((line = reader.readLine()) != null) {
        List<String> list = Arrays.asList(line.split(","));
        List<Double> example = new ArrayList<Double>();
        List<Double> result = new ArrayList<Double>();
        for (int i = 0; i < list.size(); ++i) {
          if (i < list.size() - 1) {
            String state = list.get(i);
            if (state.equals("x")) {
              example.add(1.0);
            } else if (state.equals("o")) {
              example.add(0.5);
            } else if (state.equals("b")) {
              example.add(0.0);
            }
          } else {
            String res = list.get(i);
            if (res.equals("positive")) {
              result.add(1.0);
            } else if (res.equals("negative")) {
              result.add(0.0);
            }
          }
        }
        dataset.add(example);
        expected.add(result);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        reader.close();
      }
    }
  }

  public static NeuralNetwork getNetwork() {
    List<Integer> layers = new ArrayList<Integer>();
    for (int i = 0; i < 1; ++i) {
      layers.add(15);
    }
    layers.add(1);
    NeuralNetwork network = NeuralNetwork.completeNetwork(9, layers);
    return network;
  }
}
