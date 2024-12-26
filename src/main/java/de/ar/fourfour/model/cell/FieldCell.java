package de.ar.fourfour.model.cell;

import de.ar.fourfour.model.FFColor;

public class FieldCell extends Cell{
    public FFColor getOccColor() {
        return occColor;
    }

    public void setOccColor(FFColor occColor) {
        this.occColor = occColor;
    }

    FFColor occColor = null;

    public FieldCell() {
        super();
    }
}
