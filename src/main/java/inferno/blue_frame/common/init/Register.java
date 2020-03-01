package inferno.blue_frame.common.init;

import java.util.HashMap;

public class Register<T> {
    private HashMap<String, T> map = new HashMap<>();

    public void register(String name, T object){
        map.put(name, object);
    }

    public T getObject(String name){
        return map.get(name);
    }
}
