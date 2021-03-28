package ua.com.zinchenko.level3;

import java.util.Random;

public class GameOfLife {

    private final int LIVE_STATE = 1;
    private final int DIE_STATE = 0;

    public int[][] getNextState(int[][] boardState) {

        int[][] nextBoardState = boardState.clone();

        int rows = boardState.length;
        int columns = boardState[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
                int cellState = boardState[i][j];
                int liveAround = countLive(i, j, boardState);

                if (cellState == DIE_STATE) {
                    if (liveAround == 3) {
                        nextBoardState[i][j] = LIVE_STATE;
                    }
                }
                else {
                    if (liveAround != 2 && liveAround != 3) {
                        nextBoardState[i][j] = DIE_STATE;
                    }
                }
            }
        }
        return nextBoardState;
    }

    private int countLive(int i, int j, int[][] board){
        int count = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};

        for (int[] dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];

            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length) {
                if (board[x][y] == LIVE_STATE)
                    count ++;
            }
        }
        return count;
    }

    public int[][] generateRandomBoard(int rows, int columns) {
        int[][] board = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = (int) ( Math.random() * 2 );
            }
        }
        return board;
    }

    public void printBoard(int[][] board) {
        int rows = board.length;
        int columns = board[0].length;

        for (int[] row : board) {
            for (int j = 0; j < columns; j++) {
                System.out.print(row[j] + "|");
            }
            System.out.print("\n");
            for (int k = 0; k < columns * 2; k++) {
                System.out.print("-");
            }
            System.out.print("\n");
        }
    }
}

