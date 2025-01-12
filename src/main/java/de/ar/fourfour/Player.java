package de.ar.fourfour;

import java.util.ArrayList;

public class Player {

    private final FFColor color;
    private final boolean isComp;

    public static final ArrayList<Player> playerList;

    static{
        playerList=new ArrayList<>();
        playerList.add(new Player(FFColor.RED, false));
        playerList.add(new Player(FFColor.BLUE,true));
    }

    public Player(FFColor color, boolean isComp){
        this.color = color;
        this.isComp = isComp;
    }

    static Player getPlayer(int idx){
        return playerList.get(idx);
    }

    public static Player getNextPlayer(Player player) {
        int idx = getIdx(player);
        int nidx =(idx+1)% playerList.size();
        return getPlayer(nidx);
    }

    private static int getIdx(Player player) {
        int idx = -1;
        for (Player p:playerList){
            idx++;
            if(p == player){
                return idx;
            }
        }
        throw new RuntimeException("getIdx failed");

    }


    public FFColor getFFColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Player: "+getIdx(this);
    }
}
