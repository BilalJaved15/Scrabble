
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import static java.lang.System.exit;
import static java.lang.Thread.sleep;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Bilal Javed
 */
public class MatchRoundLogic {

    TilesBag bag;
    Board board;
    ArrayList<Player> players;
    int playersCount;
    int currPlayerPointer;
    ArrayList<Word> prevWords;
    int prevScore;
    int noOfTurns;
    MatchRoundGUI refg;
    boolean isFirstTurn;

    public MatchRoundLogic(ArrayList<Player> players, MatchRoundGUI gui, Board board) {
        bag = gui.bag;
        this.board = board;
        if (bag.getTilesCount() == 100) {
            isFirstTurn = true;
        } else {
            isFirstTurn = false;
        }
        this.players = players;
        this.playersCount = playersCount;
        this.refg = gui;
    }

    public boolean checkValidPos(int row, int col) {
        if (refg.board[row][col].getText().equals("")
                || refg.board[row][col].getText().equals("*")
                || refg.board[row][col].getText().equals("3L")
                || refg.board[row][col].getText().equals("3W")
                || refg.board[row][col].getText().equals("2L")
                || refg.board[row][col].getText().equals("2W")) {
            return true;
        }
        return false;
    }

    public boolean submitWord(Word word) {
        if (isFirstTurn == false && isConsecutiveWithOtherWords(word) == false) {
            refg.fr.setEnabled(false);
            PlacementErrorGUI temp = new PlacementErrorGUI("Uh-oh! Your tile must touch at least one of the already placed tiles! Try again maybe?", refg);
            return false;
        }
        boolean correctFirstPlacement = false;
        if (isFirstTurn == true) {
            for (int i = 0; i < word.noOfLetters; i++) {
                if (board.get(word.letters[i].row, word.letters[i].col).equals("*")) {
                    correctFirstPlacement = true;
                }
            }
            if (correctFirstPlacement == false) {
                refg.fr.setEnabled(false);
                PlacementErrorGUI temp = new PlacementErrorGUI("Uh-oh! You must place a tile at the middle of the board! Try again maybe?", refg);
                return false;
            }
        }
        if (word.getWordOrientation().equals("HORIZONTAL")) {
            int colCheck = word.letters[0].col;
            colCheck++;
            for (int i = 1; i < word.noOfLetters; i++, colCheck++) {
                if (word.letters[i].col != colCheck) {
                    refg.fr.setEnabled(false);
                    PlacementErrorGUI temp = new PlacementErrorGUI("Uh-oh! You did not place the tiles consecutively! Try again maybe?", refg);
                    return false;
                }
            }
        } else {
            int rowCheck = word.letters[0].row;
            rowCheck++;
            for (int i = 1; i < word.noOfLetters; i++, rowCheck++) {
                if (word.letters[i].row != rowCheck) {
                    refg.fr.setEnabled(false);
                    PlacementErrorGUI temp = new PlacementErrorGUI("Uh-oh! You did not place the tiles consecutively! Try again maybe?", refg);
                    return false;
                }
            }
        }
        isFirstTurn = false;
        placeWord(word);
        addScore(word);
        switchTurn();
        recordBoardState();
        recordPlayersState();
        recordTilesBagState();
        return true;
    }

    public Word getBotWord() {
        Word generatedWord = ((ComputerPlayer) players.get(currPlayerPointer)).performMove(board);
        return generatedWord;
    }

