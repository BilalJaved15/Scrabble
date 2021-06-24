import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameResultGUIButtonHandler implements ActionListener {
    GameResultGUI refg;

    public GameResultGUIButtonHandler(GameResultGUI refg){
        this.refg = refg;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back To Main Menu")){
            refg.fr.setVisible(false);
            try {
                MainMenuGUI temp = new MainMenuGUI();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
