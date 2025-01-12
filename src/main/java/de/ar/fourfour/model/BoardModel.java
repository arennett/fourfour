package de.ar.fourfour.model;

import de.ar.fourfour.FFColor;
import de.ar.fourfour.Player;
import de.ar.fourfour.model.cell.Cell;
import de.ar.fourfour.model.cell.FieldCell;
import de.ar.fourfour.model.cell.GenCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


public class BoardModel implements  BoardModelIf{

    final Logger logger = LoggerFactory.getLogger(BoardModel.class);
    private final int size;
    private final Cell[][] arr;
    final ArrayList<Cell> list;
    private Cell cellMovedOff,cellMovedOn;

    public BoardModel(int size){
     this.size = size;
     arr = new Cell[size][size];
     list = new ArrayList<>();
     logger.debug("new BoardModel size <{}>",size);
    }

    public Cell getCell(int row, int col){
       if(row < size && row >= 0 && col >= 0 && col < size){
           return arr[row][col];
       }else{
           return null;
       }


    }

    @Override
    public void setCell(int row, int col, Cell cell) {
        assert row < size;
        assert col < size;
        list.add(cell);
        arr[row][col]=cell;
    }

    @Override
    public void clean() {
        logger.debug("clean...");
        for (int row =0;row < size;row++){
            for (int col =0;col < size;col++){
                setCell(row,col,null);
            }
        }
        list.clear();
    }

    @Override
    public ArrayList<GenCell> getGenCells(){
        ArrayList<GenCell> arrList = new ArrayList<>();
        for (Cell c:list){
            if (c instanceof GenCell) {
                arrList.add((GenCell)c);
            }
        }
        return arrList;
    }

    @Override
    public ArrayList<FieldCell> getFieldCells(){
        ArrayList<FieldCell> arrList = new ArrayList<>();
        for (Cell c:list){
            if (c instanceof FieldCell) {
                arrList.add((FieldCell)c);
            }
        }
        return arrList;
    }

    @Override
    public ArrayList<FieldCell> getOccupiedFieldCells(FFColor ffColor){
        ArrayList<FieldCell> arrList = new ArrayList<FieldCell>();
        for (FieldCell c:getFieldCells()){
            if (c.getOccColor()==ffColor) {
                arrList.add(c);
            }
        }
        return arrList;
    }

    @Override
    public void setTurn(Player player) {
        for (GenCell c:getGenCells()){
            c.setFFColor(player.getFFColor());
        }

    }

    @Override
    public void allCellsRead() {
        for (Cell cell:list){
            cell.initNeighbours();
            if( cell instanceof GenCell){
                ((GenCell)cell).initFieldDirection();
            }
        }
    }

    @Override
    public void setCellMovedOn(Cell cell) {
        this.cellMovedOn=cell;
    }

    public void setCellMovedOff(Cell cell) {
        this.cellMovedOff = cell;
    }

    @Override
    public Cell getCellMovedOn() {
        return cellMovedOn;
    }
    @Override
    public Cell getCellMovedOff() {
        return cellMovedOff;
    }
}
