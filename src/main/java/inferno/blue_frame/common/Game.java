package inferno.blue_frame.common;

import inferno.blue_frame.common.init.Items;
import inferno.blue_frame.common.init.Tiles;
import inferno.blue_frame.common.world.World;

public class Game {
    public static final String version = "1.0-SNAPSHOT";

    public static World world = new World();

    public static void init(){
        Tiles.init();
        Items.init();

        world.generate();
    }

    public static void update(){
        world.update();
    }

}
