package inferno.blue_frame.common.world.chunks;


import inferno.blue_frame.common.tiles.Tile;
import org.joml.Vector2f;

public class Chunk {
    private Vector2f offset;

    private Tile[] tiles = new Tile[16*16];

    public void setTile(Tile tile, int x, int y) {
        tiles[x*16+y] = tile;
    }

    public Tile getTile(int x, int y){
        return tiles[x*16+y];
    }

    public Vector2f getOffset() {
        return offset;
    }

    public void setOffset(Vector2f offset) {
        this.offset = offset;
    }
}
