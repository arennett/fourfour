package de.ar.fourfour.model;

import de.ar.fourfour.model.cell.Cell;

public class BoardModel implements  BoardModelIf{
    private final int size;
    private Cell[][] arr;

    public BoardModel(int size){
     this.size = size;
        arr = new Cell[size][size];
    }

    public Cell getCell(int row, int col){
        assert row < size;
        assert col < size;

        return arr[row][col];
    }

    @Override
    public void setCell(int row, int col, Cell cell) {
        assert row < size;
        assert col < size;

        arr[row][col]=cell;
    }
}
