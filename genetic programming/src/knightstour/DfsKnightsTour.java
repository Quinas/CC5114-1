package knightstour;

import java.util.Random;

public class DfsKnightsTour {
  Random random;
  boolean board[][];
  int movX[];
  int movY[];
  int boardSize;
  int target;
  int found;

  public DfsKnightsTour(int boardSize) {
    this.boardSize = boardSize;
    random = new Random();
    movX = new int[] {1, 2, 2, 1, -1, -2, -2, -1};
    movY = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
    board = new boolean[boardSize][boardSize];
  }

  public void findTour(int target) {
    this.target = target;
    found = 0;
    while (found < target) {
      int x = random.nextInt(boardSize);
      int y = random.nextInt(boardSize);
      clearBoard();
      tryMove(x, y, 0);
    }
  }


  public void clearBoard() {
    for (int i = 0; i < boardSize; ++i) {
      for (int j = 0; j < boardSize; ++j) {
        board[i][j] = false;
      }
    }
  }

  public boolean tryMove(int x, int y, int curMoves) {
    if (x < 0 || x >= boardSize || y < 0 || y >= boardSize || board[x][y]) {
      return false;
    }
    if (found >= target) {
      return true;
    }
    board[x][y] = true;
    if (curMoves == boardSize * boardSize - 1) {
      found++;
      board[x][y] = false;
      if (found >= target) {
        return true;
      } else {
        return false;
      }
    }
    boolean ans = false;
    for (int i = 0; i < 8; ++i) {
      ans = ans || tryMove(x + movX[i], y + movY[i], curMoves + 1);
    }
    board[x][y] = false;
    return ans;
  }
}
