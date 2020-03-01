package inferno.blue_launcher;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Updater {
    private final static String versionURL = "";
    private final static String historyURL = "";
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

        InputStream html = null;

        html = url.openStream();

        int c = 0;
        StringBuffer buffer = new StringBuffer("");

        while(c != -1) {
            c = html.read();

            buffer.append((char)c);
        }
        return buffer.toString();
    }
}
