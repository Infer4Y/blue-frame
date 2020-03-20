package inferno.blue_frame.client.utils;

import java.io.*;

public class FileUtils {

    public static String loadAsString(String file) {
        StringBuilder builder = new StringBuilder();

        try (InputStream in = FileUtils.class.getClassLoader().getResourceAsStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load a shader file!"
                    + System.lineSeparator() + ex.getMessage());
        }
        return builder.toString();
    }

}
