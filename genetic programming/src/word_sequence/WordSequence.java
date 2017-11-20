package word_sequence;

import java.util.Random;

public class WordSequence {
  StringBuilder word;
  int size;

  public WordSequence(int size) {
    this.size = size;
    word = new StringBuilder(size);
    for (int i = 0; i < size; ++i) {
      word.insert(i, randomChar());
    }
  }

  public WordSequence(WordSequence word1, WordSequence word2) {
    this(word1.size());
    int mixPoint = new Random().nextInt(size());
    for (int i = 0; i < size(); ++i) {
      if (i <= mixPoint) {
        set(i, word1.get(i));
      } else {
        set(i, word2.get(i));
      }
    }
  }

  public int size() {
    return size;
  }

  public char get(int ind) {
    return word.charAt(ind);
  }

  public char randomChar() {
    return (char) (new Random().nextInt('z' - 'a' + 1) + 'a');
  }

  public void set(int ind, char c) {
    word.setCharAt(ind, c);
  }

  public void mutate(Double mutationRate) {
    for (int i = 0; i < size(); ++i) {
      Double roll = new Random().nextDouble();
      if (roll <= mutationRate) {
        set(i, randomChar());
      }
    }
  }

  public int fitness(WordSequence word2) {
    int ans = 0;
    for (int i = 0; i < size(); ++i) {
      ans += get(i) == word2.get(i) ? 1 : 0;
    }
    return ans;
  }
}
