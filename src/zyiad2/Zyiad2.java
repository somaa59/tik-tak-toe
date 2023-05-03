package zyiad2;

import java.util.Scanner;

public class Zyiad2 {

    public static char board[][] = new char[3][3];

//print X O Matrix       
    public static void drowBoard(char board[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + board[i][j] + " |");
            }
            System.out.println("\n -------------- \n");
        }
    }
//------------------------------------------

    public static boolean havesamevaleuandnotempty(char x, char y, char z) {
        if ((x == y) && (x == z) && (x != ' ')) {
            return true;
        }
        return false;
    }
//------------------------------------------

    public static int checkWinner(char board[][]) {
        /*
       2: x is winer
      -2: o is winer
       0:   Tie
       1:   no winer
        */
        //for row      
        for (int i = 0; i < 3; i++) {
            if (havesamevaleuandnotempty(board[i][0], board[i][1], board[i][2])) {
                return (board[i][0] == 'x' ? 2 : -2);
            }
        }
        //for colum   
        for (int i = 0; i < 3; i++) {
            if (havesamevaleuandnotempty(board[0][i], board[1][i], board[2][i])) {
                return (board[0][i] == 'x' ? 2 : -2);
            }
        }
        //Diameter 1     
        if (havesamevaleuandnotempty(board[0][0], board[1][1], board[2][2])) {
            return (board[0][0] == 'x' ? 2 : -2);

        }
        //Diameter 2     
        if (havesamevaleuandnotempty(board[2][0], board[1][1], board[0][2])) {
            return board[2][0] == 'x' ? 2 : -2;
        }

        boolean tie = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    tie = false;
                }
            }
        }

        if (tie) {
            return 0;
        }

        return 1;
    }
/////////////////////
  ///fun minmax
   public static int minimax(char board[][], int depth, boolean isMaximizing,boolean firstTime ) {
        int result = checkWinner(board);
        if (depth == 0 || result != 1) {
            return result;
        }

        if (isMaximizing) {

            int finalScore = -10;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'x';
                        int score = minimax(board, depth - 1, false,false);
                        board[i][j] = ' ';
                        if (score > finalScore) {
                            finalScore = score;
                        }
                        if (firstTime) {
                            System.out.println("score, "+i+","+j+" :"+score);
                        }
                    }
                }
            }
            return finalScore;
        } else {
            int finalI=0,finalJ=0;
            int finalScore = 10;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'o';
                        int score = minimax(board, depth - 1, true,false);
                        board[i][j] = ' ';
                        if (score < finalScore) {
                            finalScore = score; 
                            finalI=i;
                            finalJ=j;
                        }
                        if (firstTime) {
                            System.out.println("else : score, "+i+","+j+" :"+score);
                        }
                    }
                }

            }
            if (firstTime) {
                board[finalI][finalJ]='o';
            }
             return finalScore;    
        }
            
    }
///////////////////////
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Zyiad2.board[i][j] = ' ';
            }
        }

        int x, y;
        boolean winner = false;
        char player = 'x';
        Scanner s = new Scanner(System.in);
        while (!winner) {
            System.out.print("enter position : ");
            x = s.nextInt();
            y = s.nextInt();
            if (board[x][y] == ' ') {
                board[x][y] = player;
//                if (player == 'x') {
//                    player = 'o';
//                } else {
//                    player = 'x';
//                }
                  int result1 =minimax(board, 100, false,true);
                System.out.println("result: "+result1);
                drowBoard(board);
             
                winner = checkWinner(board) != 1;

            } else {
                System.out.println("The field is not empty \n");
            }
        }

        int result = checkWinner(board);
        //  System.out.println("resut= "+result);
        if (result == 0) {
            System.out.println("Tie");
        } else {
            System.out.println(((result == 2) ? "x" : "o") + ":  The winer");
        }

    }

}
