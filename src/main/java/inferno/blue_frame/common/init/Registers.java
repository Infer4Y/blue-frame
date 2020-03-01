package inferno.blue_frame.common.init;

import inferno.blue_frame.common.items.Item;
import inferno.blue_frame.common.tiles.Tile;

public class Registers {
    public static final Register<Item> ITEMS = new Register<>();
    public static final Register<Tile> TILES = new Register<>();

    public static void init(){
        //Items
        ITEMS.register(Items.COAL.getName() , Items.COAL);

        // Tiles
        TILES.register(Tiles.AIR.getName(), Tiles.AIR);
        TILES.register(Tiles.GRASS.getName(), Tiles.GRASS);
        TILES.register(Tiles.DIRT.getName(), Tiles.DIRT);
        TILES.register(Tiles.STONE.getName(), Tiles.STONE);
    }
}
