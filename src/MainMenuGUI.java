
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
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
public class MainMenuGUI {

    JFrame fr;
    JLabel title;
    JButton startGame;
    JButton continueGame;
    JButton exitGame;
    MainMenuGUIButtonHandler hnd;

    public MainMenuGUI() throws IOException {
        initGUI();
    }

    public void initGUI() throws IOException {
        fr = new JFrame("Scrabble");
        ImageIcon imgico = new ImageIcon("a-6.png");
        fr.setIconImage(imgico.getImage());
        fr.setLayout(null);

        hnd = new MainMenuGUIButtonHandler(this);
        
        JLabel contentPane = new JLabel();
        contentPane.setIcon(new ImageIcon("MAINMENU.jpg"));
        contentPane.setLayout(new BorderLayout());
        contentPane.setSize(1163, 656);
        contentPane.setLocation(0, 0);

        title = new JLabel("SCRABBLE");
        title.setFont(new Font("Verdana", Font.BOLD, 40));
        title.setForeground(new Color(247, 224, 206));
        title.setLocation(50, 20);
        title.setSize(300, 300);

        Border emptyBorder = BorderFactory.createEmptyBorder();

        startGame = new JButton("Start New Game");
        startGame.setFont(new Font("Verdana", Font.PLAIN, 20));
        startGame.setBackground(new Color(68, 53, 34));
        startGame.setForeground(new Color(247, 224, 206));
        startGame.setBorder(emptyBorder);
        startGame.setHorizontalAlignment(SwingConstants.LEFT);
        startGame.setLocation(50, 200);
        startGame.setSize(200, 50);
        startGame.setFocusPainted(false);
        startGame.setBorderPainted(false);
        startGame.addActionListener(hnd);
        
        continueGame = new JButton("Continue Last Game");
        continueGame.setFont(new Font("Verdana", Font.PLAIN, 20));
        continueGame.setBackground(new Color(68, 53, 34));
        continueGame.setForeground(new Color(247, 224, 206));
        continueGame.setBorder(emptyBorder);
        continueGame.setHorizontalAlignment(SwingConstants.LEFT);
        continueGame.setLocation(50, 250);
        continueGame.setSize(230, 50);
        continueGame.setFocusPainted(false);
        continueGame.setBorderPainted(false);
        continueGame.addActionListener(hnd);
        
        exitGame = new JButton("Exit");
        exitGame.setFont(new Font("Verdana", Font.PLAIN, 20));
        exitGame.setBackground(new Color(68, 53, 34));
        exitGame.setForeground(new Color(247, 224, 206));
        exitGame.setHorizontalAlignment(SwingConstants.LEFT);
        exitGame.setBorder(emptyBorder);
        exitGame.setLocation(50, 300);
        exitGame.setSize(230, 50);
        exitGame.setFocusPainted(false);
        exitGame.setBorderPainted(false);
        exitGame.addActionListener(hnd);
        
        fr.add(startGame);
        fr.add(continueGame);
        fr.add(exitGame);
        fr.add(title);
        fr.add(contentPane);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(1163, 656);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }
}
