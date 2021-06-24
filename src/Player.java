/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Comparator;

/**
 *
 * @author Bilal Javed
 */
public class Player implements Comparator<Player> {

    String name;
    int score;
    Rack rack;

    public Player() {
        score = 0;
        rack = new Rack();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public void SetTile(Tile tile, int pos) {
        rack.set(pos, tile);
    }

    public Tile getTile(int pos) {
        return this.rack.get(pos);
    }

    @Override
    public int compare(Player o1, Player o2) {
        return o2.score - o1.score;
    }
}
