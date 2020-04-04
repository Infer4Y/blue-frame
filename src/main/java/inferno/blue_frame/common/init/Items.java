package inferno.blue_frame.common.init;

import inferno.blue_frame.common.items.Item;
import inferno.blue_frame.common.items.ItemTile;
import inferno.blue_frame.common.tiles.Tile;

import java.util.ArrayList;

public class Items {

    private static final ArrayList<Item> ITEM_LIST = new ArrayList<>();
    private static int length = 0x0000;

    public static final Item COAL = new Item().setName("Coal").setRegistryName("coal");

    public static void init() {
    }

    private static void register(Tile... items){
        for (Tile item: items) {
            register(item);
        }
    }

    private static void register(Tile item){
        ITEM_LIST.add(new ItemTile(item));
        length+=0x0001;
    }

    private static void register(Item... items){
        for (Item item: items) {
            register(item);
        }
    }

    private static void register(Item item){
        ITEM_LIST.add(item);
        length+=0x0001;
    }

    private static void register(Item item,int id){
        ITEM_LIST.add(id, item);
    }

    public static Item getItem(int id){
        return ITEM_LIST.get(id);
    }

    public static int getLength() {
        return length;
    }
}
