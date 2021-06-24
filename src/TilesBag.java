
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bilal Javed
 */
public class TilesBag {

    Tile[] tileArray;
    int tilesCount;

    public TilesBag() {
        tilesCount = 100;
        tileArray = new Tile[tilesCount];
        for (int i = 0; i < 100; i++) {
            tileArray[i] = new Tile();
        }
        for (int i = 0; i < 12; i++) {
            tileArray[i].setTileLetter("E");
            tileArray[i].setTileScore(1);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 12; i < 21; i++) {
            tileArray[i].setTileLetter("A");
            tileArray[i].setTileScore(1);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 21; i < 23; i++) {
            tileArray[i].setTileLetter("B");
            tileArray[i].setTileScore(3);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 23; i < 25; i++) {
            tileArray[i].setTileLetter("C");
            tileArray[i].setTileScore(3);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 25; i < 29; i++) {
            tileArray[i].setTileLetter("D");
            tileArray[i].setTileScore(2);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 29; i < 31; i++) {
            tileArray[i].setTileLetter("F");
            tileArray[i].setTileScore(4);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 31; i < 34; i++) {
            tileArray[i].setTileLetter("G");
            tileArray[i].setTileScore(2);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 34; i < 36; i++) {
            tileArray[i].setTileLetter("H");
            tileArray[i].setTileScore(4);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 36; i < 45; i++) {
            tileArray[i].setTileLetter("I");
            tileArray[i].setTileScore(1);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 45; i < 46; i++) {
            tileArray[i].setTileLetter("J");
            tileArray[i].setTileScore(8);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 46; i < 47; i++) {
            tileArray[i].setTileLetter("K");
            tileArray[i].setTileScore(5);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 47; i < 51; i++) {
            tileArray[i].setTileLetter("L");
            tileArray[i].setTileScore(1);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 51; i < 53; i++) {
            tileArray[i].setTileLetter("M");
            tileArray[i].setTileScore(3);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 53; i < 59; i++) {
            tileArray[i].setTileLetter("N");
            tileArray[i].setTileScore(1);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 59; i < 67; i++) {
            tileArray[i].setTileLetter("O");
            tileArray[i].setTileScore(1);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 67; i < 69; i++) {
            tileArray[i].setTileLetter("P");
            tileArray[i].setTileScore(3);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 69; i < 70; i++) {
            tileArray[i].setTileLetter("Q");
            tileArray[i].setTileScore(10);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 70; i < 76; i++) {
            tileArray[i].setTileLetter("R");
            tileArray[i].setTileScore(1);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 76; i < 80; i++) {
            tileArray[i].setTileLetter("S");
            tileArray[i].setTileScore(1);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 80; i < 86; i++) {
            tileArray[i].setTileLetter("T");
            tileArray[i].setTileScore(1);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 86; i < 90; i++) {
            tileArray[i].setTileLetter("U");
            tileArray[i].setTileScore(1);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 90; i < 92; i++) {
            tileArray[i].setTileLetter("V");
            tileArray[i].setTileScore(4);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 92; i < 94; i++) {
            tileArray[i].setTileLetter("W");
            tileArray[i].setTileScore(4);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 94; i < 95; i++) {
            tileArray[i].setTileLetter("X");
            tileArray[i].setTileScore(8);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 95; i < 97; i++) {
            tileArray[i].setTileLetter("Y");
            tileArray[i].setTileScore(4);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 97; i < 98; i++) {
            tileArray[i].setTileLetter("Z");
            tileArray[i].setTileScore(10);
            tileArray[i].setBlankTile(false);
        }
        for (int i = 98; i < 100; i++) {
            tileArray[i].setTileLetter("BL");
            tileArray[i].setTileScore(0);
            tileArray[i].setBlankTile(true);
        }
        shuffleTiles();
    }

    public void shuffleTiles() {
        Random rand = new Random();
        for (int i = 0; i < tilesCount; i++) {
            int randomIndexToSwap = rand.nextInt(tilesCount);
            Tile temp = tileArray[randomIndexToSwap];
            tileArray[randomIndexToSwap] = tileArray[i];
            tileArray[i] = temp;
        }
    }

    public Tile extractRandomTile() {
        if (tilesCount > 0) {
            Random rand = new Random();
            int random = rand.nextInt(tilesCount);
            Tile temp = tileArray[random];
            tileArray[random] = tileArray[tilesCount - 1];
            tileArray[tilesCount - 1] = temp;
            tilesCount--;
            return tileArray[tilesCount];
        } else {
            return null;
        }
    }

    public void insertTiles(Tile tile) {
        if (tilesCount == 100) {
            return;
        } else {
            tileArray[tilesCount] = tile;
            tilesCount++;
        }
    }

    public void fillRack(ArrayList<Player> players, int pos) {
        for (int i = 0; i < 7; i++) {
                players.get(pos).rack.addTile(extractRandomTile());
        }
    }

    public String getRandomTileLetter() {
        Random rand = new Random();
        int random = rand.nextInt(tilesCount);
        return tileArray[random].getTileLetter();
    }

    public int getTilesCount() {
        return tilesCount;
    }

}
