package inferno.blue_frame.client.assets;


import inferno.blue_frame.client.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Texture {
    private BufferedImage image;
    private ResourceLocation location;

    public Texture(ResourceLocation location) {
        this.location = location;
        try {
            this.image = ImageIO.read(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(location.toString())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
