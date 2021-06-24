
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Bilal Javed
 */
public class MatchRoundGUIButtonHandler implements ActionListener {

    MatchRoundGUI refg;
    MatchRoundLogic logic;
    boolean isRackButtonPressed;
    int currPressedRackIndex;
    boolean isChangeTilesPressed;
    int tilePlacementBuffer;
    int tileChangeBuffer;
    Word word;

    public MatchRoundGUIButtonHandler(MatchRoundGUI gui) {
        refg = gui;
        logic = new MatchRoundLogic(refg.players, refg, refg.refBoard);
        isRackButtonPressed = false;
        isChangeTilesPressed = false;
        tilePlacementBuffer = 0;
        tileChangeBuffer = 0;
        currPressedRackIndex = -1;
        word = new Word();
    }

    public void invokeBotForMove() {
        Word generatedWord = logic.getBotWord();
        logic.submitBotWord(generatedWord);
        refg.fr.setEnabled(true);
    }

    public void switchTurn() {
        isRackButtonPressed = false;
        isChangeTilesPressed = false;
        tilePlacementBuffer = 0;
        tileChangeBuffer = 0;
        currPressedRackIndex = -1;
        word = new Word();
    }

    public void removeTileFromBoard(int row, int col, int rackIndex) {
        refg.board[row][col].setText(logic.board.get(row, col));
        refg.currPlayerRack[rackIndex].setBackground(new Color(245, 226, 203));
        tilePlacementBuffer--;
    }

    public void placeTileOnBoard(int row, int col, int pressedRackIndex) {
        refg.board[row][col].setText(refg.currPlayerRack[pressedRackIndex].getText());
        refg.currPlayerRack[pressedRackIndex].setBackground(Color.red);
        tilePlacementBuffer++;
    }

    public void addRackTileToPlacementBuffer(int pressedRackIndex) {
        refg.currPlayerRack[currPressedRackIndex].setBackground(Color.YELLOW);
        isRackButtonPressed = true;
    }

    public void removeRackTileFromPlacementBuffer(int currPressedRackIndex) {
        refg.currPlayerRack[currPressedRackIndex].setBackground(new Color(245, 226, 203));
        isRackButtonPressed = false;
    }

    public void addRackTileToChangeBuffer(int newPressedRackIndex) {
        refg.currPlayerRack[newPressedRackIndex].setBackground(Color.GREEN);
        tileChangeBuffer++;
    }

    public void removeRackTileFromChangeBuffer(int newPressedRackIndex) {
        refg.currPlayerRack[newPressedRackIndex].setBackground(new Color(245, 226, 203));
        tileChangeBuffer--;
    }

