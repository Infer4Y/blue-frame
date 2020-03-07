package inferno.blue_update;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Main extends JFrame{
    private final String root = "update/";

    private JTextArea outText;
    private JButton launch;
    private boolean result;

    public Main() {
        initComponents();
        outText.setText("Contacting Download Server...");
        download();
    }
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel pan1 = new JPanel();
        pan1.setLayout(new BorderLayout());

        JPanel pan2 = new JPanel();
        pan2.setLayout(new FlowLayout());

        outText = new JTextArea();
        JScrollPane sp = new JScrollPane();
        sp.setViewportView(outText);

        launch = new JButton("Launch App");
        launch.setEnabled(false);
        launch.addActionListener(e -> launch());
        pan2.add(launch);
        JButton cancel = new JButton("Cancel Update");
        cancel.addActionListener(e -> System.exit(0));
        pan2.add(cancel);
        pan1.add(sp,BorderLayout.CENTER);
        pan1.add(pan2,BorderLayout.SOUTH);

        add(pan1);
        pack();
        this.setSize(500, 400);
    }

    private void download() {
        Thread worker = new Thread(() -> {
            try {
                downloadFile(getDownloadLinkFromHost());
                unzip();
                copyFiles(new File(root), new File("").getAbsolutePath());
                cleanup();
                launch.setEnabled(true);
                outText.setText(outText.getText() + "\nUpdate Finished!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occured while preforming update!");
            }
        });
        worker.start();
    }
    private void launch() {
        try {
            String[] run = {"java","-jar","blue-frame-"+ Updater.getLatestVersion()+".jar"};
            Runtime.getRuntime().exec(run);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

    private void cleanup() {
        outText.setText(outText.getText()+"\nPreforming clean up...");
        File f = new File("update.zip");
        result = f.delete();
        remove(new File(root));
        result = new File(root).delete();
    }

    private void remove(File f) {
        File[]files = f.listFiles();
        assert files != null;
        for(File ff:files) {
            if(ff.isDirectory()) {
                remove(ff);
                ff.delete();
            } else {
                ff.delete();
            }
        }
    }
    private void copyFiles(File f,String dir) throws IOException {
        File[]files = f.listFiles();
        assert files != null;
        for(File ff:files) {
            if(ff.isDirectory()){
                File f1 = new File(dir+"/"+ff.getName());
                result = f1.createNewFile();
                copyFiles(ff,dir+"/"+ff.getName());
            } else {

                copy(ff.getAbsolutePath(),dir+"/"+ff.getName());
            }

        }
    }
    private void copy(String srFile, String dtFile) throws IOException{
        File f1 = new File(srFile);
        File f2 = new File(dtFile);

        InputStream in = new FileInputStream(f1);
        OutputStream out = new FileOutputStream(f2);

        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0){
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    private void unzip() throws IOException {
        int BUFFER = 2048;
        BufferedOutputStream dest;
        BufferedInputStream is;
        ZipEntry entry;
        ZipFile zipfile = new ZipFile("update.zip");
        Enumeration e = zipfile.entries();
        result = (new File(root)).mkdirs();
        while(e.hasMoreElements()) {
            entry = (ZipEntry) e.nextElement();
            outText.setText(outText.getText()+"\nExtracting: " +entry);
            if(entry.isDirectory()) { result = (new File(root + entry.getName())).mkdirs();
            } else {
                result = (new File(root+entry.getName())).createNewFile();
                is = new BufferedInputStream(zipfile.getInputStream(entry));
                int count;
                byte[] data = new byte[BUFFER];
                FileOutputStream fos = new FileOutputStream(root+entry.getName());
                dest = new BufferedOutputStream(fos, BUFFER);
                while ((count = is.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
                dest.close();
                is.close();
            }
        }

    }

    private void downloadFile(String link) throws IOException {
        URL url = new URL(link);
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        long max = conn.getContentLength();
        outText.setText(outText.getText()+"\n"+"Downloading file...\nUpdate Size(compressed): "+max+" Bytes");
        BufferedOutputStream fOut = new BufferedOutputStream(new FileOutputStream(new File("update.zip")));
        byte[] buffer = new byte[32 * 1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            fOut.write(buffer, 0, bytesRead);
        }
        fOut.flush();
        fOut.close();
        is.close();
        outText.setText(outText.getText()+"\nDownload Complete!");

    }
    private String getDownloadLinkFromHost() throws IOException {
        String path = "https://infer4y.github.io/blue-frame/updater/url.html";
        URL url = new URL(path);

        InputStream html = url.openStream();

        int c = 0;
        StringBuilder buffer = new StringBuilder();

        while(c != -1) {
            c = html.read();
            buffer.append((char)c);

        }
        return buffer.substring(buffer.indexOf("[url]")+5,buffer.indexOf("[/url]"));
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}
