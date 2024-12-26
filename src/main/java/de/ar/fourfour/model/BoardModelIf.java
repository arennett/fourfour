package de.ar.fourfour.model;

import de.ar.fourfour.model.cell.Cell;

public interface BoardModelIf {

    Cell getCell(int ridx,int cidx);

    void setCell(int row, int col, Cell cell);
}
