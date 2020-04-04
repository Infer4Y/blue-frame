package inferno.blue_frame.common.world;


import inferno.blue_frame.common.init.Tiles;
import inferno.blue_frame.common.world.chunks.Chunk;
import org.joml.Vector2f;

import java.util.ArrayList;

public class World {
    private ArrayList<Chunk> chunks = new ArrayList<>();

    public void generate(){
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                Chunk temp = new Chunk();
                temp.setOffset(new Vector2f(x, y));

                for (int y1 = 0; y1 < 16; y1++) {
                    for (int x1 = 0; x1 < 16; x1++) {
                        temp.setTile(Tiles.getTile(0),x1,y1);
                    }
                }

                chunks.add(temp);
            }

        }
    }

    public void update(){}

    public Chunk[] getChuncks() {
        return chunks.toArray(new Chunk[]{});
    }
}
