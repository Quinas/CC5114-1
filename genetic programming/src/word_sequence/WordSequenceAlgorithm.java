package word_sequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class WordSequenceAlgorithm {
  List<WordSequence> members;
  int populationSize;
  int genesNumber;
  Double mutationRate;

  public WordSequenceAlgorithm(int populationSize, int genesNumber, Double mutationRate) {
    this.populationSize = populationSize;
    this.genesNumber = genesNumber;
    this.mutationRate = mutationRate;
    members = new ArrayList<WordSequence>(populationSize);
    for (int i = 0; i < populationSize; ++i) {
      members.add(i, new WordSequence(genesNumber));
    }
  }

  public int select(final WordSequence goal) {
    Collections.sort(members, new Comparator<WordSequence>() {
      public int compare(WordSequence arg0, WordSequence arg1) {
        int a = arg0.fitness(goal), b = arg1.fitness(goal);
        return a < b ? 1 : a == b ? 0 : -1;
      }
    });
    int total = 0;
    for (WordSequence seq : members) {
      total += seq.fitness(goal);
    }

    Double r = new Random().nextDouble();
    for (int i = 0; i < members.size(); ++i) {
      if (1.0 * members.get(i).fitness(goal) / total < r) {
        return i > 0 ? i : 1;
      }
    }
    return members.size();
  }

  public void reproduce(int matingPool) {
    List<WordSequence> children = new ArrayList<WordSequence>(members.size());
    Random random = new Random();
    for (int i = 0; i < members.size(); ++i) {
      int parent1 = random.nextInt(matingPool);
      int parent2 = random.nextInt(matingPool);
      children.add(i, new WordSequence(members.get(parent1), members.get(parent2)));
    }
    for (WordSequence seq : children) {
      seq.mutate(mutationRate);
    }
    members = children;
  }

  public List<WordSequence> getMembers() {
    return members;
  }
}
