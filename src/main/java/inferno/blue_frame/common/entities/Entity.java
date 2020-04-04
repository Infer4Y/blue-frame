package inferno.blue_frame.common.entities;

public class Entity {
    private String
            name,
            registryName;

    public Entity(){}

    public void randomTick(){}

    public void update(){}

    public String getRegistryName() {
        return registryName;
    }

    public Entity setRegistryName(String registryName) {
        this.registryName = registryName;
        return this;
    }

    public String getName() {
        return name;
    }

    public Entity setName(String name) {
        this.name = name;
        return this;
    }
}