    public void submitBotWord(Word word) {
        for (int i = 0; i < word.noOfLetters; i++) {
            board.set(word.letters[i].row, word.letters[i].col, word.letters[i].tile.tileLetter);
            refg.board[word.letters[i].row][word.letters[i].col].setText("<html>" + word.letters[i].tile.tileLetter + "<sub>" + word.letters[i].tile.tileScore + "</sub></html>");
            refg.board[word.letters[i].row][word.letters[i].col].setBackground(new Color(68, 53, 34));
            refg.board[word.letters[i].row][word.letters[i].col].setForeground(new Color(245, 226, 203));
        }
        noOfTurns++;
        isFirstTurn = false;
        addBotScore(word);
        if(bag.getTilesCount() >= 7){
        for (int i = 0; i < word.noOfLetters; i++) {
            if (word.letters[i].isMyOwnTile == true) {
                for (int j = 0; j < 7; j++) {
                    if (word.letters[i].tile.tileLetter.equals(players.get(currPlayerPointer).rack.get(j).tileLetter)) {
                        players.get(currPlayerPointer).rack.removeTile(j);
                        players.get(currPlayerPointer).rack.addTile(bag.extractRandomTile());
                    }
                }
            }
        }
        }
        else{
            GameResultGUI temp = new GameResultGUI(players);
            refg.fr.setVisible(false);
        }
        switchTurn();
    }

    public void addBotScore(Word word) {
        TurnSummaryLogic temp = new TurnSummaryLogic(word, board);
        prevWords = temp.words;
        prevScore = temp.totalScore;
        refg.fr.setEnabled(false);
        players.get(currPlayerPointer).score += temp.totalScore;
    }

    public void invokeBotForMove() {
        Word generatedWord = getBotWord();
        submitBotWord(generatedWord);
    }

    public void addScore(Word word) {
        TurnSummaryLogic temp = new TurnSummaryLogic(word, board);
        prevWords = temp.words;
        prevScore = temp.totalScore;
        refg.fr.setEnabled(false);
        TurnSummaryGUI temp2 = new TurnSummaryGUI(temp.words, temp.score, refg);
        players.get(currPlayerPointer).score += temp.totalScore;
    }

    public void switchTurn() {
        if (bag.getTilesCount() >= 7){
            for (int i = 0; i < 7; i++) {
                if (refg.currPlayerRack[i].getBackground() == Color.RED) {
                    players.get(currPlayerPointer).rack.removeTile(i);
                    players.get(currPlayerPointer).rack.addTile(bag.extractRandomTile());
                }
            }
        }
        else {
            GameResultGUI temp = new GameResultGUI(players);
            refg.fr.setVisible(false);
        }
        currPlayerPointer = (currPlayerPointer + 1) % players.size();
        refg.currPlayerPointer = (refg.currPlayerPointer + 1) % refg.players.size();
        refg.currPlayer.setText(refg.players.get(refg.currPlayerPointer).getName());
        refg.currScore.setText("" + refg.players.get(refg.currPlayerPointer).getScore());
        refg.btnChangeTiles.setEnabled(true);
        for (int i = 0; i < 7; i++) {
            refg.currPlayerRack[i].setText("<html>" + refg.players.get(refg.currPlayerPointer).rack.get(i).tileLetter + "<sub>" + refg.players.get(refg.currPlayerPointer).rack.get(i).tileScore + "</sub></html>");
        }
        for (int i = 0; i < 7; i++) {
            refg.currPlayerRack[i].setBackground(new Color(245, 226, 203));
        }
        refg.btnSubmitWord.setEnabled(false);

        if (prevWords != null) {
            refg.btnChallengeWord.setEnabled(true);
        } else {
            refg.btnChallengeWord.setEnabled(false);
        }

        if (players.get(currPlayerPointer).getName().contains("(Computer)")) {
            refg.fr.setEnabled(false);
            invokeBotForMove();
            refg.fr.setEnabled(true);
        }
    }

