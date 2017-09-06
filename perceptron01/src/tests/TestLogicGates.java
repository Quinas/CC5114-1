package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import neuralNetwork.LogicGates;
import neuralNetwork.NeuralNetwork;

import org.junit.Before;
import org.junit.Test;


public class TestLogicGates {

  NeuralNetwork nandNetwork;
  NeuralNetwork andNetwork;
  NeuralNetwork orNetwork;
  NeuralNetwork sumNetwork;

  @Before
  public void setUp() throws Exception {
    nandNetwork = LogicGates.NandGate();
    andNetwork = LogicGates.AndGate();
    orNetwork = LogicGates.OrGate();
    sumNetwork = LogicGates.SumGate();
  }

  @Test
  public void testNandGate() {
    List<Double> input = new ArrayList<Double>(2);
    input.add(0, 0.0);
    input.add(1, 0.0);
    nandNetwork.simulate(input);
    assertEquals(new Double(1), nandNetwork.getResult().get(0));
    input.set(0, 0.0);
    input.set(1, 1.0);
    nandNetwork.simulate(input);
    assertEquals(new Double(1), nandNetwork.getResult().get(0));
    input.set(0, 1.0);
    input.set(1, 0.0);
    nandNetwork.simulate(input);
    assertEquals(new Double(1), nandNetwork.getResult().get(0));
    input.set(0, 1.0);
    input.set(1, 1.0);
    nandNetwork.simulate(input);
    assertEquals(new Double(0), nandNetwork.getResult().get(0));
  }

  @Test
  public void testAndGate() {
    List<Double> input = new ArrayList<Double>(2);
    input.add(0, 0.0);
    input.add(1, 0.0);
    andNetwork.simulate(input);
    assertEquals(new Double(0), andNetwork.getResult().get(0));
    input.set(0, 0.0);
    input.set(1, 1.0);
    andNetwork.simulate(input);
    assertEquals(new Double(0), andNetwork.getResult().get(0));
    input.set(0, 1.0);
    input.set(1, 0.0);
    andNetwork.simulate(input);
    assertEquals(new Double(0), andNetwork.getResult().get(0));
    input.set(0, 1.0);
    input.set(1, 1.0);
    andNetwork.simulate(input);
    assertEquals(new Double(1), andNetwork.getResult().get(0));
  }

  @Test
  public void testOrGate() {
    List<Double> input = new ArrayList<Double>(2);
    input.add(0, 0.0);
    input.add(1, 0.0);
    orNetwork.simulate(input);
    assertEquals(new Double(0), orNetwork.getResult().get(0));
    input.set(0, 0.0);
    input.set(1, 1.0);
    orNetwork.simulate(input);
    assertEquals(new Double(1), orNetwork.getResult().get(0));
    input.set(0, 1.0);
    input.set(1, 0.0);
    orNetwork.simulate(input);
    assertEquals(new Double(1), orNetwork.getResult().get(0));
    input.set(0, 1.0);
    input.set(1, 1.0);
    orNetwork.simulate(input);
    assertEquals(new Double(1), orNetwork.getResult().get(0));
  }

  @Test
  public void testSumGate() {
    List<Double> input = new ArrayList<Double>(2);
    List<Double> expected = new ArrayList<Double>(2);
    input.add(0, 0.0);
    input.add(1, 0.0);
    expected.add(0, 0.0);
    expected.add(1, 0.0);
    sumNetwork.simulate(input);
    assertEquals(expected, sumNetwork.getResult());
    input.set(0, 1.0);
    input.set(1, 0.0);
    expected.set(0, 1.0);
    expected.set(1, 0.0);
    sumNetwork.simulate(input);
    assertEquals(expected, sumNetwork.getResult());
    input.set(0, 0.0);
    input.set(1, 1.0);
    expected.set(0, 1.0);
    expected.set(1, 0.0);
    sumNetwork.simulate(input);
    assertEquals(expected, sumNetwork.getResult());
    input.set(0, 1.0);
    input.set(1, 1.0);
    expected.set(0, 0.0);
    expected.set(1, 1.0);
    sumNetwork.simulate(input);
    assertEquals(expected, sumNetwork.getResult());
  }
}
