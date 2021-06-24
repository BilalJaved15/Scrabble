import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameResultGUI {
    JFrame fr;
    JButton btnContinue;
    JTable results;
    JLabel title;
    JLabel desc;
    String[][] tableData;
    ArrayList<Player> players;
    GameResultGUIButtonHandler hnd;

    public GameResultGUI(ArrayList<Player> players) {
        this.players = players;
        generateGameResultScore();
        initGUI();
    }

    public void generateGameResultScore() {
        for (int i = 0; i < players.size(); i++) {
            int currPlayerScore = players.get(i).getScore();
            for (int j = 0; j < players.get(i).rack.noOfTiles; j++) {
                currPlayerScore -= (players.get(i).rack.get(j).tileScore);
            }
            players.get(i).setScore(currPlayerScore);
        }
        Collections.sort(players, new Player());
        tableData = new String[players.size()][2];
        for (int i = 0; i < players.size(); i++) {
            tableData[i][0] = new String(players.get(i).name);
            tableData[i][1] = new String(players.get(i).score + "");
        }

    }

    public void initGUI() {

        fr = new JFrame();
        fr.setTitle("Scrabble");
        ImageIcon imgico = new ImageIcon("a-6.png");
        fr.setIconImage(imgico.getImage());
        fr.setLayout(null);
        fr.getContentPane().setBackground(new Color(68, 53, 34));

        hnd = new GameResultGUIButtonHandler(this);

        title = new JLabel("GAME SUMMARY", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 30));
        title.setForeground(new Color(247, 224, 206));
        title.setBackground(new Color(68, 53, 34));
        title.setLocation(40, 5);
        title.setSize(400, 100);

        desc = new JLabel("<html><center>" + "The game ended due to the " +
                "number of tiles in bag" +
                " being less than 7. Following is " +
                "the game summary. " +
                "Congratulations, " + players.get(0).getName()
                + "! You won the game" + "</center></html>", SwingConstants.CENTER);
        desc.setHorizontalAlignment(SwingConstants.CENTER);
        desc.setVerticalAlignment(SwingConstants.CENTER);
        desc.setForeground(new Color(247, 224, 206));
        desc.setFont(new Font("Verdana", Font.ITALIC, 15));
        desc.setSize(470, 200);
        desc.setLocation(10, 10);

        String column[] = {"Player", "Score"};
        results = new JTable(tableData, column);
        results.setBounds(10, 10, 100, 100);
        results.getTableHeader().setBackground(new Color(68, 53, 34));
        results.getTableHeader().setForeground(new Color(247, 224, 206));
        results.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 15));
        results.setBackground(new Color(68, 53, 34));
        results.setForeground(new Color(247, 224, 206));
        results.setFont(new Font("Verdana", Font.PLAIN, 15));
        results.setPreferredScrollableViewportSize(results.getPreferredSize());
        results.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(results);
        sp.setLocation(40, 150);
        sp.setSize(400, 27 + ((16 * (players.size()))));
        sp.setBackground(new Color(68, 53, 34));

        Border emptyBorder = BorderFactory.createEmptyBorder();

        btnContinue = new JButton("Back To Main Menu");
        btnContinue.setFont(new Font("Verdana", Font.BOLD, 20));
        btnContinue.setBackground(new Color(68, 53, 34));
        btnContinue.setForeground(new Color(247, 224, 206));
        btnContinue.setBorder(emptyBorder);
        btnContinue.setLocation(110, 350);
        btnContinue.setSize(250, 50);
        btnContinue.setFocusPainted(false);
        btnContinue.setBorderPainted(false);
        btnContinue.addActionListener(hnd);

        fr.add(sp);
        fr.add(desc);
        fr.add(btnContinue);
        fr.add(title);

        fr.setSize(500, 500);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setVisible(true);
    }
}
