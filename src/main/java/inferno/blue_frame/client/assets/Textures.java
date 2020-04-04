package inferno.blue_frame.client.assets;


import inferno.blue_frame.client.utils.ObjectType;
import inferno.blue_frame.common.init.Items;
import inferno.blue_frame.common.init.Tiles;
import inferno.blue_frame.common.items.Item;
import inferno.blue_frame.common.items.ItemTile;
import inferno.blue_frame.common.tiles.Tile;

import java.util.HashMap;
import java.util.LinkedList;

public class Textures {
    private static HashMap< ObjectType , HashMap<String, LinkedList<Texture>>> TEXTURE_MAP = new HashMap<>();
    private static Texture placeholder;
    private static LinkedList<Texture> placeholderList = new LinkedList<>();


    public static void init(){
        TEXTURE_MAP.put(ObjectType.TILE, new HashMap<>());
        TEXTURE_MAP.put(ObjectType.ITEM, new HashMap<>());


        placeholder = new Texture(new ResourceLocation("textures/tiles/placeholder.png"));
        placeholderList.add(placeholder);

        for (int i = 0; i < Tiles.getLength(); i++) {
            registerTexture(Tiles.getTile(i));
        }

        for (int i = 0; i < Items.getLength(); i++) {
            registerTexture(Items.getItem(i));
        }
    }


    public static void registerTexture(Tile tile) {
        LinkedList<Texture> temp = new LinkedList<>();
        temp.add(new Texture(new ResourceLocation("textures/tiles/<NAME>.png".replace("<NAME>",tile.getRegistryName()))));
        TEXTURE_MAP.get(ObjectType.TILE).put(tile.getName(), temp);
    }

    public static void registerTexture(Item tile) {
        LinkedList<Texture> temp = new LinkedList<>();
        if ( tile instanceof ItemTile) return;
        temp.add(new Texture(new ResourceLocation("textures/items/<NAME>.png".replace("<NAME>",tile.getRegistryName()))));
        TEXTURE_MAP.get(ObjectType.ITEM).put(tile.getName(), temp);
    }

    public static LinkedList<Texture> getTexture(ObjectType type, String name){
        return TEXTURE_MAP.get(type).getOrDefault(name, placeholderList);
    }
}
