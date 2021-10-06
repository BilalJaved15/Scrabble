
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bilal Javed
 */
public class PlacementErrorGUI {

    JFrame fr;
    JLabel errorIcon;
    JButton btnOK;
    JLabel errorMessage;
    PlacementErrorGUIButtonHandler hnd;

    public PlacementErrorGUI(String errorMessage, MatchRoundGUI refg) {
        hnd = new PlacementErrorGUIButtonHandler(this, refg);

        fr = new JFrame("Placement Error");
        ImageIcon imgico = new ImageIcon("assets/a-6.png");
        fr.setIconImage(imgico.getImage());
        fr.setLayout(null);

        fr.getContentPane().setBackground(new Color(68, 53, 34));

        errorIcon = new JLabel();
        errorIcon.setIcon(new ImageIcon("assets/error_icon.png"));
        errorIcon.setLayout(new BorderLayout());
        errorIcon.setSize(64, 64);
        errorIcon.setLocation(210, 50);

        this.errorMessage = new JLabel("<html><center>" + errorMessage + "</center></html>", SwingConstants.CENTER);
        this.errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        this.errorMessage.setVerticalAlignment(SwingConstants.CENTER);
        this.errorMessage.setForeground(new Color(247, 224, 206));
        this.errorMessage.setFont(new Font("Verdana", Font.ITALIC, 15));
        this.errorMessage.setSize(470, 200);
        this.errorMessage.setLocation(10, 65);

        Border emptyBorder = BorderFactory.createEmptyBorder();

        btnOK = new JButton("OK");
        btnOK.setFont(new Font("Verdana", Font.BOLD, 20));
        btnOK.setBackground(new Color(68, 53, 34));
        btnOK.setForeground(new Color(247, 224, 206));
        btnOK.setBorder(emptyBorder);
        btnOK.setLocation(205, 210);
        btnOK.setSize(80, 50);
        btnOK.setFocusPainted(false);
        btnOK.setBorderPainted(false);
        btnOK.addActionListener(hnd);

        fr.add(btnOK);
        fr.add(this.errorMessage);
        fr.add(errorIcon);

        fr.setSize(500, 320);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
