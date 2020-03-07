package inferno.blue_launcher;

import inferno.blue_launcher.utils.Updater;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Launcher extends JFrame{

    private JTextPane outText;
    private JButton launch;
    private boolean result;

    public Launcher() {
        initComponents();
        Document domTree = null;
        try {
            domTree = Jsoup.connect("http://infer4y.github.io/blue-frame/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        outText.setEditable(false);
        outText.setContentType( "text/html" );
        try {
            outText.setPage(domTree.html());
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBackground(Color.black);
    }
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel pan1 = new JPanel();
        pan1.setLayout(new BorderLayout());

        JPanel pan2 = new JPanel();
        pan2.setLayout(new FlowLayout());

        outText = new JTextPane();
        JScrollPane sp = new JScrollPane();
        sp.setViewportView(outText);

        launch = new JButton("Launch App");
        launch.addActionListener(e -> launch());
        pan2.add(launch);

        JButton cancel = new JButton("Exit");
        cancel.addActionListener(e -> System.exit(0));
        pan2.add(cancel);
        pan1.add(sp,BorderLayout.CENTER);
        pan1.add(pan2,BorderLayout.SOUTH);

        add(pan1);
        pack();
        this.setSize(500, 400);
    }

    public static String sendGetRequest(String endpoint){
        String result = null;
        //Makes sure the endpoint is valid
        if (endpoint.startsWith("http://") || endpoint.startsWith("https://")){
            try{
                //Gets a connection to the web-page
                URL url = new URL(endpoint);
                URLConnection conn = url.openConnection ();
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                //Reads lines from the web-page and appends them to the StringBuilder
                String line;
                while ((line = rd.readLine()) != null){
                    sb.append(line);
                }
                //Close the connection
                rd.close();
                //Return the contents of the StringBuilder
                result = sb.toString();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    private void launch() {
        try {
            String[] run = {"java","-jar","update/blue-frame-"+ Updater.getLatestVersion()+".jar"};
            Runtime.getRuntime().exec(run);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Launcher().setVisible(true));
    }
}
