/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Bilal Javed
 */
public class Tile {

    String tileLetter;
    int tileScore;
    boolean blankTile;

    public Tile() {

    }

    public Tile(String tileLetter) {
        this.tileLetter = tileLetter;
        if (tileLetter.equals("BL")){
            blankTile = true;
        }
        else{
            blankTile = false;
        }
        this.tileScore = getTileScoreByLetter(tileLetter);
    }

    public String getTileLetter() {
        return tileLetter;
    }

    public int getTileScore() {
        return tileScore;
    }

    public boolean isBlankTile() {
        return blankTile;
    }

    public void setTileLetter(String tileLetter) {
        this.tileLetter = tileLetter;
        this.tileScore = getTileScoreByLetter(tileLetter);
    }

    public void setTileScore(int tileScore) {
        this.tileScore = tileScore;
    }

    public void setBlankTile(boolean blankTile) {
        this.blankTile = blankTile;
    }

    public static int getTileScoreByLetter(String letter) {
        if (letter.equals("BL")) {
            return 0;
        } else if (letter.equals("E") || letter.equals("A") || letter.equals("I")
                || letter.equals("S") || letter.equals("T") || letter.equals("R")
                || letter.equals("O") || letter.equals("N") || letter.equals("U")
                || letter.equals("L")) {
            return 1;
        } else if (letter.equals("D") || letter.equals("G")) {
            return 2;
        } else if (letter.equals("B") || letter.equals("C") || letter.equals("M")
                || letter.equals("P")) {
            return 3;
        } else if (letter.equals("F") || letter.equals("H") || letter.equals("V")
                || letter.equals("W") || letter.equals("Y")) {
            return 4;
        } else if (letter.equals("K")) {
            return 5;
        } else if (letter.equals("J") || letter.equals("X")) {
            return 8;
        } else {
            return 10;
        }
    }
}
