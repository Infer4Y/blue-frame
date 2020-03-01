package inferno.blue_frame.client.assets;


import inferno.blue_frame.common.tiles.Tile;

import java.util.HashMap;
import java.util.LinkedList;

public class Textures {
    private static HashMap<String, LinkedList<Texture>> TEXTURE_MAP = new HashMap<>();

    public static void registerTexture(Tile tile) {
        LinkedList<Texture> temp = new LinkedList<>();
        temp.add(new Texture(new ResourceLocation(tile.getName())));
        TEXTURE_MAP.put(tile.getName(), temp);
    }

    public static LinkedList<Texture> getTexture(String name){
        return TEXTURE_MAP.getOrDefault(name, null);
    }
}
