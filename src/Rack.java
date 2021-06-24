import java.util.ArrayList;

public class Rack {
    Tile[] rackTiles;
    int noOfTiles;

    public Rack() {
        rackTiles = new Tile[7];
        noOfTiles = 0;
    }

    public void addTile(Tile tile) {
        if (noOfTiles <= 6 && tile != null) {
            rackTiles[noOfTiles] = tile;
            noOfTiles++;
        }
    }

    public void removeTile(Tile tile){
        if (noOfTiles >= 1){
            int index = -1;
            for (int i = 0; i < noOfTiles; i++){
                if (rackTiles[i].tileLetter == tile.tileLetter &&
                        rackTiles[i].blankTile == tile.blankTile){
                    index = i;
                }
            }
            if (index != -1){
                Tile temp = rackTiles[index];
                rackTiles[index] = rackTiles[noOfTiles - 1];
                rackTiles[noOfTiles - 1] = temp;
                noOfTiles--;
            }
        }
    }

    public void removeTile(int index){
        if (index >= 0 && index < noOfTiles){
            Tile temp = rackTiles[index];
            rackTiles[index] = rackTiles[noOfTiles - 1];
            rackTiles[noOfTiles - 1] = temp;
            noOfTiles--;
        }
    }

    public Tile get(int index){
        if (index >= 0 && index < noOfTiles){
            return rackTiles[index];
        }
        else{
            return new Tile("NULL");
        }
    }
    public void set(int index, Tile tile){
        if (index >= 0 && index < noOfTiles){
            rackTiles[index] = tile;
        }
    }

}
