package inferno.blue_frame.client;

import inferno.blue_frame.client.assets.ResourceLocation;
import inferno.blue_frame.client.assets.Shader;
import inferno.blue_frame.client.assets.Texture;
import inferno.blue_frame.client.assets.Textures;
import inferno.blue_frame.client.audio.AudioMaster;
import inferno.blue_frame.client.audio.Source;
import inferno.blue_frame.client.rendering.Renderer;
import inferno.blue_frame.client.rendering.TileModel;
import inferno.blue_frame.client.utils.WindowReference;
import inferno.blue_frame.client.window.ClientWindow;
import inferno.blue_frame.common.Game;
import inferno.blue_frame.common.utils.Matrix4f;
import inferno.blue_frame.common.world.chunks.Chunk;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;

import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;

public class Main {
    private static ClientWindow windowClient;
    private static Renderer renderer;


    public static void main(String[] args){
        Game.init();

        windowClient = new ClientWindow("LWJGL Test 1.5", WindowReference.width, WindowReference.height){
            Source source;

            @Override
            public void initTwo(){
                Game.init();

                Textures.init();

                AudioMaster.init();
                AudioMaster.setListenerData();
                source  = new Source();

                Shader.loadAll();
                Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f, 10.0f, -1.0f, 1.0f);
                Shader.TILE.setUniformMat4f("pr_matrix", pr_matrix);
                Shader.TILE.setUniform1i("tex", 1);


                renderer = new Renderer();
            }

            public boolean isKeyPressed(int keyCode) {
                return glfwGetKey(this.getWindow(), keyCode) == GLFW_PRESS;
            }

            @Override
            public void update() {
                super.update();
                Game.update();

                {
                    if (isKeyPressed(GLFW_KEY_W)) {
                        renderer.camera.update(0, 0.1f);
                    }
                    if (isKeyPressed(GLFW_KEY_S)) {
                        renderer.camera.update(0, -0.1f);
                    }
                    if (isKeyPressed(GLFW_KEY_A)) {
                        renderer.camera.update(-0.1f, 0);
                    }
                    if (isKeyPressed(GLFW_KEY_D)) {
                        renderer.camera.update(0.1f, 0);
                    }
                }
            }

            @Override
            public void render() {
                super.render();
                renderer.render(Game.world);
            }

            @Override
            public void clean() {
                super.clean();
                source.delete();
                AudioMaster.cleanUp();
                Shader.deleteAll();
            }
        };


    }
}
