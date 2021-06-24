
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Bilal Javed
 */
public class TurnSummaryGUIButtonHandler implements ActionListener {

    TurnSummaryGUI refg;
    MatchRoundGUI mainGUI;

    public TurnSummaryGUIButtonHandler(TurnSummaryGUI gui, MatchRoundGUI mainGUI) {
        refg = gui;
        this.mainGUI = mainGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Continue")) {
            refg.fr.setVisible(false);
            mainGUI.fr.setEnabled(true);
        }
    }

}
