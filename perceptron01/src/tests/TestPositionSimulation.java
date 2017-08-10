package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import perceptron.PositionSimulation;

public class TestPositionSimulation {

  PositionSimulation perceptron;
  List<Integer> inputTest1;
  List<Integer> inputTest2;
  List<Integer> inputTest3;

  @Before
  public void setUp() throws Exception {
    perceptron = new PositionSimulation(0.01);
    inputTest1 = new ArrayList<Integer>();
    inputTest2 = new ArrayList<Integer>();
    inputTest3 = new ArrayList<Integer>();
    Random random = new Random();
    for (int i = 0; i < 100000; ++i) {
      inputTest1.add(random.nextInt(1000000));
      inputTest2.add(random.nextInt(1000000));
      inputTest3.add(random.nextInt(1000000));
    }
  }

  @Test
  public void testTraining() {
    double accuracy1 = perceptron.accuracy(inputTest1);
    double accuracy2 = perceptron.accuracy(inputTest2);
    double accuracy3 = perceptron.accuracy(inputTest3);
    perceptron.training(inputTest1);
    assertTrue(accuracy1 < perceptron.accuracy(inputTest1) - 0.4);
    assertTrue(accuracy2 < perceptron.accuracy(inputTest2) - 0.4);
    assertTrue(accuracy3 < perceptron.accuracy(inputTest3) - 0.4);
  }

}
