
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bilal Javed
 */
public class PlacementErrorGUIButtonHandler implements ActionListener {
    
    PlacementErrorGUI refg;
    MatchRoundGUI boardGUI;
    
    public PlacementErrorGUIButtonHandler(PlacementErrorGUI gui, MatchRoundGUI boardGUI) {
        refg = gui;
        this.boardGUI = boardGUI;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OK")) {
            refg.fr.setVisible(false);
            boardGUI.fr.setEnabled(true);
        }
    }
    
}
