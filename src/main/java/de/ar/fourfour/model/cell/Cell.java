package de.ar.fourfour.model.cell;

public class Cell {

    public Cell(){

    }

    public static Cell parseStr(String str) {
        if (str.equals("e")){
            return new EmptyCell();
        }else if (str.equals("f")){
            return new FieldCell();
        }else if (str.equals("g")){
            return new GenCell();
        }else if (str.equals("s")) {
            return new StopCell();
        }
            return null;
    }
}
