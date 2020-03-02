package inferno.blue_launcher.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateInformation extends JFrame{
    private JEditorPane infoPane;

    public UpdateInformation(String info) {
        initComponents();
        infoPane.setText(info);
    }

    private void initComponents() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("New Update Found");
        JPanel pan1 = new JPanel();
        pan1.setLayout(new BorderLayout());

        JPanel pan2 = new JPanel();
        pan2.setLayout(new FlowLayout());

        infoPane = new JEditorPane();
        infoPane.setContentType("text/html");

        JScrollPane scp = new JScrollPane();
        scp.setViewportView(infoPane);

        JButton ok = new JButton("Update");
        ok.addActionListener(e -> update());

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> UpdateInformation.this.dispose());
        pan2.add(ok);
        pan2.add(cancel);
        pan1.add(pan2, BorderLayout.SOUTH);
        pan1.add(scp, BorderLayout.CENTER);
        this.add(pan1);
        pack();
        show();
        this.setSize(300, 200);
    }

    private void update() {
        String[] run = {"java","-jar","updater/update.jar"};
        try {
            Runtime.getRuntime().exec(run);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }
}
