package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import neuralNetwork.NeuralNetwork;

import org.junit.Before;
import org.junit.Test;

public class TestBackpropagationGates {

  NeuralNetwork network1;
  NeuralNetwork network2;
  List<List<Double>> examples;

  @Before
  public void setUp() throws Exception {
    List<Integer> layerSizes1 = new ArrayList<Integer>();
    List<Integer> layerSizes2 = new ArrayList<Integer>();
    examples = new ArrayList<List<Double>>();
    layerSizes1.add(0, 5);
    layerSizes1.add(1, 1);
    layerSizes2.add(0, 6);
    layerSizes2.add(1, 2);
    network1 = NeuralNetwork.completeNetwork(2, layerSizes1);
    network2 = NeuralNetwork.completeNetwork(2, layerSizes2);
    for (int i = 0; i <= 1; ++i) {
      for (int j = 0; j <= 1; ++j) {
        List<Double> example = new ArrayList<Double>(2);
        example.add(0, new Double(i));
        example.add(0, new Double(j));
        examples.add(example);
      }
    }
  }

  public boolean compareOutput(Double output1, Double output2) {
    int var1 = output1 > 0.5 ? 1 : 0;
    int var2 = output2 > 0.5 ? 1 : 0;
    return var1 == var2;
  }

  @Test
  public void testXorGate() {
    List<List<Double>> expected = new ArrayList<List<Double>>();
    for (List<Double> example : examples) {
      List<Double> exp = new ArrayList<Double>();
      exp.add(new Double((example.get(0).intValue() ^ example.get(1).intValue()) == 0 ? 1 : 0));
      expected.add(exp);
    }
    for (int i = 0; i < 50000; ++i) {
      network1.epoch(examples, expected);
    }
    Iterator<List<Double>> it = expected.iterator();
    for (List<Double> example : examples) {
      network1.simulate(example);
      assertTrue(compareOutput(it.next().get(0), network1.getResult().get(0)));
    }
  }

  @Test
  public void testSumGate() {
    List<List<Double>> expected = new ArrayList<List<Double>>();
    for (List<Double> example : examples) {
      List<Double> exp = new ArrayList<Double>();
      exp.add(new Double((example.get(0).intValue() ^ example.get(1).intValue()) == 0 ? 1 : 0));
      exp.add(example.get(0) == 1.0 && example.get(1) == 1.0 ? 1.0 : 0.0);
      expected.add(exp);
    }
    network2.analysis(500, examples, expected);


  }
}
