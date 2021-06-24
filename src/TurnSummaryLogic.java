/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 * @author Bilal Javed
 */
public class TurnSummaryLogic {

    Word word;
    Board board;
    ArrayList<Word> words;
    int[] score;
    int totalScore;

    public TurnSummaryLogic(Word word, Board board) {
        this.board = board;
        this.word = word;
        calculateWords();
        calculateScore();
    }

    public void calculateWords() {
        words = new ArrayList<Word>();
        int wordCount = 0;
        words.add(word);
        //THIS IF CONDITION ADDS THE TILES TO THE WORD THAT ARE IN THE SAME ROW/COL BUT NO PLACED BY THE USER
        if (word.wordOrientation == "HORIZONTAL") {
            for (int i = word.letters[0].col - 1; !board.isBlankPlace(word.letters[0].row, i); i--) {
                words.get(wordCount).addLetter(word.letters[0].row, i, board.get(word.letters[0].row, i), false);
            }
            words.get(0).sortWord();
            for (int i = (word.letters[words.get(0).noOfLetters - 1].col) + 1; !board.isBlankPlace(word.letters[0].row, i); i++) {
                words.get(wordCount).addLetter(word.letters[0].row, i, board.get(word.letters[0].row, i), false);
            }
            words.get(0).sortWord();
        } else {
            for (int i = word.letters[0].row - 1; !board.isBlankPlace(i, word.letters[0].col); i--) {
                words.get(wordCount).addLetter(i, word.letters[0].col, board.get(i, word.letters[0].col), false);
            }
            words.get(0).sortWord();
            for (int i = (word.letters[words.get(0).noOfLetters - 1].row) + 1; !board.isBlankPlace(i, word.letters[0].col); i++) {
                words.get(wordCount).addLetter(i, word.letters[0].col, board.get(i, word.letters[0].col), false);
            }
            words.get(0).sortWord();
        }
        wordCount = 1;
        for (int i = 0; i < word.noOfLetters; i++) {
            if (word.letters[i].isMyOwnTile == true) {
                if (word.wordOrientation == "HORIZONTAL") {
                    if (word.letters[0].row == 0) {
                        if (!board.isBlankPlace(1, word.letters[i].col)) {
                            words.add(new Word());
                            words.get(wordCount).addLetter(0, word.letters[i].col, board.get(0, word.letters[i].col), true);
                            for (int j = 1; !board.isBlankPlace(j, word.letters[i].col); j++) {
                                words.get(wordCount).addLetter(j, word.letters[i].col, board.get(j, word.letters[i].col), true);
                            }
                            words.get(wordCount).sortWord();
                            wordCount++;
                        }
                    } else if (word.letters[0].row == 14) {
                        if (!board.isBlankPlace(13, word.letters[i].col)) {
                            words.add(new Word());
                            words.get(wordCount).addLetter(14, word.letters[i].col, board.get(14, word.letters[i].col), true);
                            for (int j = 13; !board.isBlankPlace(j, word.letters[i].col); j--) {
                                words.get(wordCount).addLetter(j, word.letters[i].col, board.get(j, word.letters[i].col), true);
                            }
                            words.get(wordCount).sortWord();
                            wordCount++;
                        }
                    } else {
                        boolean wordAdded = false;
                        boolean isMyWord = true;
                        if ((!board.isBlankPlace(word.letters[i].row + 1, word.letters[i].col)) && (!board.isBlankPlace(word.letters[i].row - 1, word.letters[i].col))) {
                            isMyWord = false;
                        }
                        if (isMyWord) {
                            if ((!board.isBlankPlace(word.letters[i].row + 1, word.letters[i].col)) || (!board.isBlankPlace(word.letters[i].row - 1, word.letters[i].col))) {
                                words.add(new Word());
                                words.get(wordCount).addLetter(word.letters[i].row, word.letters[i].col, board.get(word.letters[i].row, word.letters[i].col), true);
                                wordAdded = true;
                            }
                            if (!board.isBlankPlace(word.letters[i].row + 1, word.letters[i].col)) {
                                for (int j = word.letters[i].row + 1; !board.isBlankPlace(j, word.letters[i].col); j++) {
                                    words.get(wordCount).addLetter(j, word.letters[i].col, board.get(j, word.letters[i].col), true);
                                }
                            }
                            if (!board.isBlankPlace(word.letters[i].row - 1, word.letters[i].col)) {
                                for (int j = word.letters[i].row - 1; !board.isBlankPlace(j, word.letters[i].col); j--) {
                                    words.get(wordCount).addLetter(j, word.letters[i].col, board.get(j, word.letters[i].col), true);
                                }
                            }
                            if (wordAdded == true) {
                                words.get(wordCount).sortWord();
                                wordCount++;
                            }
                        }
                    }
                } else {
                    if (word.letters[0].col == 0) {
                        if (!board.isBlankPlace(word.letters[i].row, 1)) {
                            words.add(new Word());
                            words.get(wordCount).addLetter(word.letters[i].row, 0, board.get(word.letters[i].row, 0), true);
                            for (int j = 1; !board.isBlankPlace(word.letters[i].row, j); j++) {
                                words.get(wordCount).addLetter(word.letters[i].row, j, board.get(word.letters[i].row, j), true);
                            }
                            words.get(wordCount).sortWord();
                            wordCount++;
                        }
                    } else if (word.letters[0].col == 14) {
                        if (!board.isBlankPlace(word.letters[i].row, 13)) {
                            words.add(new Word());
                            words.get(wordCount).addLetter(word.letters[i].row, 14, board.get(word.letters[i].row, 14), true);
                            for (int j = 13; !board.isBlankPlace(word.letters[i].row, j); j--) {
                                words.get(wordCount).addLetter(word.letters[i].row, j, board.get(word.letters[i].row, j), true);
                            }
                            words.get(wordCount).sortWord();
                            wordCount++;
                        }
                    } else {
                        boolean wordAdded = false;
                        boolean isMyWord = true;
                        if ((!board.isBlankPlace(word.letters[i].row, word.letters[i].col + 1)) && (!board.isBlankPlace(word.letters[i].row, word.letters[i].col - 1))) {
                            isMyWord = false;
                        }
                        if (isMyWord) {
                            if ((!board.isBlankPlace(word.letters[i].row, word.letters[i].col + 1)) || (!board.isBlankPlace(word.letters[i].row, word.letters[i].col - 1))) {
                                words.add(new Word());
                                words.get(wordCount).addLetter(word.letters[i].row, word.letters[i].col, board.get(word.letters[i].row, word.letters[i].col), true);
                                wordAdded = true;
                            }
                            if (!board.isBlankPlace(word.letters[i].row, word.letters[i].col + 1)) {
                                for (int j = word.letters[i].col + 1; !board.isBlankPlace(word.letters[i].row, j); j++) {
                                    words.get(wordCount).addLetter(word.letters[i].row, j, board.get(word.letters[i].row, j), true);
                                }
                            }
                            if ((!board.isBlankPlace(word.letters[i].row, word.letters[i].col - 1))) {
                                for (int j = word.letters[i].col - 1; !board.isBlankPlace(word.letters[i].row, j); j--) {
                                    words.get(wordCount).addLetter(word.letters[i].row, j, board.get(word.letters[i].row, j), true);
                                }

                            }
                            if (wordAdded == true) {
                                words.get(wordCount).sortWord();
                                wordCount++;
                            }
                        }
                    }
                }
            }
        }
    }

