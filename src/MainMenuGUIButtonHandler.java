
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Bilal Javed
 */
public class MainMenuGUIButtonHandler implements ActionListener {

    MainMenuGUI refg;

    public MainMenuGUIButtonHandler(MainMenuGUI gui) {
        refg = gui;
    }

    public void populateBoard(Board board) {
        try {
            File file = new File("boardState.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String row = br.readLine();
            for (int i = 0; i < 15; i++) {
                String[] rowIndexValues = row.split(":");
                for (int j = 0; j < 15; j++) {
                    if (rowIndexValues[j].equals("-")) {
                        board.set(i, j, Board.getDefaultBoardPos(i, j));
                    } else {
                        board.set(i, j, rowIndexValues[j]);
                    }
                }
                row = br.readLine();
            }
            br.close();
            fr.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int populatePlayers(ArrayList<Player> players) {
        int currPlayerPointer = 0;
        try {
            File file = new File("playersState.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            int n = 0;
            int playerIterator = 0;
            String line = br.readLine();
            currPlayerPointer = Integer.parseInt(line);
            line = br.readLine();
            while (line != null) {
                if (n % 3 == 0) {
                    if (line.contains("(Computer)")) {
                        players.add(new ComputerPlayer());
                        players.get(playerIterator).setName(line);
                    } else {
                        players.add(new HumanPlayer());
                        players.get(playerIterator).setName(line);
                    }
                } else if (n % 3 == 1) {
                    String[] rackValues = line.split(":");
                    for (int i = 0; i < rackValues.length; i++) {
                        players.get(playerIterator).rack.addTile(new Tile(rackValues[i]));
                    }
                } else {
                    players.get(playerIterator).score = Integer.parseInt(line);
                    playerIterator++;
                }
                n++;
                line = br.readLine();
            }

            br.close();
            fr.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return currPlayerPointer;
    }

    public void populateTilesBag(TilesBag bag) {
        try {
            File file = new File("tilesBagState.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            String[] tiles = line.split(":");
            bag.tileArray = null;
            bag.tileArray = new Tile[tiles.length];
            bag.tilesCount = 0;
            for (int i = 0; i < tiles.length; i++) {
                bag.insertTiles(new Tile(tiles[i]));
            }

            br.close();
            fr.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Exit")) {
            refg.fr.setVisible(false);
            System.exit(0);
        } else if (e.getActionCommand().equals("Start New Game")) {
            refg.fr.setVisible(false);
            PlayerSelectorGUI temp = new PlayerSelectorGUI();
        } else if (e.getActionCommand().equals("Continue Last Game")) {
            Board board = new Board();
            ArrayList<Player> players = new ArrayList<Player>();
            TilesBag bag = new TilesBag();
            populateBoard(board);
            int currPlayerPointer = populatePlayers(players);
            populateTilesBag(bag);
            MatchRoundGUI temp = new MatchRoundGUI(players, currPlayerPointer, board ,bag);
            temp.fr.setVisible(false);
            temp.currPlayerPointer = currPlayerPointer;
            refg.fr.setVisible(false);
            temp.fr.setVisible(true);
        }
    }

}