    public void changeTiles() {
        if (bag.getTilesCount() >= 7) {
            for (int i = 0; i < refg.currPlayerRack.length; i++) {
                if (refg.currPlayerRack[i].getBackground() == Color.GREEN) {
                    bag.insertTiles(players.get(currPlayerPointer).rack.get(i));
                    players.get(currPlayerPointer).rack.removeTile(i);
                    players.get(currPlayerPointer).rack.addTile(bag.extractRandomTile());
                    refg.currPlayerRack[i].setText("<html>" + players.get(currPlayerPointer).rack.get(i).tileLetter + "<sub>" + players.get(currPlayerPointer).rack.get(i).tileScore + "</sub></html>");
                }
            }
        }
        else {
            GameResultGUI temp = new GameResultGUI(players);
            refg.fr.setVisible(false);
        }
        prevWords = null;
        switchTurn();
    }

    public void placeWord(Word word) {
        for (int i = 0; i < word.noOfLetters; i++) {
            board.set(word.letters[i].row, word.letters[i].col, word.letters[i].tile.tileLetter);
            refg.board[word.letters[i].row][word.letters[i].col].setBackground(new Color(68, 53, 34));
            refg.board[word.letters[i].row][word.letters[i].col].setForeground(new Color(245, 226, 203));
        }
        noOfTurns++;
    }

    public void addPlacedLetters(Word word, int row, int col) {
        if (word.noOfLetters == 0 || word.searchRow(row - 1) || word.searchRow(row + 1) || word.searchCol(col - 1) || word.searchCol(col + 1)) {
            return;
        }
        word.sortWord();
        if (word.noOfLetters == 1) {
            if (col == word.letters[0].col) {
                if (word.letters[0].row < row) {
                    for (int i = word.letters[0].row + 1; i < row; i++) {
                        if (board.isBlankPlace(i, word.letters[0].col)) {
                            return;
                        }
                    }
                    for (int i = word.letters[0].row + 1; i < row; i++) {
                        word.addLetter(i, word.letters[0].col, board.get(i, word.letters[0].col), false);
                    }
                } else {
                    for (int i = word.letters[0].row - 1; i > row; i--) {
                        if (board.isBlankPlace(i, word.letters[0].col)) {
                            return;
                        }
                    }
                    for (int i = word.letters[0].row - 1; i > row; i--) {
                        word.addLetter(i, word.letters[0].col, board.get(i, word.letters[0].col), false);
                    }
                }
            } else if (row == word.letters[0].row) {
                if (word.letters[0].col < col) {
                    for (int i = word.letters[0].col + 1; i < col; i++) {
                        if (board.isBlankPlace(word.letters[0].row, i)) {
                            return;
                        }
                    }
                    for (int i = word.letters[0].col + 1; i < col; i++) {
                        word.addLetter(word.letters[0].row, i, board.get(word.letters[0].row, i), false);
                    }
                } else {
                    for (int i = word.letters[0].col - 1; i > col; i--) {
                        if (board.isBlankPlace(word.letters[0].row, i)) {
                            return;
                        }
                    }
                    for (int i = word.letters[0].col - 1; i > col; i--) {
                        word.addLetter(word.letters[0].row, i, board.get(word.letters[0].row, i), false);
                    }
                }
            }
        } else {
            if (word.getWordOrientation().equals("HORIZONTAL")) {
                if (word.letters[word.noOfLetters - 1].col < col) {
                    for (int i = word.letters[word.noOfLetters - 1].col + 1; i < col; i++) {
                        if (board.isBlankPlace(word.letters[0].row, i)) {
                            return;
                        }
                    }
                    for (int i = word.letters[word.noOfLetters - 1].col + 1; i < col; i++) {
                        word.addLetter(word.letters[0].row, i, board.get(word.letters[0].row, i), false);
                    }
                } else {
                    for (int i = word.letters[0].col - 1; i > col; i--) {
                        if (board.isBlankPlace(word.letters[0].row, i)) {
                            return;
                        }
                    }
                    for (int i = word.letters[0].col - 1; i > col; i--) {
                        word.addLetter(word.letters[0].row, i, board.get(word.letters[0].row, i), false);
                    }
                }
            } else {
                if (word.letters[word.noOfLetters - 1].row < row) {
                    for (int i = word.letters[word.noOfLetters - 1].row + 1; i < row; i++) {
                        if (board.isBlankPlace(i, word.letters[0].col)) {
                            return;
                        }
                    }
                    for (int i = word.letters[word.noOfLetters - 1].row + 1; i < row; i++) {
                        word.addLetter(i, word.letters[0].col, board.get(i, word.letters[0].col), false);
                    }
                } else {
                    for (int i = word.letters[0].row - 1; i > row; i--) {
                        if (board.isBlankPlace(i, word.letters[0].col)) {
                            return;
                        }
                    }
                    for (int i = word.letters[0].row - 1; i > row; i--) {
                        word.addLetter(i, word.letters[0].col, board.get(i, word.letters[0].col), false);
                    }
                }
            }
        }
        word.sortWord();
    }

