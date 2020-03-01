package inferno.blue_frame.client.rendering;

import java.util.HashMap;
import java.util.LinkedList;

public class Renderer {
    // Int : Layer = 3
    private HashMap<Integer, LinkedList<Renderable>> renderlist = new HashMap<>();

    public Camera camera = new Camera(150,150);


    public Renderer() {
        renderlist.put(0, new LinkedList<Renderable>());
        renderlist.put(1, new LinkedList<Renderable>());
        renderlist.put(2, new LinkedList<Renderable>());
    }

    public void render(){
    }


    public void add(int layer, Renderable renderable) {
        renderlist.get(layer).add(renderable);
    }

    public void clear() {
        renderlist.clear();
        renderlist.put(0, new LinkedList<Renderable>());
        renderlist.put(1, new LinkedList<Renderable>());
        renderlist.put(2, new LinkedList<Renderable>());
    }
}
