package de.ar.fourfour;

import java.awt.*;

public enum FFColor {
    RED,BLUE,YELLOW,GREEN;

    static final String[] STR_COLORS = {"red","blue","yellow","green"};

    static final String[] SHORTSTR_COLORS = {"r","b","y","g"};

    static final Color[] COLORS = {Color.red,Color.blue,Color.yellow,Color.green};

    public static FFColor getFFColor(String shortStrCol){
        for (int i=0; i < SHORTSTR_COLORS.length;i++){
            if (SHORTSTR_COLORS[i].equals(shortStrCol)){
                return FFColor.values()[i];
            }
        }
        return null;
    }


    public Color getColor(){
        return COLORS[ordinal()];
    }

}