    public boolean blankTileReturn(int row, int col) {
        for (int i = 0; i < refg.players.get(refg.currPlayerPointer).rack.noOfTiles; i++) {
            if (refg.players.get(refg.currPlayerPointer).rack.get(i).blankTile == true) {
                if (refg.currPlayerRack[i].getBackground() == Color.RED) {
                    removeTileFromBoard(row, col, i);
                    logic.removePlacedLetters(word, row, col);
                    word.removeLetter(row, col);
                }
                return true;
            }
        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().substring(0, 4).equals("rack")) { //A RACK TILE IS PRESSED
            //FETCHING WHICH INDEX IS PRESSED
            int newPressedRackIndex = Integer.parseInt(e.getActionCommand().substring(4, 5));
            if (isChangeTilesPressed == true) { //IF THE SWAP TILES BUTTON IS PRESSED
                if (refg.currPlayerRack[newPressedRackIndex].getBackground() == Color.GREEN) {
                    //GREEN COLOR OF A  RACK TILE SPECIFIES THAT IT HAS BEEN CHOSEN FOR SWAPPING
                    removeRackTileFromChangeBuffer(newPressedRackIndex);
                } else {
                    //IF TILE COLOR IS  NOT GREEN THEN IT HAS TO BE PLACED IN CHANGE BUFFER
                    addRackTileToChangeBuffer(newPressedRackIndex);
                    refg.btnConfirmChange.setEnabled(true);
                }
                //IF, AFTER REMOVING A TITLE FROM CHANGE BUFFER, CHANGE BUFFER IS EMPTY, THE
                //CONFIRM CHANGE BUTTON MUST NOT BE ENABLED
                if (tileChangeBuffer == 0) {
                    refg.btnConfirmChange.setEnabled(false);
                }
            } else {
                if (refg.currPlayerRack[newPressedRackIndex].getBackground() != Color.RED) {
                    if (newPressedRackIndex != currPressedRackIndex) {
                        if (isRackButtonPressed == false) {
                            //SPECIFYING THAT CURRENTLY PLACEMENT BUFFER IS EMPTY
                            currPressedRackIndex = newPressedRackIndex;
                            addRackTileToPlacementBuffer(newPressedRackIndex);
                        } else if (isRackButtonPressed == true) {
                            //REMOVE PREVIOUS TILE AND ADD THE NEW ONE
                            removeRackTileFromPlacementBuffer(currPressedRackIndex);
                            currPressedRackIndex = newPressedRackIndex;
                            addRackTileToPlacementBuffer(currPressedRackIndex);
                        }
                    } else {
                        if (refg.currPlayerRack[currPressedRackIndex].getBackground() == Color.YELLOW) {
                            removeRackTileFromPlacementBuffer(currPressedRackIndex);
                        } else {
                            addRackTileToPlacementBuffer(currPressedRackIndex);
                        }
                        return;
                    }
                }
            }
        } else if (e.getActionCommand().substring(0, 5).equals("board")) {
            String[] indexes = e.getActionCommand().split(":");
            int row = Integer.parseInt(indexes[1]);
            int col = Integer.parseInt(indexes[2]);
            if (!isChangeTilesPressed) {
                if (isRackButtonPressed == true) {
                    String letter = refg.players.get(refg.currPlayerPointer).rack.get(currPressedRackIndex).tileLetter;
                    logic.addPlacedLetters(word, row, col);
                    boolean isValidPos = logic.checkValidPos(row, col);
                    if (isValidPos) {
                        boolean isWordAdded = word.addLetter(row, col, letter, true);
                        if (isWordAdded) {
                            removeRackTileFromPlacementBuffer(currPressedRackIndex);
                            placeTileOnBoard(row, col, currPressedRackIndex);
                            if (refg.players.get(refg.currPlayerPointer).rack.get(currPressedRackIndex).blankTile == true) {
                                BlankTileAssignerGUI inpBlTile = new BlankTileAssignerGUI(row, col, refg);
                            }
                            word.letters[word.noOfLetters - 1].tile.setTileLetter(refg.board[row][col].getText().substring(6, 7));
                            refg.btnChangeTiles.setEnabled(false);
                            if (tilePlacementBuffer > 1) {
                                refg.btnSubmitWord.setEnabled(true);
                            }
                            if (tilePlacementBuffer > 0) {
                                refg.btnChallengeWord.setEnabled(false);
                            }
                        } else {
                            String errorMessage = "Uh-oh! You did not place the tiles consecutively! Try again maybe?";
                            refg.fr.setEnabled(false);
                            PlacementErrorGUI temp = new PlacementErrorGUI(errorMessage, refg);
                        }
                    } else {
                        String errorMessage = "Uh-oh! You cannot place the tile at the selected place! Try again maybe?";
                        refg.fr.setEnabled(false);
                        PlacementErrorGUI temp = new PlacementErrorGUI(errorMessage, refg);
                    }
                } else {
                    boolean replaced = false, blankPlace = logic.checkValidPos(row, col);
                    if (!blankPlace) {
                        String pressedBoardLetter = refg.board[row][col].getText().substring(6, 7);
                        boolean wasBlankTile = blankTileReturn(row, col);
                        if (!wasBlankTile) {
                            for (int i = 0; i < refg.players.get(refg.currPlayerPointer).rack.noOfTiles && !replaced; i++) {
                                if (pressedBoardLetter.equals(refg.players.get(refg.currPlayerPointer).rack.get(i).tileLetter)
                                        && refg.currPlayerRack[i].getBackground() == Color.RED) {
                                    removeTileFromBoard(row, col, i);
                                    logic.removePlacedLetters(word, row, col);
                                    word.removeLetter(row, col);
                                    replaced = true;
                                }
                            }
                        }
                    }
                    if (tilePlacementBuffer == 1) {
                        refg.btnSubmitWord.setEnabled(false);
                        refg.btnChallengeWord.setEnabled(false);
                    }
                    if (tilePlacementBuffer == 0) {
                        refg.btnSubmitWord.setEnabled(false);
                        refg.btnChangeTiles.setEnabled(true);
                        if (logic.prevWords != null) {
                            refg.btnChallengeWord.setEnabled(true);
                        }
                    }
                }
            }
        } else if (e.getActionCommand().equals("Change Tiles!")) {
            isChangeTilesPressed = true;
            if (isRackButtonPressed == true) {
                removeRackTileFromPlacementBuffer(currPressedRackIndex);
                addRackTileToChangeBuffer(currPressedRackIndex);
            }
            refg.btnSubmitWord.setVisible(false);
            refg.btnChangeTiles.setVisible(false);
            refg.btnConfirmChange.setVisible(true);
            refg.btnCancelChange.setVisible(true);
            refg.btnConfirmChange.setEnabled(false);
            if (tileChangeBuffer > 0) {
                refg.btnConfirmChange.setEnabled(true);
            }
        } else if (e.getActionCommand().equals("Submit Word!")) {
            word.sortWord();
            boolean wordSubmission = logic.submitWord(word);
            if (wordSubmission == true) {
                switchTurn();
            }
        } else if (e.getActionCommand().equals("Confirm Change!")) {
            logic.changeTiles();
            for (int i = 0; i < refg.currPlayerRack.length; i++) {
                if (refg.currPlayerRack[i].getBackground() == Color.GREEN) {
                    removeRackTileFromChangeBuffer(i);
                }
            }
            isChangeTilesPressed = false;
            refg.btnConfirmChange.setVisible(false);
            refg.btnCancelChange.setVisible(false);
            refg.btnSubmitWord.setVisible(true);
            refg.btnChangeTiles.setVisible(true);
            refg.btnSubmitWord.setEnabled(false);
            switchTurn();
        } else if (e.getActionCommand().equals("Cancel Change!")) {
            for (int i = 0; i < refg.currPlayerRack.length; i++) {
                if (refg.currPlayerRack[i].getBackground() == Color.GREEN) {
                    removeRackTileFromChangeBuffer(i);
                }
            }
            isChangeTilesPressed = false;
            refg.btnConfirmChange.setVisible(false);
            refg.btnCancelChange.setVisible(false);
            refg.btnSubmitWord.setVisible(true);
            refg.btnChangeTiles.setVisible(true);
            refg.btnSubmitWord.setEnabled(false);
        } else if (e.getActionCommand().equals("Challenge Last Word!")) {
            logic.challengeLastTurn();
        }
    }
}
