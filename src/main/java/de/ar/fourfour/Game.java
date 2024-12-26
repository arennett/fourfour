package de.ar.fourfour;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {

    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private final MessagePanel mpanel;

    public Game(MessagePanel mpanel) {
        this.mpanel = mpanel;
    }

    public void message (String message){
        mpanel.append(message);
    }


}
