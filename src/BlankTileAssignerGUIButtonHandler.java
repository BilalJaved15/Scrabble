import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlankTileAssignerGUIButtonHandler implements ActionListener {

    BlankTileAssignerGUI refg;

    public BlankTileAssignerGUIButtonHandler(BlankTileAssignerGUI refg) {
        this.refg = refg;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK")) {
            String inp = refg.letterInput.getText();
            if (inp.length() == 1 &&
                    ((inp.charAt(0) >= 'A' && inp.charAt(0) <= 'Z')
                            || (inp.charAt(0) >= 'a' && inp.charAt(0) <= 'z'))) {
                refg.refg.board[refg.posX][refg.posY].setText("<html>" + inp.toUpperCase() + "<sub>" + Tile.getTileScoreByLetter(inp) + "</sub></html>");
                refg.refg.fr.setEnabled(true);
                refg.mainCont = true;
                refg.fr.setVisible(false);
            } else {
                refg.letterInput.setText("Invalid Input");
            }
        }
    }
}