    public void calculateScore() {
        score = new int[words.size()];
        totalScore = 0;
        boolean isTripleWord = false;
        boolean isDoubleWord = false;
        for (int i = 0; i < words.size(); i++) {
            score[i] = 0;
            for (int j = 0; j < words.get(i).noOfLetters; j++) {
                int currTileScore = 0;
                currTileScore += Tile.getTileScoreByLetter(words.get(i).letters[j].tile.tileLetter);
                if (Board.getDefaultBoardPos(words.get(i).letters[j].row, words.get(i).letters[j].col).equals("2L")) {
                    currTileScore *= 2;
                } else if (Board.getDefaultBoardPos(words.get(i).letters[j].row, words.get(i).letters[j].col).equals("3L")) {
                    currTileScore *= 3;
                }
                if (Board.getDefaultBoardPos(words.get(i).letters[j].row, words.get(i).letters[j].col).equals("2W")) {
                    isDoubleWord = true;
                } else if (Board.getDefaultBoardPos(words.get(i).letters[j].row, words.get(i).letters[j].col).equals("3W")) {
                    isTripleWord = true;
                }
                score[i] += currTileScore;
            }
            if (isTripleWord == true) {
                score[i] = score[i] * 3;
                isTripleWord = false;
            } else if (isDoubleWord == true) {
                score[i] = score[i] * 2;
                isDoubleWord = false;
            }
            totalScore += score[i];
        }
        int totalTilesUsed = 0;
        for (int i = 0; i < word.noOfLetters; i++) {
            if (word.letters[i].isMyOwnTile) {
                totalTilesUsed++;
            }
        }
        if (totalTilesUsed == 7) {
            totalScore += 50;
        }
    }
}
