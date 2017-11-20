package bit_sequence;

public class CompareSequenceFitness implements BitSequenceFitness {


  public int fitness(BitSequence sequence, BitSequence goal) {
    int ans = 0;
    for (int i = 0; i < sequence.size(); ++i) {
      ans += sequence.get(i) == goal.get(i) ? 1 : 0;
    }
    return ans;
  }
}
