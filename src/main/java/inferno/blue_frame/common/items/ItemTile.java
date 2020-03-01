package inferno.blue_frame.common.items;

import inferno.blue_frame.common.tiles.Tile;

public class ItemTile extends Item {
    private Tile reference;

    public ItemTile(Tile tile) {
        super(tile.getName());
        this.reference = tile;
    }

    public Tile getTile() {
        return reference;
    }
}
