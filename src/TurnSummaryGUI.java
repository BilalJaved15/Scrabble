
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.Border;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Bilal Javed
 */
public class TurnSummaryGUI {

    JDialog fr;
    JTable table;
    JLabel title;
    JButton btnContinue;
    int noOfWords;
    int totalScore;
    String[][] tableData;
    TurnSummaryGUIButtonHandler hnd;

    public TurnSummaryGUI(ArrayList<Word> words, int[] score, MatchRoundGUI refg) {
        noOfWords = words.size();
        this.tableData = new String[words.size() + 1][2];
        totalScore = 0;
        for (int i = 0; i < words.size(); i++) {
            tableData[i][0] = new String();
            tableData[i][1] = new String();
        }
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.get(i).noOfLetters; j++) {
                tableData[i][0] += words.get(i).letters[j].tile.tileLetter;
            }
            tableData[i][1] = new String("" + score[i]);
            totalScore += score[i];
        }
        tableData[words.size()][0] = "Total";
        tableData[words.size()][1] = new String("" + totalScore);
        int totalTilesUsed = 0;
        for (int i = 0; i < words.get(0).noOfLetters; i++) {
            if (words.get(0).letters[i].isMyOwnTile) {
                totalTilesUsed++;
            }
        }
        if (totalTilesUsed == 7) {
            totalScore += 50;
            tableData[words.size()][0] = "Total + Bonus";
        } else {
            tableData[words.size()][0] = "Total";
        }
        tableData[words.size()][1] = new String("" + totalScore);
        initGUI(refg);
    }

    public void initGUI(MatchRoundGUI refg) {
        hnd = new TurnSummaryGUIButtonHandler(this, refg);

        fr = new JDialog();
        fr.setTitle("Scrabble");
        ImageIcon imgico = new ImageIcon("a-6.png");
        fr.setIconImage(imgico.getImage());
        fr.setLayout(null);

        fr.getContentPane().setBackground(new Color(68, 53, 34));

        title = new JLabel("TURN SUMMARY", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 30));
        title.setForeground(new Color(247, 224, 206));
        title.setBackground(new Color(68, 53, 34));
        title.setLocation(40, 5);
        title.setSize(400, 100);

        String column[] = {"Word", "Score"};
        table = new JTable(tableData, column);
        table.setBounds(10, 10, 100, 100);
        table.getTableHeader().setBackground(new Color(68, 53, 34));
        table.getTableHeader().setForeground(new Color(247, 224, 206));
        table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 15));
        table.setBackground(new Color(68, 53, 34));
        table.setForeground(new Color(247, 224, 206));
        table.setFont(new Font("Verdana", Font.PLAIN, 15));
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(table);
        sp.setLocation(40, 100);
        sp.setSize(400, 27 + ((16 * (noOfWords + 1))));
        sp.setBackground(new Color(68, 53, 34));

        Border emptyBorder = BorderFactory.createEmptyBorder();

        btnContinue = new JButton("Continue");
        btnContinue.setFont(new Font("Verdana", Font.BOLD, 20));
        btnContinue.setBackground(new Color(68, 53, 34));
        btnContinue.setForeground(new Color(247, 224, 206));
        btnContinue.setBorder(emptyBorder);
        btnContinue.setLocation(165, 350);
        btnContinue.setSize(150, 50);
        btnContinue.setFocusPainted(false);
        btnContinue.setBorderPainted(false);
        btnContinue.addActionListener(hnd);

        fr.add(sp);
        fr.add(btnContinue);
        fr.add(title);

        fr.setModal(true);
        fr.setSize(500, 500);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setVisible(true);

    }
}
