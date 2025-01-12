package de.ar.fourfour;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {


    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private final MessagePanel mpanel;


    private Player turn=null;

    boolean running = false;

    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }

    public Player getTurn() {
        return turn;
    }

    public void setTurn(Player turn) {
        this.turn = turn;
    }


    public Game(MessagePanel mpanel) {
        this.mpanel = mpanel;
    }

    public void message (String message){
        mpanel.append(message);
    }


    public Player getNextTurn() {
        return Player.getNextPlayer(getTurn());
    }

    public void switchTurn(){
        Player p=getNextTurn();
        setTurn(p);
    }

    public void clearMessages() {
        mpanel.clear();
    }
}
