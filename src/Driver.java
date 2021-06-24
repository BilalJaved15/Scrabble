
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Bilal Javed
 */
public class Driver {

    public static void main(String[] args) throws IOException {
        MainMenuGUI xx = new MainMenuGUI();
        /*ArrayList<Player> players = new ArrayList<Player>();
        players.add(new HumanPlayer());
        players.get(0).name = "BHUTTO";
        players.get(0).score = 43;
        players.add(new HumanPlayer());
        players.get(1).name = "IMRAN";
        players.get(1).score = 98;
        players.add(new HumanPlayer());
        players.get(2).name = "KUJO JOTARO";
        players.get(2).score = 138;
        players.add(new HumanPlayer());
        players.get(3).name = "EREN YAEGAR";
        players.get(3).score = 19;
        GameResultGUI xx = new GameResultGUI(players);*/
        //BlankTileAssignerGUI x = new BlankTileAssignerGUI(3, 4);
        //WordCheck j = new WordCheck("nice");
        //System.out.println(j.isWord());
        //TilesBag xx = new TilesBag();
        //System.out.println(xx.extractRandomTile().tileLetter);
        //System.out.println(xx.extractRandomTile().tileLetter);

        /*Board board = new Board();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board.get(i, j).length() == 1 || board.get(i, j).length() == 0) {
                    System.out.print("  " + ":");
                } else {
                    System.out.print(board.get(i, j) + ":");
                }
            }
            System.out.println();
        }*/
        /*board.set(7, 7, "R");
        board.set(7, 6, "O");
        board.set(7, 5, "H");
        board.set(7, 8, "N");
        board.set(5, 7, "F");
        board.set(6, 7, "A");
        board.set(8, 7, "M");
        board.set(9, 7, "S");
        board.set(8, 8, "O");
        board.set(8, 9, "B");
        board.set(9, 6, "A");
        board.set(9, 5, "P");
        board.set(9, 8, "T");
        board.set(9, 9, "E");
        board.set(10, 6, "T");
        board.set(10, 5, "I");
        board.set(10, 4, "B");
        board.set(6, 5, "S");*/
        /*ComputerPlayer x = new ComputerPlayer();
        TilesBag xx = new TilesBag();
        for (int i = 0; i < 7; i++){
            xx.shuffleTiles();
            String temp = xx.getRandomTileLetter();
            while (temp.equals("BL")){
                temp = xx.getRandomTileLetter();
            }
            x.rack[i] = new Tile(temp);
        }
        board.printBoard();
        x.performMove(board);
        System.out.println();
        board.printBoard();*/

        /*WordGenerator x = new WordGenerator("FJASDCNEOI", new Pattern(""));
        ArrayList<WordGenerator.Word> xx = x.fetchMatchingWords();
        for (int i = 0; i < xx.size(); i++){
            System.out.println(xx.get(i).word);
        }*/

        /*String x = "1:B:3:J:10";

        new Pattern(x).isAMatch("DASD");

        StringTokenizer prefixtokenizer = new StringTokenizer(x, ":");
        String strMaxprefixLength = prefixtokenizer.nextToken();
        x = x.replaceFirst(strMaxprefixLength + ":", "");

        StringTokenizer postFixtokenizer = new StringTokenizer(((new StringBuilder()).append(x).reverse().toString()), ":");
        String strMaxPostfixLength  = postFixtokenizer.nextToken();
        x = new StringBuilder().append(x).reverse().toString().replaceFirst(strMaxPostfixLength + ":", "");
        x = new StringBuilder().append(x).reverse().toString();
        strMaxPostfixLength = new StringBuilder().append(strMaxPostfixLength).reverse().toString();



        System.out.println(x);
        System.out.println(strMaxprefixLength);
        System.out.println(strMaxPostfixLength);

        String[] xSplit = x.split(":");*/
        //System.out.println(x.split(":").length);
        /*String h = "7:F:7";
        String[] hSplit = h.split(":");*/
        //System.out.println(x.split(":").length);
        /*for (int i = 0; i < hSplit.length; i++){
            System.out.println(hSplit[i]);
        }
        for (int i = 0; i < xSplit.length; i++){
            System.out.println(xSplit[i]);
        }*/
        //WordGenerator x = new WordGenerator("GONFTEDB", new Pattern("10:B:4"));
        //System.out.println(x.getHighestScoreWord());
    }
}
//    public void test() {
//        String[][] board;
//        String[][] multiplierBoard;
//        board = new String[15][15];
//        multiplierBoard = new String[15][15];
//        for (int i = 0; i < 15; i++) {
//            for (int j = 0; j < 15; j++) {
//                board[i][j] = new String("");
//            }
//        }
//
//        board[0][0] = ("3W");
//        board[0][3] = ("2L");
//        board[0][7] = ("3W");
//        board[0][11] = ("2L");
//        board[0][14] = ("3W");
//        board[1][1] = ("2W");
//        board[1][5] = ("3L");
//        board[1][9] = ("3L");
//        board[1][13] = ("2W");
//        board[2][2] = ("2W");
//        board[2][6] = ("2L");
//        board[2][8] = ("2L");
//        board[2][12] = ("2W");
//        board[3][0] = ("2L");
//        board[3][3] = ("2W");
//        board[3][7] = ("2L");
//        board[3][11] = ("2W");
//        board[3][14] = ("2L");
//        board[4][4] = ("2W");
//        board[4][10] = ("2W");
//        board[5][1] = ("3L");
//        board[5][5] = ("3L");
//        board[5][9] = ("3L");
//        board[5][13] = ("3L");
//        board[6][2] = ("2L");
//        board[6][6] = ("2L");
//        board[6][8] = ("2L");
//        board[6][12] = ("2L");
//        board[7][0] = ("3W");
//        board[7][14] = ("3W");
//        board[7][3] = ("2L");
//        board[7][11] = ("2L");
//        board[7][7] = ("*");
//
//        board[13][1] = ("2W");
//        board[13][5] = ("3L");
//        board[13][9] = ("3L");
//        board[13][13] = ("2W");
//        board[12][2] = ("2W");
//        board[12][6] = ("2L");
//        board[12][8] = ("2L");
//        board[12][12] = ("2W");
//        board[11][0] = ("2L");
//        board[11][3] = ("2W");
//        board[11][7] = ("2L");
//        board[11][11] = ("2W");
//        board[11][14] = ("2L");
//        board[10][4] = ("2W");
//        board[10][10] = ("2W");
//        board[9][1] = ("3L");
//        board[9][5] = ("3L");
//        board[9][9] = ("3L");
//        board[9][13] = ("3L");
//        board[8][2] = ("2L");
//        board[8][6] = ("2L");
//        board[8][8] = ("2L");
//        board[8][12] = ("2L");
//        board[14][0] = ("3W");
//        board[14][14] = ("3W");
//        board[14][3] = ("2L");
//        board[14][11] = ("2L");
//        board[14][7] = ("3W");
//
//        for (int i = 0; i < 15; i++) {
//            for (int j = 0; j < 15; j++) {
//                multiplierBoard[i][j] = new String(board[i][j]);
//            }
//        }
//
//        board[7][7] = "R";
//        board[7][6] = "O";
//        board[7][5] = "H";
//        board[7][8] = "N";
//        board[5][7] = "F";
//        board[6][7] = "A";
//        board[8][7] = "M";
//        board[9][7] = "S";
//        board[8][8] = "O";
//        board[8][9] = "B";
//        board[9][6] = "A";
//        board[9][5] = "P";
//        board[9][8] = "T";
//        board[9][9] = "E";
//        board[10][6] = "T";
//        board[10][5] = "I";
//        board[10][4] = "B";
//
//        Word newWord = new Word();
//        newWord.addLetter(10, 4, "B");
//        newWord.addLetter(10, 5, "I");
//        newWord.addLetter(10, 6, "T");
//
//        TurnScore tt = new TurnScore(newWord, multiplierBoard, board);
//
//        TurnSummaryGUI ii = new TurnSummaryGUI(tt.words, tt.score);
//    }
//}
