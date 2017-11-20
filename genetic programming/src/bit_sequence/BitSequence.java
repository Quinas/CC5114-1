package bit_sequence;

import java.util.BitSet;
import java.util.Random;

public class BitSequence {
  BitSet bits;
  int size;

  public BitSequence(int n) {
    bits = new BitSet();
    size = n;
    Random random = new Random();
    for (int i = 0; i < n; ++i) {
      bits.set(i, random.nextInt(2) == 1 ? true : false);
    }
  }

  public BitSequence(BitSequence bits1, BitSequence bits2) {
    this(bits1.size());
    int mixPoint = new Random().nextInt(size());
    for (int i = 0; i < size(); ++i) {
      if (i <= mixPoint) {
        bits.set(i, bits1.get(i));
      } else {
        bits.set(i, bits2.get(i));
      }
    }
  }

  public int size() {
    return size;
  }

  public boolean get(int ind) {
    return bits.get(ind);
  }

  public void mutate(Double mutationRate) {
    for (int i = 0; i < size(); ++i) {
      Double roll = new Random().nextDouble();
      if (roll <= mutationRate) {
        bits.set(i, !bits.get(i));
      }
    }
  }
}
