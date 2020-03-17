package inferno.blue_frame.client;

import inferno.blue_frame.client.assets.ResourceLocation;
import inferno.blue_frame.client.assets.Shader;
import inferno.blue_frame.client.assets.Texture;
import inferno.blue_frame.client.rendering.TileModel;
import inferno.blue_frame.client.utils.WindowReference;
import inferno.blue_frame.client.window.ClientWindow;
import inferno.blue_frame.common.utils.Matrix4f;

public class Main {
    private static ClientWindow windowClient;

    public static void main(String[] args){

        windowClient = new ClientWindow("Test 1", WindowReference.width, WindowReference.height){
            Texture brick;
            TileModel brickModel;

            @Override
            public void initTwo(){
                Shader.loadAll();
                Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
                Shader.TILE.setUniformMat4f("pr_matrix", pr_matrix);
                Shader.TILE.setUniform1i("tex", 1);

                brick = new Texture(new ResourceLocation("textures/tiles/brick.png"));
                brickModel = new TileModel(brick);
            }

            @Override
            public void update() {
                super.update();
                brickModel.update();
            }

            @Override
            public void render() {
                super.render();

                brickModel.render();
            }
        };
    }
}
