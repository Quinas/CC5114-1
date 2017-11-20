package knightstour;

import bit_sequence.BitSequence;
import bit_sequence.BitSequenceFitness;

public class PureGeneticFitness implements BitSequenceFitness {

  int boardSize;
  int startX;
  int startY;
  int movX[];
  int movY[];
  boolean[][] board;

  public PureGeneticFitness(int boardSize, int startX, int startY) {
    this.boardSize = boardSize;
    this.startX = startX;
    this.startY = startY;
    board = new boolean[boardSize][boardSize];
    movX = new int[] {1, 2, 2, 1, -1, -2, -2, -1};
    movY = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
  }

  public int fitness(BitSequence sequence, BitSequence goal) {
    for (int i = 0; i < boardSize; ++i) {
      for (int j = 0; j < boardSize; ++j) {
        board[i][j] = false;
      }
    }
    int curX = startX, curY = startY;
    board[curX][curY] = true;
    int ans = 0;
    for (int i = 0; i < sequence.size(); i += 3, ans++) {
      int move = sequence.get(i) ? 4 : 0;
      move += sequence.get(i + 1) ? 2 : 0;
      move += sequence.get(i + 2) ? 1 : 0;
      curX += movX[move];
      curY += movY[move];
      if (curX < 0 || curX >= boardSize || curY < 0 || curY >= boardSize || board[curX][curY]) {
        break;
      }
      board[curX][curY] = true;
    }
    return ans;
  }

}
