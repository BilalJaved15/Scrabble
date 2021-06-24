
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
public class TurnSelectorGUIButtonHandler implements ActionListener {

    TurnSelectorGUI refg;

    public TurnSelectorGUIButtonHandler(TurnSelectorGUI gui) {
        refg = gui;
    }

    public void grabTiles() {
        for (int i = 0; i < refg.labels.length; i++) {
            char c = refg.labels[i].getText().charAt(14);
            if (refg.labels[i].getText().charAt(15) == 'L') {
                for (int j = 0; j < refg.labels.length; j++) {
                    if (j != i) {
                        if (refg.labels[j].getText().charAt(14) == 'B'
                                && refg.labels[j].getText().charAt(15) == 'L') {
                            refg.labels[i].setText("<html><center>" + refg.bag.getRandomTileLetter() + "</center><br /><center>" + refg.players.get(i).getName() + "</center></html>");
                        }
                    }
                }
            } else {
                for (int j = 0; j < refg.labels.length; j++) {
                    if (j != i) {
                        if (refg.labels[j].getText().charAt(14) == c) {
                            refg.labels[i].setText("<html><center>" + refg.bag.getRandomTileLetter() + "</center><br /><center>" + refg.players.get(i).getName() + "</center></html>");
                        }
                    }
                }
            }
        }
    }

    public void sortPlayers() {
        boolean blankSort = false;
        for (int i = 0; i < refg.labels.length; i++) {
            if (refg.labels[i].getText().charAt(14) == 'B'
                    && refg.labels[i].getText().charAt(14) == 'L') {
                Player temp1 = refg.players.get(i);
                refg.players.set(i, refg.players.get(0));
                refg.players.set(0, temp1);
                String temp2 = refg.labels[i].getText();
                refg.labels[i].setText(refg.labels[0].getText());
                refg.labels[0].setText(temp2);
                blankSort = true;
            }
        }
        int start = 0;
        if (blankSort == true) {
            start = 1;
        }
        for (int i = start; i < refg.labels.length; i++) {
            char smallest = refg.labels[i].getText().charAt(14);
            int index = i;
            for (int j = i; j < refg.labels.length; j++) {
                if (refg.labels[j].getText().charAt(14) < smallest) {
                    smallest = refg.labels[j].getText().charAt(14);
                    index = j;
                }
            }
            Player temp1 = refg.players.get(i);
            refg.players.set(i, refg.players.get(index));
            refg.players.set(index, temp1);
            String temp2 = refg.labels[i].getText();
            refg.labels[i].setText(refg.labels[0].getText());
            refg.labels[0].setText(temp2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Shuffle")) {
            grabTiles();
            if (refg.isUnique()) {
                refg.btnShuffleContinue.setText("Continue");
            }
        } else {
            sortPlayers();
            refg.fr.setVisible(false);
            MatchRoundGUI temp = new MatchRoundGUI(refg.players, 0, new Board(), new TilesBag());
        }
    }

}
