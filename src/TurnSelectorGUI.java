
import java.awt.*;
import java.util.ArrayList;
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
public class TurnSelectorGUI {

    JFrame fr;
    ArrayList<Player> players;
    int playerCount;
    JButton[] labels;
    JButton btnShuffleContinue;
    JPanel tilesPanel;
    TurnSelectorGUIButtonHandler hnd;
    TilesBag bag;
    JLabel title;
    JLabel desc;

    public TurnSelectorGUI(ArrayList<Player> players) {
        this.players = players;
        this.playerCount = players.size();
        initGUI();
    }

    public boolean isUnique() {
        for (int i = 0; i < labels.length; i++) {
            char c = labels[i].getText().charAt(14);
            if (labels[i].getText().charAt(15) == 'L') {
                for (int j = 0; j < labels.length; j++) {
                    if (j != i) {
                        if (labels[j].getText().charAt(14) == 'B'
                                && labels[j].getText().charAt(15) == 'L') {
                            return false;
                        }
                    }
                }
            } else {
                for (int j = 0; j < labels.length; j++) {
                    if (j != i) {
                        if (labels[j].getText().charAt(14) == c) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void initGUI() {
        bag = new TilesBag();

        hnd = new TurnSelectorGUIButtonHandler(this);

        fr = new JFrame("Scrabble");
        ImageIcon imgico = new ImageIcon("a-6.png");
        fr.setIconImage(imgico.getImage());
        fr.setLayout(null);
        fr.getContentPane().setBackground(new Color(68, 53, 34));

        title = new JLabel("TURN ASSIGNER", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 30));
        title.setForeground(new Color(247, 224, 206));
        title.setBackground(new Color(68, 53, 34));
        title.setLocation(40, 0);
        title.setSize(400, 100);

        desc = new JLabel("<html><center>" + "Tiles are randomly drawn from the tiles bag. " +
                "The player with the tile closest to A gets the first turn and so on. " +
                "A blank tile beats any letter. " +
                "If not unique, press the shuffle button to shuffle the duplicate tiles, otherwise continue." + "</center></html>", SwingConstants.CENTER);
        desc.setHorizontalAlignment(SwingConstants.CENTER);
        desc.setVerticalAlignment(SwingConstants.CENTER);
        desc.setForeground(new Color(247, 224, 206));
        desc.setFont(new Font("Verdana", Font.ITALIC, 15));
        desc.setSize(470, 200);
        desc.setLocation(10, 30);

        tilesPanel = new JPanel();
        tilesPanel.setLayout(new GridLayout(1, playerCount));
        tilesPanel.setSize(280, 70);
        tilesPanel.setLocation(100, 200);

        labels = new JButton[playerCount];

        Border border = BorderFactory.createLineBorder(new Color(68, 53, 34));

        int posX = 250 - 80, posY = 70;

        for (int i = 0; i < playerCount; i++) {
            labels[i] = new JButton();
            labels[i].setText("<html><center>" + bag.getRandomTileLetter() + "</center><br /><center>" + players.get(i).getName() + "</center></html>");
            labels[i].setLocation(posX, posY);
            labels[i].setForeground(new Color(68, 53, 34));
            labels[i].setBackground(new Color(247, 224, 206));
            labels[i].setBorder(border);
            posX += 80;
            labels[i].setFocusPainted(false);
            labels[i].setSize(70, 70);
            tilesPanel.add(labels[i]);
        }

        fr.add(tilesPanel);

        btnShuffleContinue = new JButton();
        btnShuffleContinue.setFont(new Font("Verdana", Font.PLAIN, 20));
        btnShuffleContinue.setBackground(new Color(68, 53, 34));
        btnShuffleContinue.setForeground(new Color(247, 224, 206));
        btnShuffleContinue.setBorder(border);
        btnShuffleContinue.setLocation(130, 300);
        btnShuffleContinue.setSize(230, 50);
        btnShuffleContinue.setFocusPainted(false);
        btnShuffleContinue.setBorderPainted(false);
        btnShuffleContinue.addActionListener(hnd);

        if (isUnique() == true) {
            btnShuffleContinue.setText("Continue");
        } else {
            btnShuffleContinue.setText("Shuffle");
        }

        fr.add(btnShuffleContinue);
        fr.add(title);
        fr.add(desc);
        fr.setSize(500, 450);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
