
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Bilal Javed
 */
public class MatchRoundGUI {

    JFrame fr;
    JButton[][] board;
    JLabel currPlayer;
    JLabel currScore;
    JButton btnChallengeWord;
    JButton btnSubmitWord;
    JButton btnChangeTiles;
    JButton btnConfirmChange;
    JButton btnCancelChange;
    ArrayList<Player> players;
    int currPlayerPointer;
    JButton[] currPlayerRack;
    TilesBag bag;
    MatchRoundGUIButtonHandler hnd;
    Board refBoard;

    public MatchRoundGUI(ArrayList<Player> players, int currPlayerPointer, Board board, TilesBag bag) {
        this.players = players;
        this.currPlayerPointer = currPlayerPointer;
        this.bag = bag;
        this.refBoard = board;
        initGUI();
    }

    public void initGUI() {
        hnd = new MatchRoundGUIButtonHandler(this);

        fr = new JFrame("Scrabble");
        ImageIcon imgico = new ImageIcon("assets/a-6.png");
        fr.setIconImage(imgico.getImage());
        fr.setLayout(null);

        JLabel contentPane = new JLabel();
        contentPane.setIcon(new ImageIcon("assets/GAMEBOARD.png"));
        contentPane.setLayout(new BorderLayout());
        contentPane.setSize(1320, 768);
        contentPane.setLocation(0, 0);

        board = new JButton[15][15];

        int posX = 15, posY = 10;

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = new JButton();
                board[i][j].setFont(new Font("Verdana", Font.PLAIN, 20));
                board[i][j].setActionCommand("board" + ":" + (i) + ":" + (j));
                board[i][j].setForeground(new Color(68, 53, 34));
                board[i][j].setBackground(new Color(245, 226, 203));
                board[i][j].setLocation(posX, posY);
                board[i][j].setSize(45, 45);
                board[i][j].setBorder(new LineBorder(Color.BLACK));
                board[i][j].addActionListener(hnd);
                fr.add(board[i][j]);
                posX += 45;
            }
            posY += 45;
            posX = 15;
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (refBoard.get(i, j).length() == 1 && !(refBoard.get(i, j).equals("*"))) {
                    board[i][j].setText("<html>" + refBoard.get(i, j) + "<sub>"
                            + Tile.getTileScoreByLetter(refBoard.get(i, j)) + "</sub></html>");
                    board[i][j].setForeground(new Color(245, 226, 203));
                    board[i][j].setBackground(new Color(68, 53, 34));
                } else {
                    board[i][j].setText(refBoard.get(i, j));
                }
            }
        }

        if (bag.getTilesCount() == 100) {
            for (int i = 0; i < players.size(); i++) {
                bag.fillRack(players, i);
            }
        }

        currPlayerRack = new JButton[7];

        posX = 749;
        posY = 480;

        for (int i = 0; i < currPlayerRack.length; i++) {
            currPlayerRack[i] = new JButton();
            currPlayerRack[i].setText("<html>" + players.get(currPlayerPointer).rack.get(i).tileLetter + "<sub>"
                    + players.get(currPlayerPointer).rack.get(i).tileScore + "</sub></html>");
            currPlayerRack[i].setActionCommand("rack" + i);
            currPlayerRack[i].setFont(new Font("Verdana", Font.PLAIN, 17));
            currPlayerRack[i].setForeground(new Color(68, 53, 34));
            currPlayerRack[i].setBackground(new Color(245, 226, 203));
            currPlayerRack[i].setLocation(posX, posY);
            currPlayerRack[i].setFocusPainted(false);
            currPlayerRack[i].setBorderPainted(false);
            currPlayerRack[i].setSize(48, 49);
            currPlayerRack[i].addActionListener(hnd);
            posX += 75;
            fr.add(currPlayerRack[i]);
        }

        currPlayer = new JLabel(players.get(currPlayerPointer).getName(), SwingConstants.CENTER);
        currPlayer.setFont(new Font("Verdana", Font.PLAIN, 20));
        currPlayer.setForeground(new Color(245, 226, 203));
        currPlayer.setLocation(872, 145);
        currPlayer.setSize(251, 37);
        fr.add(currPlayer);

        currScore = new JLabel("" + players.get(currPlayerPointer).getScore(), SwingConstants.CENTER);
        currScore.setFont(new Font("Verdana", Font.PLAIN, 20));
        currScore.setForeground(new Color(245, 226, 203));
        currScore.setLocation(872, 239);
        currScore.setSize(251, 37);
        fr.add(currScore);

        btnChallengeWord = new JButton("Challenge Last Word!");
        btnChallengeWord.setFont(new Font("Verdana", Font.PLAIN, 20));
        btnChallengeWord.setForeground(new Color(245, 226, 203));
        btnChallengeWord.setBackground(new Color(68, 53, 34));
        btnChallengeWord.setLocation(872, 337);
        btnChallengeWord.setSize(251, 37);
        btnChallengeWord.setFocusPainted(false);
        btnChallengeWord.setBorderPainted(false);
        btnChallengeWord.setEnabled(false);
        btnChallengeWord.addActionListener(hnd);
        fr.add(btnChallengeWord);

        btnSubmitWord = new JButton("Submit Word!");
        btnSubmitWord.setFont(new Font("Verdana", Font.PLAIN, 20));
        btnSubmitWord.setBackground(new Color(245, 226, 203));
        btnSubmitWord.setForeground(new Color(68, 53, 34));
        btnSubmitWord.setLocation(734, 600);
        btnSubmitWord.setSize(251, 37);
        btnSubmitWord.setFocusPainted(false);
        btnSubmitWord.setBorderPainted(false);
        btnSubmitWord.setEnabled(false);
        btnSubmitWord.addActionListener(hnd);
        fr.add(btnSubmitWord);

        btnConfirmChange = new JButton("Confirm Change!");
        btnConfirmChange.setFont(new Font("Verdana", Font.PLAIN, 20));
        btnConfirmChange.setBackground(new Color(245, 226, 203));
        btnConfirmChange.setForeground(new Color(68, 53, 34));
        btnConfirmChange.setLocation(734, 600);
        btnConfirmChange.setSize(251, 37);
        btnConfirmChange.setFocusPainted(false);
        btnConfirmChange.setBorderPainted(false);
        btnConfirmChange.setEnabled(false);
        btnConfirmChange.addActionListener(hnd);
        btnConfirmChange.setVisible(false);
        fr.add(btnConfirmChange);

        btnChangeTiles = new JButton("Change Tiles!");
        btnChangeTiles.setFont(new Font("Verdana", Font.PLAIN, 20));
        btnChangeTiles.setBackground(new Color(245, 226, 203));
        btnChangeTiles.setForeground(new Color(68, 53, 34));
        btnChangeTiles.setLocation(1020, 600);
        btnChangeTiles.setSize(251, 37);
        btnChangeTiles.setFocusPainted(false);
        btnChangeTiles.setBorderPainted(false);
        btnChangeTiles.addActionListener(hnd);
        fr.add(btnChangeTiles);

        btnCancelChange = new JButton("Cancel Change!");
        btnCancelChange.setFont(new Font("Verdana", Font.PLAIN, 20));
        btnCancelChange.setBackground(new Color(245, 226, 203));
        btnCancelChange.setForeground(new Color(68, 53, 34));
        btnCancelChange.setLocation(1020, 600);
        btnCancelChange.setSize(251, 37);
        btnCancelChange.setFocusPainted(false);
        btnCancelChange.setBorderPainted(false);
        btnCancelChange.addActionListener(hnd);
        btnCancelChange.setVisible(false);
        fr.add(btnCancelChange);

        fr.add(contentPane);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(1320, 768);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);

        if (players.get(currPlayerPointer).getName().contains("(Computer)")) {
            hnd.invokeBotForMove();
        }
    }
}
