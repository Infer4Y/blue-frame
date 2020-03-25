package inferno.blue_frame.common.world.chunks;


import inferno.blue_frame.common.tiles.Tile;

public class Chunk {
    private Tile[] tiles = new Tile[16*16];

    public void setTile(Tile tile, int x, int y) {
        tiles[x*16+y] = tile;
    }

    public Tile getTile(int x, int y){
        return tiles[x*16+y];
    }
}
