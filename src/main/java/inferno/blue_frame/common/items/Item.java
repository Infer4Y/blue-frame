package inferno.blue_frame.common.items;

public class Item {
    private String
            name,
            registryName;

    public Item() { }

    public String getRegistryName() {
        return registryName;
    }

    public Item setRegistryName(String registryName) {
        this.registryName = registryName;

        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;

        return this;
    }
}
