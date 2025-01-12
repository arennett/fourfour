package de.ar.fourfour.model;

import de.ar.fourfour.FFColor;
import de.ar.fourfour.Player;
import de.ar.fourfour.model.cell.Cell;
import de.ar.fourfour.model.cell.FieldCell;
import de.ar.fourfour.model.cell.GenCell;

import java.util.ArrayList;

public interface BoardModelIf {

    Cell getCell(int ridx,int cidx);

    void setCell(int row, int col, Cell cell);

    void clean();

    ArrayList<GenCell> getGenCells();

    ArrayList<FieldCell> getFieldCells();

    ArrayList<FieldCell> getOccupiedFieldCells(FFColor ffColor);

    void setTurn(Player player);

    void allCellsRead();


    void setCellMovedOn(Cell cell);
    void setCellMovedOff(Cell cell);

    Cell getCellMovedOn();
    Cell getCellMovedOff();



}
