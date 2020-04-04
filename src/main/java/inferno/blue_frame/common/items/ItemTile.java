package inferno.blue_frame.common.items;

import inferno.blue_frame.common.tiles.Tile;

public class ItemTile extends Item {
    private Tile reference;

    public ItemTile(Tile tile) {
        this.setName(tile.getName());
        this.setRegistryName(tile.getRegistryName());
        this.reference = tile;
    }

    public Tile getTile() {
        return reference;
    }
}
