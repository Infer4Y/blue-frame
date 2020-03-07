package inferno.blue_launcher;

import inferno.blue_launcher.ui.UpdateInformation;
import inferno.blue_launcher.utils.Updater;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Launcher extends JFrame{

    //private JTextPane outText;
    private JFXPanel javafxBridge;
    private JButton launch;
    //private boolean result;

    public Launcher() {
        initComponents();
        setBackground(Color.black);

        try {
            if (!(new File("update/blue-frame-"+ Updater.getLatestVersion()+".jar").exists())){
                new UpdateInformation(Updater.getWhatsNew());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel pan1 = new JPanel();
        pan1.setLayout(new BorderLayout());

        JPanel pan2 = new JPanel();
        pan2.setLayout(new FlowLayout());

        //outText = new JTextPane();
        //JScrollPane sp = new JScrollPane();
        //sp.setViewportView(outText);

        javafxBridge = new JFXPanel();

        launch = new JButton("Launch App");
        launch.addActionListener(e -> launch());
        pan2.add(launch);

        JButton cancel = new JButton("Exit");
        cancel.addActionListener(e -> System.exit(0));
        pan2.add(cancel);
        //pan1.add(sp,BorderLayout.CENTER);
        pan1.add(javafxBridge,BorderLayout.CENTER);
        pan1.add(pan2,BorderLayout.SOUTH);

        add(pan1);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);

        Platform.runLater(() -> {
            WebView webView = new WebView();
            javafxBridge.setScene(new Scene(webView));
            webView.getEngine().load("https://infer4y.github.io/blue-frame/");
        });
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
