
import java.awt.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bilal Javed
 */
public class PlayerSelectorGUI {

    JFrame fr;
    JButton btnAddSub3;
    JButton btnAddSub4;
    JButton btnSwitchPlayer1;
    JButton btnSwitchPlayer2;
    JButton btnSwitchPlayer3;
    JButton btnSwitchPlayer4;
    JButton btnContinue;
    JTextField player1Name;
    JTextField player2Name;
    JTextField player3Name;
    JTextField player4Name;
    PlayerSelectorGUIButtonHandler hnd;
    JLabel title;
    JLabel desc;

    public PlayerSelectorGUI() {
        initGUI();
    }

    public void initGUI() {
        hnd = new PlayerSelectorGUIButtonHandler(this);

        fr = new JFrame("Scrabble");
        ImageIcon imgico = new ImageIcon("a-6.png");
        fr.setIconImage(imgico.getImage());
        fr.setLayout(null);
        fr.getContentPane().setBackground(new Color(68, 53, 34));

        title = new JLabel("PLAYER SELECTOR", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 30));
        title.setForeground(new Color(247, 224, 206));
        title.setBackground(new Color(68, 53, 34));
        title.setLocation(40, 0);
        title.setSize(400, 100);

        desc = new JLabel("<html><center>" + "Enter player names. Player 3 and 4 are optional." +
                " Use +/- button to add or remove them," +
                " toggle the human/computer button to change type of player." + "</center></html>", SwingConstants.CENTER);
        desc.setHorizontalAlignment(SwingConstants.CENTER);
        desc.setVerticalAlignment(SwingConstants.CENTER);
        desc.setForeground(new Color(247, 224, 206));
        desc.setFont(new Font("Verdana", Font.ITALIC, 15));
        desc.setSize(470, 200);
        desc.setLocation(10, 10);

        player1Name = new JTextField();
        player1Name.setBackground(new Color(247, 224, 206));
        player1Name.setSize(200, 40);
        player1Name.setLocation(50, 160);
        player1Name.setFont(new Font("Verdana", Font.BOLD, 15));

        btnSwitchPlayer1 = new JButton("HUMAN");
        btnSwitchPlayer1.setActionCommand("Player1Human");
        btnSwitchPlayer1.setBackground(new Color(247, 224, 206));
        btnSwitchPlayer1.setSize(120, 40);
        btnSwitchPlayer1.setLocation(260, 160);
        btnSwitchPlayer1.setFocusPainted(false);
        btnSwitchPlayer1.setBorderPainted(false);
        btnSwitchPlayer1.setFont(new Font("Verdana", Font.BOLD, 12));
        btnSwitchPlayer1.addActionListener(hnd);
        
        player2Name = new JTextField();
        player2Name.setBackground(new Color(247, 224, 206));
        player2Name.setSize(200, 40);
        player2Name.setLocation(50, 210);
        player2Name.setFont(new Font("Verdana", Font.BOLD, 15));

        btnSwitchPlayer2 = new JButton("HUMAN");
        btnSwitchPlayer2.setActionCommand("Player2Human");
        btnSwitchPlayer2.setBackground(new Color(247, 224, 206));
        btnSwitchPlayer2.setSize(120, 40);
        btnSwitchPlayer2.setLocation(260, 210);
        btnSwitchPlayer2.setFocusPainted(false);
        btnSwitchPlayer2.setBorderPainted(false);
        btnSwitchPlayer2.setFont(new Font("Verdana", Font.BOLD, 12));
        btnSwitchPlayer2.addActionListener(hnd);
        
        player3Name = new JTextField();
        player3Name.setEditable(false);
        player3Name.setBackground(new Color(247, 224, 206));
        player3Name.setSize(200, 40);
        player3Name.setLocation(50, 260);
        player3Name.setFont(new Font("Verdana", Font.BOLD, 15));

        btnSwitchPlayer3 = new JButton("HUMAN");
        btnSwitchPlayer3.setActionCommand("Player3Human");
        btnSwitchPlayer3.setBackground(new Color(247, 224, 206));
        btnSwitchPlayer3.setSize(120, 40);
        btnSwitchPlayer3.setLocation(260, 260);
        btnSwitchPlayer3.setFocusPainted(false);
        btnSwitchPlayer3.setBorderPainted(false);
        btnSwitchPlayer3.setFont(new Font("Verdana", Font.BOLD, 12));
        btnSwitchPlayer3.setEnabled(false);
        btnSwitchPlayer3.addActionListener(hnd);
        
        btnAddSub3 = new JButton("+");
        btnAddSub3.setActionCommand("Player3+");
        btnAddSub3.setBackground(new Color(247, 224, 206));
        btnAddSub3.setSize(50, 40);
        btnAddSub3.setLocation(390, 260);
        btnAddSub3.setFocusPainted(false);
        btnAddSub3.setBorderPainted(false);
        btnAddSub3.setFont(new Font("Verdana", Font.BOLD, 12));
        btnAddSub3.addActionListener(hnd);

        player4Name = new JTextField();
        player4Name.setEditable(false);
        player4Name.setBackground(new Color(247, 224, 206));
        player4Name.setSize(200, 40);
        player4Name.setLocation(50, 310);
        player4Name.setFont(new Font("Verdana", Font.BOLD, 15));

        btnSwitchPlayer4 = new JButton("HUMAN");
        btnSwitchPlayer4.setActionCommand("Player4Human");
        btnSwitchPlayer4.setBackground(new Color(247, 224, 206));
        btnSwitchPlayer4.setSize(120, 40);
        btnSwitchPlayer4.setLocation(260, 310);
        btnSwitchPlayer4.setFocusPainted(false);
        btnSwitchPlayer4.setBorderPainted(false);
        btnSwitchPlayer4.setFont(new Font("Verdana", Font.BOLD, 12));
        btnSwitchPlayer4.setEnabled(false);
        btnSwitchPlayer4.addActionListener(hnd);
        
        btnAddSub4 = new JButton("+");
        btnAddSub4.setActionCommand("Player4+");
        btnAddSub4.setBackground(new Color(247, 224, 206));
        btnAddSub4.setSize(50, 40);
        btnAddSub4.setLocation(390, 310);
        btnAddSub4.setFocusPainted(false);
        btnAddSub4.setBorderPainted(false);
        btnAddSub4.setFont(new Font("Verdana", Font.BOLD, 12));
        btnAddSub4.addActionListener(hnd);

        btnContinue = new JButton("Start Game");
        btnContinue.setBackground(new Color(247, 224, 206));
        btnContinue.setSize(170, 40);
        btnContinue.setLocation(180, 360);
        btnContinue.setFocusPainted(false);
        btnContinue.setBorderPainted(false);
        btnContinue.setFont(new Font("Verdana", Font.BOLD, 12));
        btnContinue.addActionListener(hnd);

        fr.add(title);
        fr.add(desc);
        fr.add(player1Name);
        fr.add(btnSwitchPlayer1);
        fr.add(player2Name);
        fr.add(btnSwitchPlayer2);
        fr.add(player3Name);
        fr.add(btnSwitchPlayer3);
        fr.add(btnAddSub3);
        fr.add(player4Name);
        fr.add(btnSwitchPlayer4);
        fr.add(btnAddSub4);
        fr.add(btnContinue);

        fr.setSize(500, 480);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