    public void removePlacedLetters(Word word, int row, int col) {
        boolean removableLetters = false;
        for (int i = 0; i < word.noOfLetters; i++) {
            if (word.letters[i].isMyOwnTile == false) {
                removableLetters = true;
            }
        }
        if (!removableLetters) {
            return;
        }
        word.sortWord();
        int index = word.getIndexOf(row, col);
        if (word.wordOrientation.equals("HORIZONTAL")) {
            if (index == 0) {
                for (int i = index + 1; (word.letters[i].isMyOwnTile == false); i++) {
                    word.removeLetter(word.letters[0].row, word.letters[i].col);
                }
            }
            if (index == word.noOfLetters - 1) {
                for (int i = index - 1; (word.letters[i].isMyOwnTile == false); i--) {
                    word.removeLetter(word.letters[0].row, word.letters[i].col);
                }
            } else {
                for (int i = index + 1; (word.letters[i].isMyOwnTile == false); i++) {
                    word.removeLetter(word.letters[0].row, word.letters[i].col);
                }
                for (int i = index - 1; (word.letters[i].isMyOwnTile == false); i--) {
                    word.removeLetter(word.letters[0].row, word.letters[i].col);
                }
            }
        } else {
            if (index == 0) {
                for (int i = index + 1; (word.letters[i].isMyOwnTile == false); i++) {
                    word.removeLetter(word.letters[i].row, word.letters[0].col);
                }
            }
            if (index == word.noOfLetters - 1) {
                for (int i = index - 1; (word.letters[i].isMyOwnTile == false); i--) {
                    word.removeLetter(word.letters[i].row, word.letters[0].col);
                }
            } else {
                for (int i = index + 1; (word.letters[i].isMyOwnTile == false); i++) {
                    word.removeLetter(word.letters[i].row, word.letters[0].col);

                }
                for (int i = index - 1; (word.letters[i].isMyOwnTile == false); i--) {
                    word.removeLetter(word.letters[i].row, word.letters[0].col);
                }
            }
        }
        word.sortWord();
    }

