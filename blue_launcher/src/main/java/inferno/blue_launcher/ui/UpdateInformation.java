package inferno.blue_launcher.ui;

import inferno.blue_launcher.Launcher;

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
        pan1.setBackground(Color.darkGray);

        JPanel pan2 = new JPanel();
        pan2.setLayout(new FlowLayout());
        pan2.setBackground(Color.darkGray);

        infoPane = new JEditorPane();
        infoPane.setEditable(false);
        infoPane.setForeground(new Color(0x708090));
        infoPane.setBackground(Color.darkGray);
        infoPane.setFont(infoPane.getFont().deriveFont(Font.BOLD, 14f));

        JScrollPane scp = new JScrollPane();
        scp.setViewportView(infoPane);

        JButton ok = Launcher.createSimpleButton("Update");
        ok.addActionListener(e -> update());

        JButton cancel = Launcher.createSimpleButton("Cancel");
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
