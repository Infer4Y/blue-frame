package inferno.blue_frame.client.rendering;

import inferno.blue_frame.client.assets.Textures;
import inferno.blue_frame.client.utils.ObjectType;
import inferno.blue_frame.common.init.Tiles;
import inferno.blue_frame.common.world.World;
import inferno.blue_frame.common.world.chunks.Chunk;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.LinkedList;

public class Renderer {
    // Int : Layer = 3

    private TileModel tileRenderer = new TileModel(null);

    public Camera camera = new Camera(16,16);


    public Renderer() {
    }



    public void render(World world){
        for (Chunk chunk : world.getChuncks()) {

            for (int y1 = 0; y1 < 16; y1++) {
                for (int x1 = 0; x1 < 16; x1++) {
                    tileRenderer.setTexture(Textures.getTexture(ObjectType.TILE, chunk.getTile(x1,y1).getRegistryName()).getFirst());
                    tileRenderer.setPos((chunk.getOffset().x+x1)*2-camera.getX(), (chunk.getOffset().y+y1)*2-camera.getY(), -1f);
                    tileRenderer.render();
                }
            }

        }
    }


    public void add(int layer, Renderable renderable) {
    }

    public void clear() {
    }
}
