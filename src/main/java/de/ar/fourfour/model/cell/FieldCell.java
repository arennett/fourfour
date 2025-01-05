package de.ar.fourfour.model.cell;

import de.ar.fourfour.FFColor;
import de.ar.fourfour.model.BoardModelIf;

public class FieldCell extends Cell{
    public FieldCell(int row, int col, BoardModelIf bModel) {
        super(row,col,bModel);
    }

    public FFColor getOccColor() {
        return occColor;
    }

    public void setOccColor(FFColor occColor) {
        this.occColor = occColor;
    }

    FFColor occColor = null;


    public boolean isOccupied() {
        return getOccColor()!=null;
    }
}
