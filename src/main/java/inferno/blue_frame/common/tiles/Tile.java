package inferno.blue_frame.common.tiles;

public class Tile {
    private String
            name,
            registryName;

    private boolean
            solid = false,
            tickable = false;

    public Tile() {}

    public void tick(){}

    public String getName() {
        return name;
    }

    public Tile setSolid(boolean solid) {
        this.solid = solid;

        return this;
    }

    public Tile setTickable(boolean tickable) {
        this.tickable = tickable;

        return this;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isTickable() {
        return tickable;
    }

    public String getRegistryName() {
        return registryName;
    }

    public Tile setName(String name) {
        this.name = name;

        return this;
    }

    public Tile setRegistryName(String registryName) {
        this.registryName = registryName;

        return this;
    }
}
