
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bilal Javed
 */
public class PlayerSelectorGUIButtonHandler implements ActionListener {

    PlayerSelectorGUI refg;

    public PlayerSelectorGUIButtonHandler(PlayerSelectorGUI gui) {
        refg = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Player1Human")) {
            refg.btnSwitchPlayer1.setActionCommand("Player1Computer");
            refg.btnSwitchPlayer1.setText("COMPUTER");
        } else if (e.getActionCommand().equals("Player2Human")) {
            refg.btnSwitchPlayer2.setActionCommand("Player2Computer");
            refg.btnSwitchPlayer2.setText("COMPUTER");
        } else if (e.getActionCommand().equals("Player3Human")) {
            refg.btnSwitchPlayer3.setActionCommand("Player3Computer");
            refg.btnSwitchPlayer3.setText("COMPUTER");
        } else if (e.getActionCommand().equals("Player4Human")) {
            refg.btnSwitchPlayer4.setActionCommand("Player4Computer");
            refg.btnSwitchPlayer4.setText("COMPUTER");
        } else if (e.getActionCommand().equals("Player1Computer")) {
            refg.btnSwitchPlayer1.setActionCommand("Player1Human");
            refg.btnSwitchPlayer1.setText("HUMAN");
        } else if (e.getActionCommand().equals("Player2Computer")) {
            refg.btnSwitchPlayer2.setActionCommand("Player2Human");
            refg.btnSwitchPlayer2.setText("HUMAN");
        } else if (e.getActionCommand().equals("Player3Computer")) {
            refg.btnSwitchPlayer3.setActionCommand("Player3Human");
            refg.btnSwitchPlayer3.setText("HUMAN");
        } else if (e.getActionCommand().equals("Player4Computer")) {
            refg.btnSwitchPlayer4.setActionCommand("Player4Human");
            refg.btnSwitchPlayer4.setText("HUMAN");
        } else if (e.getActionCommand().equals("Player3+")) {
            refg.btnAddSub3.setActionCommand("Player3-");
            refg.btnAddSub3.setText("-");
            refg.player3Name.setEditable(true);
            refg.btnSwitchPlayer3.setEnabled(true);
        } else if (e.getActionCommand().equals("Player4+")) {
            refg.btnAddSub4.setActionCommand("Player4-");
            refg.btnAddSub4.setText("-");
            refg.player4Name.setEditable(true);
            refg.btnSwitchPlayer4.setEnabled(true);
        } else if (e.getActionCommand().equals("Player3-")) {
            refg.btnAddSub3.setActionCommand("Player3+");
            refg.btnAddSub3.setText("+");
            refg.player3Name.setEditable(false);
            refg.btnSwitchPlayer3.setEnabled(false);
        } else if (e.getActionCommand().equals("Player4-")) {
            refg.btnAddSub4.setActionCommand("Player4+");
            refg.btnAddSub4.setText("+");
            refg.player4Name.setEditable(false);
            refg.btnSwitchPlayer4.setEnabled(false);
        } else if (e.getActionCommand().equals("Start Game")) {
            int n = 0;
            ArrayList<Player> players = new ArrayList<Player>();
            if (refg.btnSwitchPlayer1.getText().equals("HUMAN")) {
                players.add(new HumanPlayer());
                if (refg.player1Name.getText().equals("")) {
                    players.get(n).setName("Player " + (n + 1));
                } else {
                    players.get(n).setName(refg.player1Name.getText());
                }
                n++;
            } else {
                players.add(new ComputerPlayer());
                if (refg.player1Name.getText().equals("")) {
                    players.get(n).setName("Player " + (n + 1) + " (Computer)");
                } else {
                    players.get(n).setName(refg.player1Name.getText() + " (Computer)");
                }
                n++;
            }
            if (refg.btnSwitchPlayer2.getText().equals("HUMAN")) {
                players.add(new HumanPlayer());
                if (refg.player2Name.getText().equals("")) {
                    players.get(n).setName("Player " + (n + 1));
                } else {
                    players.get(n).setName(refg.player2Name.getText());
                }
                n++;
            } else {
                players.add(new ComputerPlayer());
                if (refg.player2Name.getText().equals("")) {
                    players.get(n).setName("Player " + (n + 1) + " (Computer)");
                } else {
                    players.get(n).setName(refg.player2Name.getText() + " (Computer)");
                }
                n++;
            }
            if (refg.btnAddSub3.getText().equals("-")) {
                if (refg.btnSwitchPlayer3.getText().equals("HUMAN")) {
                    players.add(new HumanPlayer());
                    if (refg.player3Name.getText().equals("")) {
                        players.get(n).setName("Player " + (n + 1));
                    } else {
                        players.get(n).setName(refg.player3Name.getText());
                    }
                    n++;
                } else {
                    players.add(new ComputerPlayer());
                    if (refg.player3Name.getText().equals("")) {
                        players.get(n).setName("Player " + (n + 1) + " (Computer)");
                    } else {
                        players.get(n).setName(refg.player3Name.getText() + " (Computer)");
                    }
                    n++;
                }
            }
            if (refg.btnAddSub4.getText().equals("-")) {
                if (refg.btnSwitchPlayer4.getText().equals("HUMAN")) {
                    players.add(new HumanPlayer());
                    if (refg.player4Name.getText().equals("")) {
                        players.get(n).setName("Player " + (n + 1));
                    } else {
                        players.get(n).setName(refg.player4Name.getText());
                    }
                    n++;
                } else {
                    players.add(new ComputerPlayer());
                    if (refg.player4Name.getText().equals("")) {
                        players.get(n).setName("Player " + (n + 1) + " (Computer)");
                    } else {
                        players.get(n).setName(refg.player4Name.getText() + " (Computer)");
                    }
                    n++;
                }
            }
            refg.fr.setVisible(false);
            TurnSelectorGUI temp = new TurnSelectorGUI(players);
        }
    }

}
