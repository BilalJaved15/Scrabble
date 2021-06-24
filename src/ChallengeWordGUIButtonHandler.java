import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChallengeWordGUIButtonHandler implements ActionListener {

    ChallengeWordGUI challengeGUI;
    MatchRoundGUI mainGUI;

    public ChallengeWordGUIButtonHandler(ChallengeWordGUI challengeGUI, MatchRoundGUI mainGUI) {
        this.challengeGUI = challengeGUI;
        this.mainGUI = mainGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Challenge!")) {
            boolean areAllValid = true;
            String[] wordsValidity = new String[challengeGUI.noOfWords];
            for (int i = 0; i < challengeGUI.noOfWords; i++) {
                boolean isValid = WordCheck.isWord(challengeGUI.tableData[i][0]);
                if (isValid == true) {
                    wordsValidity[i] = new String("Valid");
                } else {
                    wordsValidity[i] = new String("Not Valid");
                    areAllValid = false;
                }
            }
            challengeGUI.tableModel.addColumn("Validity", wordsValidity);
            challengeGUI.btnConfirm.setEnabled(false);
            challengeGUI.btnCancel.setText("OK");
            if (areAllValid){
                challengeGUI.desc.setText("<html><center>" + "Uh-oh!" +
                        " All the words are valid, you lose your turn." + "</center></html>");
                challengeGUI.logic.switchTurn();
            }
            else{
                challengeGUI.desc.setText("<html><center>" + "Woohoo!" +
                        " One or more of the words is/are invalid." + "</center></html>");
                challengeGUI.logic.reverseLastTurn();
            }
        }
        else if(e.getActionCommand().equals("Cancel")){
            challengeGUI.fr.setVisible(false);
            mainGUI.fr.setEnabled(true);
        }
        else if(e.getActionCommand().equals("OK")){
            challengeGUI.fr.setVisible(false);
            mainGUI.fr.setEnabled(true);
            mainGUI.btnChallengeWord.setEnabled(false);
        }
    }
}
