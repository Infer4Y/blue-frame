package inferno.blue_frame.client;

import inferno.blue_frame.client.assets.ResourceLocation;
import inferno.blue_frame.client.assets.Shader;
import inferno.blue_frame.client.assets.Texture;
import inferno.blue_frame.client.rendering.TileModel;
import inferno.blue_frame.client.utils.WindowReference;
import inferno.blue_frame.client.window.ClientWindow;
import inferno.blue_frame.common.utils.Matrix4f;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;

import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;

public class Main {
    private static ClientWindow windowClient;
    private static Random random = new Random();

    public static void main(String[] args){

        windowClient = new ClientWindow("LWJGL Test 1.2", WindowReference.width, WindowReference.height){
            Texture[] textures;
            TileModel tileRenderer;
            int textureId = 0;
            int updates = 0;

            @Override
            public void initTwo(){
                Shader.loadAll();
                Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f, 10.0f, -1.0f, 1.0f);
                Shader.TILE.setUniformMat4f("pr_matrix", pr_matrix);
                Shader.TILE.setUniform1i("tex", 1);

                textures = new Texture[]{
                        new Texture(new ResourceLocation("textures/tiles/brick.png")),
                        new Texture(new ResourceLocation("textures/tiles/diag_brick.png")),
                        new Texture(new ResourceLocation("textures/tiles/tile.png")),
                        new Texture(new ResourceLocation("textures/tiles/black_tile.png")),
                        new Texture(new ResourceLocation("textures/tiles/stone.png"))
                };

                tileRenderer = new TileModel(textures[0]);

                glfwSetKeyCallback(super.getWindow(), GLFWKeyCallback.create((window, key, scancode, action, mods) -> {
                    if ( key == GLFW_KEY_W ) {
                        tileRenderer.setRot(tileRenderer.getRot()+1.5f);
                    } else if ( key == GLFW_KEY_S ) {
                        tileRenderer.setRot(tileRenderer.getRot()-1.5f);
                    }

                    if ( key == GLFW_KEY_A ) {
                        if ( textureId == textures.length-1 ) {
                            textureId = 0;
                        } else {
                            textureId++;
                        }
                    } else if ( key == GLFW_KEY_D ) {
                        if ( textureId == 0 ) {
                            textureId = textures.length-1;
                        } else {
                            textureId--;
                        }
                    }

                }));
            }

            @Override
            public void update() {
                super.update();
                tileRenderer.update();

                if ( updates == 5 ){
                    //tileRenderer.setRot(tileRenderer.getRot()+.5f);
                    updates = 0;
                } else {
                    updates++;
                }
            }

            @Override
            public void render() {
                super.render();

                GL11.glColor3f(.5f, 1f,0f);

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        tileRenderer.setPos((i*2)-9,(j*2)-9,-1f);

                        tileRenderer.setTexture(textures[textureId]);

                        tileRenderer.render();


                        if ( textureId == textures.length-1 ) {
                            textureId = 0;
                        } else {
                            textureId++;
                        }
                    }

                    if ( textureId == textures.length-1 ) {
                        textureId = 0;
                    } else {
                        textureId++;
                    }
                }
            }
        };
    }
}
