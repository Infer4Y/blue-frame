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
import static org.lwjgl.opengl.GL11.*;

public class Main {
    private static ClientWindow windowClient;
    private static Random random = new Random();
    public static int[][] map = new int[][]{
            { 0, 0, 1, 2, 3, 3, 2, 1, 0, 0},//1
            { 0, 1, 2, 3, 4, 4, 3, 2, 1, 0},//2
            { 1, 2, 3, 4, 3, 3, 4, 3, 2, 1},//3
            { 2, 3, 4, 3, 2, 2, 3, 4, 3, 2},//4
            { 3, 4, 3, 2, 1, 1, 2, 3, 4, 3},//5
            { 3, 4, 3, 2, 1, 1, 2, 3, 4, 3},//6
            { 2, 3, 4, 3, 2, 2, 3, 4, 3, 2},//7
            { 1, 2, 3, 4, 3, 3, 4, 3, 2, 1},//8
            { 0, 1, 2, 3, 4, 4, 3, 2, 1, 0},//9
            { 0, 0, 1, 2, 3, 3, 2, 1, 0, 0} //10
    };

    public static void main(String[] args){

        windowClient = new ClientWindow("LWJGL Test 1.2", WindowReference.width, WindowReference.height){
            Texture[] textures;

            @Override
            public void initTwo(){
                Shader.loadAll();
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                glOrtho(0, 640, 480, 0, 1, -1);
                glMatrixMode(GL_MODELVIEW);
                glEnable(GL_TEXTURE_2D);
                glEnable(GL_BLEND);
                glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
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
                        new Texture(new ResourceLocation("textures/tiles/place_holder.png"))
                };

            }

            public boolean isKeyPressed(int keyCode) {
                return glfwGetKey(this.getWindow(), keyCode) == GLFW_PRESS;
            }

            @Override
            public void update() {
                super.update();

                // Handle input
            }

            @Override
            public void render() {
                super.render();

                GL11.glColor3f(.5f, 1f,0f);

                // Render code
            }
        };
    }
}