    public boolean isConsecutiveWithOtherWords(Word word) {
        for (int i = 0; i < word.noOfLetters; i++) {
            if (word.letters[i].isMyOwnTile == false) {
                return true;
            }
        }
        if (word.wordOrientation == "HORIZONTAL") {
            if (!board.isBlankPlace(word.letters[0].row - 1, word.letters[0].col) || !board.isBlankPlace(word.letters[0].row + 1, word.letters[0].col)
                    || !board.isBlankPlace(word.letters[0].row, word.letters[0].col - 1)) {
                return true;
            }
            for (int i = 1; i < word.noOfLetters - 1; i++) {
                if (!board.isBlankPlace(word.letters[i].row - 1, word.letters[i].col) || !board.isBlankPlace(word.letters[i].row + 1, word.letters[i].col)) {
                    return true;
                }
            }
            if (!board.isBlankPlace(word.letters[0].row - 1, word.letters[word.noOfLetters - 1].col) || !board.isBlankPlace(word.letters[0].row + 1, word.letters[word.noOfLetters - 1].col)
                    || !board.isBlankPlace(word.letters[0].row, word.letters[word.noOfLetters - 1].col + 1)) {
                return true;
            }
        } else {
            if (!board.isBlankPlace(word.letters[0].row, word.letters[0].col - 1) || !board.isBlankPlace(word.letters[0].row, word.letters[0].col + 1)
                    || !board.isBlankPlace(word.letters[0].row - 1, word.letters[0].col)) {
                return true;
            }
            for (int i = 1; i < word.noOfLetters - 1; i++) {
                if (!board.isBlankPlace(word.letters[i].row, word.letters[i].col - 1) || !board.isBlankPlace(word.letters[i].row, word.letters[i].col + 1)) {
                    return true;
                }
            }
            if (!board.isBlankPlace(word.letters[word.noOfLetters - 1].row + 1, word.letters[0].col) || !board.isBlankPlace(word.letters[word.noOfLetters - 1].row, word.letters[0].col + 1)
                    || !board.isBlankPlace(word.letters[word.noOfLetters - 1].row, word.letters[0].col - 1)) {
                return true;
            }
        }
        return false;
    }

    public void challengeLastTurn() {
        refg.fr.setEnabled(false);
        ChallengeWordGUI temp = new ChallengeWordGUI(prevWords, refg, this);
    }

    public void reverseLastTurn() {
        for (int i = 0; i < prevWords.get(0).noOfLetters; i++) {
            if (prevWords.get(0).letters[i].isMyOwnTile == true) {
                int row = prevWords.get(0).letters[i].row;
                int col = prevWords.get(0).letters[i].col;
                refg.board[row][col].setText(Board.getDefaultBoardPos(row, col));
                board.set(row, col, Board.getDefaultBoardPos(row, col));
                refg.board[row][col].setForeground(new Color(68, 53, 34));
                refg.board[row][col].setBackground(new Color(245, 226, 203));
            }
        }
        int prevPlayerPointer = currPlayerPointer - 1;
        if (prevPlayerPointer == -1) {
            prevPlayerPointer = players.size() - 1;
        }
        players.get(prevPlayerPointer).score -= prevScore;
        noOfTurns--;
        if (noOfTurns == 0) {
            isFirstTurn = true;
        }
    }

    public void recordBoardState() {
        try {
            File file = new File("assets/boardState.txt");
            file.delete();
            FileWriter fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    if (j != 14) {
                        if (board.get(i, j).length() == 1) {
                            pw.print(board.get(i, j) + ":");
                        } else {
                            pw.print("-" + ":");
                        }
                    } else {
                        if (board.get(i, j).length() == 1) {
                            pw.print(board.get(i, j));
                        } else {
                            pw.print("-");
                        }
                    }
                }

                pw.println();
            }

            pw.println();

            pw.close();
            fw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void recordPlayersState() {
        try {
            File file = new File("assets/playersState.txt");
            file.delete();
            FileWriter fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(currPlayerPointer);


            for (int i = 0; i < players.size(); i++) {
                pw.println(players.get(i).getName());
                for (int j = 0; j < players.get(i).rack.noOfTiles; j++) {
                    if (j != 6) {
                        pw.print(players.get(i).rack.get(j).tileLetter + ":");
                    } else {
                        pw.print(players.get(i).rack.get(j).tileLetter);
                    }
                }
                pw.println();
                pw.println(players.get(i).score);
            }

            pw.close();
            fw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void recordTilesBagState() {
        try {
            File file = new File("assets/tilesBagState.txt");
            file.delete();
            FileWriter fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0; i < bag.getTilesCount(); i++) {
                if (i != bag.getTilesCount() - 1) {
                    pw.print(bag.tileArray[i].tileLetter + ":");
                } else {
                    pw.print(bag.tileArray[i].tileLetter);
                }
            }

            pw.close();
            fw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
