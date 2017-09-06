package neuralNetwork;


public class MirrorNeuron extends AbstractNeuron {

  @Override
  public void calculateResult() {
    output = input;
  }

}
