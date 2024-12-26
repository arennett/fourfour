package de.ar.fourfour.model;

public enum FFColor {
    RED,BLUE,YELLOW,GREEN;

    static final String[] STR_COLORS = {"red","blue","yellow","green"};

    static final String[] SHORTSTR_COLORS = {"r","b","y","g"};

    public static FFColor getFFColor(String shortStrCol){
        for (int i=0; i < SHORTSTR_COLORS.length;i++){
            if (SHORTSTR_COLORS[i].equals(shortStrCol)){
                return FFColor.values()[i];
            }
        }
        return null;
    }

}
