package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import perceptron.AbstractNeuralNetwork;
import perceptron.LogicGates;

public class TestLogicGates {

  AbstractNeuralNetwork nandNetwork;
  AbstractNeuralNetwork andNetwork;
  AbstractNeuralNetwork orNetwork;
  AbstractNeuralNetwork sumNetwork;

  @Before
  public void setUp() throws Exception {
    nandNetwork = LogicGates.NandGate();
    andNetwork = LogicGates.AndGate();
    orNetwork = LogicGates.OrGate();
    sumNetwork = LogicGates.SumGate();
  }

  @Test
  public void testNandGate() {
    List<Integer> input = new ArrayList<Integer>(2);
    input.add(0, 0);
    input.add(1, 0);
    nandNetwork.simulate(input);
    assertEquals(new Integer(1), nandNetwork.calculateResult().get(0));
    input.set(0, 0);
    input.set(1, 1);
    nandNetwork.simulate(input);
    assertEquals(new Integer(1), nandNetwork.calculateResult().get(0));
    input.set(0, 1);
    input.set(1, 0);
    nandNetwork.simulate(input);
    assertEquals(new Integer(1), nandNetwork.calculateResult().get(0));
    input.set(0, 1);
    input.set(1, 1);
    nandNetwork.simulate(input);
    assertEquals(new Integer(0), nandNetwork.calculateResult().get(0));
  }

  @Test
  public void testAndGate() {
    List<Integer> input = new ArrayList<Integer>(2);
    input.add(0, 0);
    input.add(1, 0);
    andNetwork.simulate(input);
    assertEquals(new Integer(0), andNetwork.calculateResult().get(0));
    input.set(0, 0);
    input.set(1, 1);
    andNetwork.simulate(input);
    assertEquals(new Integer(0), andNetwork.calculateResult().get(0));
    input.set(0, 1);
    input.set(1, 0);
    andNetwork.simulate(input);
    assertEquals(new Integer(0), andNetwork.calculateResult().get(0));
    input.set(0, 1);
    input.set(1, 1);
    andNetwork.simulate(input);
    assertEquals(new Integer(1), andNetwork.calculateResult().get(0));
  }

  @Test
  public void testOrGate() {
    List<Integer> input = new ArrayList<Integer>(2);
    input.add(0, 0);
    input.add(1, 0);
    orNetwork.simulate(input);
    assertEquals(new Integer(0), orNetwork.calculateResult().get(0));
    input.set(0, 0);
    input.set(1, 1);
    orNetwork.simulate(input);
    assertEquals(new Integer(1), orNetwork.calculateResult().get(0));
    input.set(0, 1);
    input.set(1, 0);
    orNetwork.simulate(input);
    assertEquals(new Integer(1), orNetwork.calculateResult().get(0));
    input.set(0, 1);
    input.set(1, 1);
    orNetwork.simulate(input);
    assertEquals(new Integer(1), orNetwork.calculateResult().get(0));
  }

  @Test
  public void testSumGate() {
    List<Integer> input = new ArrayList<Integer>(2);
    List<Integer> expected = new ArrayList<Integer>(2);
    input.add(0, 0);
    input.add(1, 0);
    expected.add(0, 0);
    expected.add(1, 0);
    sumNetwork.simulate(input);
    assertEquals(expected, sumNetwork.calculateResult());
    input.set(0, 1);
    input.set(1, 0);
    expected.set(0, 1);
    expected.set(1, 0);
    sumNetwork.simulate(input);
    assertEquals(expected, sumNetwork.calculateResult());
    input.set(0, 0);
    input.set(1, 1);
    expected.set(0, 1);
    expected.set(1, 0);
    sumNetwork.simulate(input);
    assertEquals(expected, sumNetwork.calculateResult());
    input.set(0, 1);
    input.set(1, 1);
    expected.set(0, 0);
    expected.set(1, 1);
    sumNetwork.simulate(input);
    assertEquals(expected, sumNetwork.calculateResult());
  }
}
