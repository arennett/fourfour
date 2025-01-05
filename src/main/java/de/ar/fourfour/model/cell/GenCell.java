package de.ar.fourfour.model.cell;

import de.ar.fourfour.FFColor;
import de.ar.fourfour.model.BoardModelIf;

public class GenCell extends Cell{
    enum Orientation {upper,left,bottom,right}

    private FFColor ffColor=null;
    public Direction fieldDirection=null;

    public void initFieldDirection(){
            NeighBoursIterator it = new NeighBoursIterator(this);
            for (Cell cell:it){
                if (cell instanceof FieldCell){
                    fieldDirection = cell.getDirection(this);
                    break;
                }
            }
    }


    public FFColor getFFColor() {
        return ffColor;
    }

    public void setFFColor(FFColor ffColor) {
        this.ffColor = ffColor;
    }


    public GenCell(int row, int col, BoardModelIf bmodel) {
        super(row,col,bmodel);
    }

    public FieldCell getFieldCell() {
        for (Cell cell:getNeighbours()){
            if (cell instanceof FieldCell) {
                return (FieldCell) cell;
            }
        }
        return null;
    };


    public FieldCell getLastFieldCell() {
        Cell itCell=this;
        while (itCell.getNeighbour(fieldDirection) !=null){
            Cell neigh =itCell.getNeighbour(fieldDirection);
            if(neigh instanceof FieldCell){
                FieldCell fneigh =(FieldCell) neigh;
                if(!fneigh.isOccupied()){
                    itCell= fneigh;
                }else{
                    break;
                }
            }else {
                break;
            }

        }
        if(itCell instanceof FieldCell){
            return (FieldCell) itCell;
        }
        return null;
    };

}
