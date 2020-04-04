package inferno.blue_frame.common.init;

import inferno.blue_frame.common.tiles.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Tiles {
    private static final ArrayList<Tile> TILE_LIST = new ArrayList<>();
    private static int length = 0x0000;

    public static final Tile AIR = new Tile().setName("Air").setRegistryName("air");
    public static final Tile GRASS = new Tile().setName("Grass").setRegistryName("grass").setSolid(true);
    public static final Tile DIRT = new Tile().setName("Dirt").setRegistryName("dirt").setSolid(true);
    public static final Tile STONE = new Tile().setName("Stone").setRegistryName("stone").setSolid(true);


    public static final Tile ERROR = new Tile().setName("placeholder").setRegistryName("placeholder").setSolid(true);

    public static void init() {
        register(AIR, GRASS, DIRT, STONE, ERROR);

    }

    private static void register(Tile... items){
        for (Tile item: items) {
            register(item);
        }
    }

    private static void register(Tile item){
        TILE_LIST.add(item);
        length+=0x0001;
    }

    private static void register(Tile item,int id){
        TILE_LIST.add(id, item);
    }

    public static Tile getTile(int id){
        return TILE_LIST.get(id);
    }

    public static int getLength() {
        return length;
    }
}
