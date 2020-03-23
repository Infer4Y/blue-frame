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
    public static int[][] map = new int[10][10];

    public static void main(String[] args){

        windowClient = new ClientWindow("LWJGL Test 1.5", WindowReference.width, WindowReference.height){
            Texture[] textures;
            TileModel tileRenderer;
            int updates = 0;
            boolean render;

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
                        new Texture(new ResourceLocation("textures/tiles/aluminum.png")),
                        new Texture(new ResourceLocation("textures/tiles/cobalt.png")),
                        new Texture(new ResourceLocation("textures/tiles/iron.png")),
                        new Texture(new ResourceLocation("textures/tiles/blue_glass.png")),
                        new Texture(new ResourceLocation("textures/tiles/magenta_glass.png")),
                        new Texture(new ResourceLocation("textures/tiles/purple_glass.png")),
                        new Texture(new ResourceLocation("textures/tiles/red_glass.png")),
                        new Texture(new ResourceLocation("textures/tiles/orange_glass.png")),
                        new Texture(new ResourceLocation("textures/tiles/yellow_glass.png")),
                        new Texture(new ResourceLocation("textures/tiles/green_glass.png")),
                        new Texture(new ResourceLocation("textures/tiles/grey_glass.png")),
                        new Texture(new ResourceLocation("textures/tiles/dark_grey_glass.png")),
                        new Texture(new ResourceLocation("textures/tiles/white_glass.png")),
                        new Texture(new ResourceLocation("textures/tiles/stone.png")),
                        new Texture(new ResourceLocation("textures/tiles/placeholder.png"))
                };

                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        if ( i+j > textures.length-1 ) {
                            map[i][j] = 0;
                        } else {
                            map[i][j] = i+j;
                        }
                    }
                }

                tileRenderer = new TileModel(textures[0]);
            }

            public boolean isKeyPressed(int keyCode) {
                return glfwGetKey(this.getWindow(), keyCode) == GLFW_PRESS;
            }

            @Override
            public void update() {
                super.update();
                tileRenderer.update();

                {
                    if (isKeyPressed(GLFW_KEY_W)) {
                        tileRenderer.setRot(tileRenderer.getRot() + 1.5f);
                    }
                    if (isKeyPressed(GLFW_KEY_S)) {
                        tileRenderer.setRot(tileRenderer.getRot() - 1.5f);
                    }
                    if (isKeyPressed(GLFW_KEY_A)) {
                    }
                    if (isKeyPressed(GLFW_KEY_D)) {
                    }
                }

                if ( updates == 10 ){
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map[i].length; j++) {
                            if ( map[i][j] == textures.length-1 ) {
                                map[i][j] = 0;
                            } else {
                                map[i][j]++;
                            }
                        }
                    }
                    updates = 0;
                } else {
                    updates++;
                }
            }

            @Override
            public void render() {
                super.render();

                GL11.glColor3f(.5f, 1f,0f);

                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        tileRenderer.setPos((i * 2) - 9, (j * 2) - 9, -1f);

                        tileRenderer.setTexture(textures[map[i][j]]);

                        tileRenderer.render();
                    }
                }
            }
        };
    }
}
