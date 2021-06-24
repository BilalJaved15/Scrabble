import javax.swing.*;
import java.awt.*;

public class BlankTileAssignerGUI {
    JDialog fr;
    JLabel title;
    JLabel desc;
    int posX;
    int posY;
    JTextField letterInput;
    JButton btnContinue;
    BlankTileAssignerGUIButtonHandler hnd;
    MatchRoundGUI refg;
    boolean mainCont;

    public BlankTileAssignerGUI(int posX, int posY, MatchRoundGUI refg) {
        this.posX = posX;
        this.posY = posY;
        this.refg = refg;
        mainCont = false;
        initGUI();
    }

    public void initGUI() {

        hnd = new BlankTileAssignerGUIButtonHandler(this);

        fr = new JDialog();
        fr.setTitle("Scrabble");
        fr.setLayout(null);
        ImageIcon imgico = new ImageIcon("a-6.png");
        fr.setIconImage(imgico.getImage());
        fr.setLayout(null);
        fr.getContentPane().setBackground(new Color(68, 53, 34));

        title = new JLabel("BLANK TILE ASSIGNER", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 30));
        title.setForeground(new Color(247, 224, 206));
        title.setBackground(new Color(68, 53, 34));
        title.setLocation(40, 0);
        title.setSize(400, 100);

        desc = new JLabel("<html><center>" + "You placed a blank tile at X: "
                + posX + " Y: " + posY +
                " Please Specify the tile letter you want it to be." + "</center></html>", SwingConstants.CENTER);
        desc.setHorizontalAlignment(SwingConstants.CENTER);
        desc.setVerticalAlignment(SwingConstants.CENTER);
        desc.setForeground(new Color(247, 224, 206));
        desc.setFont(new Font("Verdana", Font.ITALIC, 15));
        desc.setSize(470, 200);
        desc.setLocation(10, 10);

        letterInput = new JTextField();
        letterInput.setBackground(new Color(247, 224, 206));
        letterInput.setSize(200, 40);
        letterInput.setLocation(145, 160);
        letterInput.setFont(new Font("Verdana", Font.BOLD, 15));

        btnContinue = new JButton("OK");
        btnContinue.setBackground(new Color(247, 224, 206));
        btnContinue.setSize(170, 40);
        btnContinue.setLocation(163, 230);
        btnContinue.setFocusPainted(false);
        btnContinue.setBorderPainted(false);
        btnContinue.setFont(new Font("Verdana", Font.BOLD, 12));
        btnContinue.addActionListener(hnd);

        fr.getContentPane().add(title);
        fr.getContentPane().add(desc);
        fr.getContentPane().add(letterInput);
        fr.getContentPane().add(btnContinue);
        fr.getContentPane().add(btnContinue);

        fr.setModal(true);
        fr.setSize(500, 380);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
