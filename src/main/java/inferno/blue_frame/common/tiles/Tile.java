package inferno.blue_frame.common.tiles;

public class Tile {
    private String name;
    private boolean
            solid = false,
            tickable = false;

    public Tile(String name) {
        this.name = name;
    }

    public Tile(String name, boolean solid, boolean tickable) {
        this.name = name;
        this.solid = solid;
        this.tickable = tickable;
    }

    public void tick(){}

    public String getName() {
        return name;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isTickable() {
        return tickable;
    }
}
