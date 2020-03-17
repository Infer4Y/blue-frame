package inferno.blue_frame.client;

import inferno.blue_frame.client.assets.ResourceLocation;
import inferno.blue_frame.client.assets.Shader;
import inferno.blue_frame.client.assets.Texture;
import inferno.blue_frame.client.rendering.TileModel;
import inferno.blue_frame.client.utils.WindowReference;
import inferno.blue_frame.client.window.ClientWindow;

public class Main {
    private static ClientWindow windowClient;

    public static void main(String[] args){

        windowClient = new ClientWindow("Test 1", WindowReference.width, WindowReference.height){
            Texture brick;
            TileModel brickModel;

            @Override
            public void initTwo(){
                Shader.loadAll();

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
