package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import perceptron.PositionSimulation;

public class TestPositionSimulation {

  PositionSimulation simulation;
  Random random;
  List<List<Double>> inputTest1;
  List<List<Double>> inputTest2;
  List<List<Double>> inputTest3;

  @Before
  public void setUp() throws Exception {
    simulation = new PositionSimulation();
    simulation.initNetwork();
    random = new Random();
    inputTest1 = new ArrayList<List<Double>>();
    inputTest2 = new ArrayList<List<Double>>();
    inputTest3 = new ArrayList<List<Double>>();
    for (int i = 0; i < 100000; ++i) {
      inputTest1.add(getTest());
      inputTest2.add(getTest());
      inputTest3.add(getTest());
    }
  }

  public List<Double> getTest() {
    List<Double> test = new ArrayList<Double>();
    test.add(new Double(random.nextInt(1000000)));
    test.add(new Double(random.nextInt(1000000)));
    return test;
  }

  @Test
  public void testTraining() {
    double accuracy1 = simulation.accuracy(inputTest1);
    double accuracy2 = simulation.accuracy(inputTest2);
    double accuracy3 = simulation.accuracy(inputTest3);
    simulation.training(inputTest1);
    assertTrue(accuracy1 < simulation.accuracy(inputTest1) - 0.4);
    assertTrue(accuracy2 < simulation.accuracy(inputTest2) - 0.4);
    assertTrue(accuracy3 < simulation.accuracy(inputTest3) - 0.4);
  }

}
