package inferno.blue_frame.client.rendering;

import inferno.blue_frame.client.assets.Shader;
import inferno.blue_frame.client.assets.Texture;
import inferno.blue_frame.common.utils.Matrix4f;
import org.joml.Vector3f;

public class EntityItemModel {
    private float SIZE = 1.0f;
    private VertexArray mesh;
    private Texture texture;

    private Vector3f position = new Vector3f(0f,0f,-1f);
    private float rot = 0.0f;
    private float delta = 0.0f;

    public EntityItemModel(Texture texture) {

        float[] vertices = new float[] {
                -SIZE / 2.0f, -SIZE / 2.0f, 0.2f,
                -SIZE / 2.0f,  SIZE / 2.0f, 0.2f,
                SIZE / 2.0f,  SIZE / 2.0f, 0.2f,
                SIZE / 2.0f, -SIZE / 2.0f, 0.2f
        };

        byte[] indices = new byte[] {
                0, 1, 2,
                2, 3, 0
        };

        float[] tcs = new float[] {
                0, 1,
                0, 0,
                1, 0,
                1, 1
        };

        mesh = new VertexArray(vertices, indices, tcs);
        this.texture = texture;
    }

    public void update(){
        position.y -= delta;
    }

    public void render() {
        Shader.TILE.enable();
        Shader.TILE.setUniformMat4f("ml_matrix", Matrix4f.translate(position).multiply(Matrix4f.rotate(rot)));
        texture.bind();
        mesh.render();
        Shader.TILE.disable();
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setPos(Vector3f pos) {
        this.position = pos;
    }

    public void setPos(float x, float y, float z) {
        this.position = new Vector3f(x, y, z);
    }

    public float getRot() {
        return rot;
    }

    public void setRot(float rot) {
        this.rot = rot;
    }
}

