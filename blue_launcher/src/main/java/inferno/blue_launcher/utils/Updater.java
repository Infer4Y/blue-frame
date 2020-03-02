package inferno.blue_launcher.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Updater {
    private final static String versionURL = "https://infer4y.github.io/blue-frame/updater/version.html";
    private final static String historyURL = "https://infer4y.github.io/blue-frame/updater/history.html";
    public static String getLatestVersion() throws Exception
    {
        String data = getData(versionURL);
        return data.substring(data.indexOf("[version]")+9,data.indexOf("[/version]"));
    }

    public static String getWhatsNew() throws Exception
    {
        String data = getData(historyURL);
        return data.substring(data.indexOf("[history]")+9,data.indexOf("[/history]"));
    }

    private static String getData(String address) throws IOException {
        URL url = new URL(address);

        InputStream html = url.openStream();

        int c = 0;
        StringBuilder buffer = new StringBuilder();

        while(c != -1) {
            c = html.read();

            buffer.append((char)c);
        }
        return buffer.toString();
    }
}
