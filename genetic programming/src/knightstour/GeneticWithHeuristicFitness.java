package knightstour;

import bit_sequence.BitSequence;
import bit_sequence.BitSequenceFitness;

public class GeneticWithHeuristicFitness implements BitSequenceFitness {

  int boardSize;
  int startX;
  int startY;
  int movX[];
  int movY[];
  boolean board[][];

  public GeneticWithHeuristicFitness(int boardSize, int startX, int startY) {
    this.boardSize = boardSize;
    this.startX = startX;
    this.startY = startY;
    board = new boolean[boardSize][boardSize];
    movX = new int[] {1, 2, 2, 1, -1, -2, -2, -1};
    movY = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
  }

  public boolean isLegitPosition(int x, int y) {
    if (x < 0 || x >= boardSize || y < 0 || y >= boardSize || board[x][y]) {
      return false;
    }
    return true;
  }

  public int positionValue(int x, int y) {
    int ans = 0;
    for (int i = 0; i < 8; ++i) {
      int dx = x + movX[i], dy = y + movY[i];
      ans += isLegitPosition(dx, dy) ? 1 : 0;
    }
    return ans;
  }

  public int heuristicMove(int x, int y) {
    int ans = -1, mn = -1;
    for (int i = 0; i < 8; ++i) {
      int dx = x + movX[i], dy = y + movY[i];
      if (isLegitPosition(dx, dy)) {
        if (mn == -1) {
          mn = positionValue(dx, dy);
          ans = i;
        }
        if (positionValue(dx, dy) < mn) {
          mn = positionValue(dx, dy);
          ans = i;
        }
      }
    }
    return ans;
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
      if (!isLegitPosition(curX + movX[move], curY + movY[move])) {
        move = heuristicMove(curX, curY);
        if (move == -1) {
          break;
        }
      }
      curX += movX[move];
      curY += movY[move];
      board[curX][curY] = true;
    }
    return ans;
  }

}
