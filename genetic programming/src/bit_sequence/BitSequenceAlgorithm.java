package bit_sequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BitSequenceAlgorithm {
  List<BitSequence> members;
  int populationSize;
  int genesNumber;
  Double mutationRate;

  public BitSequenceAlgorithm(int populationSize, int genesNumber, Double mutationRate) {
    this.populationSize = populationSize;
    this.genesNumber = genesNumber;
    this.mutationRate = mutationRate;
    members = new ArrayList<BitSequence>(populationSize);
    for (int i = 0; i < populationSize; ++i) {
      members.add(i, new BitSequence(genesNumber));
    }
  }

  public int select(final BitSequence goal, final BitSequenceFitness fit) {
    Collections.sort(members, new Comparator<BitSequence>() {
      public int compare(BitSequence arg0, BitSequence arg1) {
        int a = fit.fitness(arg0, goal), b = fit.fitness(arg1, goal);
        return a < b ? 1 : a == b ? 0 : -1;
      }
    });
    int total = 0;
    for (BitSequence seq : members) {
      total += fit.fitness(seq, goal);
    }

    Double r = new Random().nextDouble();
    for (int i = 0; i < members.size(); ++i) {
      if (1.0 * fit.fitness(members.get(i), goal) / total < r) {
        return i > 0 ? i : 1;
      }
    }
    return members.size();
  }

  public void reproduce(int matingPool) {
    List<BitSequence> children = new ArrayList<BitSequence>(members.size());
    Random random = new Random();
    for (int i = 0; i < members.size(); ++i) {
      int parent1 = random.nextInt(matingPool);
      int parent2 = random.nextInt(matingPool);
      children.add(i, new BitSequence(members.get(parent1), members.get(parent2)));
    }
    for (BitSequence seq : children) {
      seq.mutate(mutationRate);
    }
    members = children;
  }

  public int maxFitnessValue(BitSequence goal, BitSequenceFitness fit) {
    int ans = 0;
    for (BitSequence sequence : members) {
      ans = Math.max(ans, fit.fitness(sequence, goal));
    }
    return ans;
  }

  public List<BitSequence> getMembers() {
    return members;
  }
}
