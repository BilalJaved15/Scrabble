public class TileOnBoard {

    Tile tile;
    int row;
    int col;
    boolean isMyOwnTile;

    public TileOnBoard(){

    }

    public TileOnBoard(Tile tile, int row, int col, boolean isMyOwnTile){
        this.tile = new Tile(tile.tileLetter);
        this.row = row;
        this.col = col;
        this.isMyOwnTile = isMyOwnTile;
    }

    public Tile getTile() {
        return tile;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isMyOwnTile() {
        return isMyOwnTile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setMyOwnTile(boolean myOwnTile) {
        isMyOwnTile = myOwnTile;
    }
}
