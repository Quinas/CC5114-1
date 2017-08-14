package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import perceptron.SigmoidPositionSimulation;

public class TestSigmoidPositionSimulation {

  SigmoidPositionSimulation simulation;
  List<List<Double>> inputTest;
  Random random;

  @Before
  public void setUp() throws Exception {
    simulation = new SigmoidPositionSimulation();
    simulation.initNetwork();
    inputTest = new ArrayList<List<Double>>();
    random = new Random();
    for (int i = 0; i < 10000; ++i) {
      inputTest.add(getTest());
    }
  }

  public List<Double> getTest() {
    List<Double> test = new ArrayList<Double>();
    test.add(new Double(random.nextInt(1000000)));
    test.add(new Double(random.nextInt(1000000)));
    return test;
  }

  @Test
  public void test() {
    double accuracy = simulation.accuracy(inputTest);
    simulation.training(inputTest);
    assertTrue(accuracy < simulation.accuracy(inputTest) - 0.2);
  }
}
