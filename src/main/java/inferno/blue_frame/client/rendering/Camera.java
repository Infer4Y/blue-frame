package inferno.blue_frame.client.rendering;

import org.joml.Vector2f;

public class Camera {
    private Vector2f pos = new Vector2f();

    public Camera(float x, float y) {
        this.pos.x = x;
        this.pos.y = y;
    }

    public void update(float x, float y){
        this.pos.x += x;
        this.pos.y += y;
    }

    public void moveTo(float x, float y){
        this.pos.x = x;
        this.pos.y = y;
    }

    public Vector2f getPos() {
        return pos;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }
}
