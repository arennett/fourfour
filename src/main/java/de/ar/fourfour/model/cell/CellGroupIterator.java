package de.ar.fourfour.model.cell;

import de.ar.fourfour.model.BoardModelIf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class CellGroupIterator implements Iterable<CellGroup>{

    BoardModelIf boardModel;
    ArrayList<FieldCell> fieldCells;
    ArrayList<CellGroup> cellGroups;
    public CellGroupIterator(BoardModelIf boardModel){

        this.boardModel = boardModel;
        fieldCells =boardModel.getFieldCells();
        cellGroups= new ArrayList<>();
        for (FieldCell fcell:fieldCells){
            CellGroup cellGroup= new CellGroup(fcell, CellGroup.Orientation.HORIZONTAL);
            if (cellGroup.isFieldCellGroup()){
                cellGroups.add(cellGroup);
            }
            cellGroup= new CellGroup(fcell, CellGroup.Orientation.VERTICAL);
            if (cellGroup.isFieldCellGroup()){
                cellGroups.add(cellGroup);
            }
        }

    }

    @Override
    public Iterator<CellGroup> iterator() {
        Iterator<CellGroup> it = new Iterator<CellGroup>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < cellGroups.size();
            }

            @Override
            public CellGroup next() {
                CellGroup next = null;
                if (hasNext()){
                    next = cellGroups.get(i);
                }
                i++;
                return next;
            }
        };
        return it;
    }

    @Override
    public void forEach(Consumer<? super CellGroup> action) {
        Iterable.super.forEach(action);
    }
}
