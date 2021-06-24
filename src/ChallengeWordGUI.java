import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ChallengeWordGUI {
    JFrame fr;
    JTable table;
    DefaultTableModel tableModel;
    JLabel title;
    JLabel desc;
    JButton btnConfirm;
    JButton btnCancel;
    int noOfWords;
    int totalScore;
    String[][] tableData;
    ChallengeWordGUIButtonHandler hnd;
    MatchRoundLogic logic;

    public ChallengeWordGUI(ArrayList<Word> words, MatchRoundGUI refg, MatchRoundLogic logic) {
        this.logic = logic;
        noOfWords = words.size();
        this.tableData = new String[words.size()][1];
        totalScore = 0;
        for (int i = 0; i < words.size(); i++) {
            tableData[i][0] = new String();
        }
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.get(i).noOfLetters; j++) {
                tableData[i][0] += words.get(i).letters[j].tile.tileLetter;
            }
        }
        initGUI(refg);
    }

    public void initGUI(MatchRoundGUI refg) {
        hnd = new ChallengeWordGUIButtonHandler(this, refg);

        fr = new JFrame("Scrabble");
        ImageIcon imgico = new ImageIcon("a-6.png");
        fr.setIconImage(imgico.getImage());
        fr.setLayout(null);

        fr.getContentPane().setBackground(new Color(68, 53, 34));

        title = new JLabel("CHALLENGE WORD", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 30));
        title.setForeground(new Color(247, 224, 206));
        title.setBackground(new Color(68, 53, 34));
        title.setLocation(40, 5);
        title.setSize(400, 100);

        desc = new JLabel("<html><center>" + "The following words were created in the last turn." +
                " Press Challenge to verify these words." + "</center></html>", SwingConstants.CENTER);
        desc.setHorizontalAlignment(SwingConstants.CENTER);
        desc.setVerticalAlignment(SwingConstants.CENTER);
        desc.setForeground(new Color(247, 224, 206));
        desc.setFont(new Font("Verdana", Font.ITALIC, 15));
        desc.setSize(470, 200);
        desc.setLocation(10, 10);

        String column[] = {"Word"};

        tableModel = new DefaultTableModel(tableData, column);

        table = new JTable(tableModel);
        table.setBounds(10, 10, 100, 100);
        table.getTableHeader().setBackground(new Color(68, 53, 34));
        table.getTableHeader().setForeground(new Color(247, 224, 206));
        table.getTableHeader().setFont(new  Font("Verdana", Font.BOLD, 15));
        table.setBackground(new Color(68, 53, 34));
        table.setForeground(new Color(247, 224, 206));
        table.setFont(new  Font("Verdana", Font.PLAIN, 15));
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(table);
        sp.setLocation(40, 150);
        sp.setSize(400, 27 + ((16 * (noOfWords))));
        sp.setBackground(new Color(68, 53, 34));

        Border emptyBorder = BorderFactory.createEmptyBorder();

        btnConfirm = new JButton("Challenge!");
        btnConfirm.setFont(new Font("Verdana", Font.BOLD, 20));
        btnConfirm.setBackground(new Color(68, 53, 34));
        btnConfirm.setForeground(new Color(247, 224, 206));
        btnConfirm.setBorder(emptyBorder);
        btnConfirm.setLocation(80, 350);
        btnConfirm.setSize(150, 50);
        btnConfirm.setFocusPainted(false);
        btnConfirm.setBorderPainted(false);
        btnConfirm.addActionListener(hnd);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Verdana", Font.BOLD, 20));
        btnCancel.setBackground(new Color(68, 53, 34));
        btnCancel.setForeground(new Color(247, 224, 206));
        btnCancel.setBorder(emptyBorder);
        btnCancel.setLocation(245, 350);
        btnCancel.setSize(150, 50);
        btnCancel.setFocusPainted(false);
        btnCancel.setBorderPainted(false);
        btnCancel.addActionListener(hnd);

        fr.add(sp);
        fr.add(btnConfirm);
        fr.add(btnCancel);
        fr.add(title);
        fr.add(desc);

        fr.setSize(500, 500);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);

    }
}
