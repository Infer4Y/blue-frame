package inferno.blue_frame.client.assets;

public class ResourceLocation {
    private String domain = "blue_frame";
    private String path;

    public ResourceLocation(String domain, String path) {
        this.domain = domain;
        this.path = path;
    }

    public ResourceLocation(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return ("assets/"+domain+"/"+path);
    }
}
