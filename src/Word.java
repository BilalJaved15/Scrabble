/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bilal Javed
 */
public class Word {

    TileOnBoard[] letters;
    String wordOrientation;
    int noOfLetters;

    public Word() {
        letters = new TileOnBoard[15];
        for (int i = 0; i < 15; i++){
            letters[i] = new TileOnBoard();
        }
        noOfLetters = 0;
        wordOrientation = "NULL";
    }

    public boolean addLetter(int row, int col, String letter, boolean isMyOwnTile) {
        Tile tempTile = new Tile(letter);
        TileOnBoard tempTileOnBoard = new TileOnBoard(tempTile, row, col, isMyOwnTile);
        if (noOfLetters == 0) {
            this.letters[noOfLetters] = tempTileOnBoard;
            noOfLetters++;
            return true;
        } else if (noOfLetters == 1) {
            if (this.letters[0].row == row) {
                if (!searchCol(col - 1) && !searchCol(col + 1)) {
                    return false;
                }
                wordOrientation = "HORIZONTAL";
                this.letters[noOfLetters] = tempTileOnBoard;
                noOfLetters++;
                return true;
            } else if (this.letters[0].col == col) {
                if (!searchRow(row - 1) && !searchRow(row + 1)) {
                    return false;
                }
                wordOrientation = "VERTICAL";
                this.letters[noOfLetters] = tempTileOnBoard;
                noOfLetters++;
                return true;
            } else {
                return false;
            }
        }
        if (wordOrientation == "HORIZONTAL") {
            if (this.letters[noOfLetters - 1].row == row && (searchCol(col - 1) || searchCol(col + 1))) {
                this.letters[noOfLetters] = tempTileOnBoard;
                noOfLetters++;
                return true;
            } else {
                return false;
            }
        } else {
            if (this.letters[noOfLetters - 1].col == col && (searchRow(row - 1) || searchRow(row + 1))) {
                this.letters[noOfLetters] = tempTileOnBoard;
                noOfLetters++;
                return true;
            } else {
                return false;
            }
        }
    }

    public void removeLetter(int row, int col) {
        int index = -1;
        for (int i = 0; i < noOfLetters; i++) {
            if (this.letters[i].row == row && this.letters[i].col == col) {
                index = i;
            }
        }
        if (index != -1){
            TileOnBoard tempTile = letters[index];
            letters[index] = letters[noOfLetters - 1];
            letters[noOfLetters - 1] = tempTile;
            noOfLetters--;
        }
    }

    public void sortWord() {
        for (int i = 0; i < noOfLetters; i++) {
            int index = i;
            int smallestRow = letters[i].row;
            int smallestCol = letters[i].col;
            for (int j = i; j < noOfLetters; j++) {
                if (smallestRow >= letters[j].row && smallestCol >= letters[j].col) {
                    index = j;
                    smallestRow = letters[j].row;
                    smallestCol = letters[j].col;
                }
            }
            TileOnBoard temp = letters[index];
            letters[index] = letters[i];
            letters[i] = temp;
        }
    }

    public boolean searchRow(int rowNo) {
        for (int i = 0; i < noOfLetters; i++) {
            if (letters[i].row == rowNo) {
                return true;
            }
        }
        return false;
    }

    public boolean searchCol(int colNo) {
        for (int i = 0; i < noOfLetters; i++) {
            if (letters[i].col == colNo) {
                return true;
            }
        }
        return false;
    }
    
    public int getIndexOf(int row, int col){
        for(int i = 0; i < noOfLetters; i++){
            if(this.letters[i].row == row && letters[i].col == col){
                return i;
            }
        }
        return -1;
    }

    public String getWordOrientation() {
        return wordOrientation;
    }
}
