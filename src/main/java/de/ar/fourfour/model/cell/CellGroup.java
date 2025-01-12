package de.ar.fourfour.model.cell;

import de.ar.fourfour.FFColor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

import static de.ar.fourfour.ConstIf.*;

public class CellGroup implements Iterable<Cell> {
    final ArrayList<Cell> cells;

    public CellGroup(Cell startCell, Orientation orientation) {
        cells = new ArrayList<>(WIN_GROUP_SIZE);
        Cell itCell = startCell;
        for (int i = 0; i < WIN_GROUP_SIZE; i++) {
            if (itCell != null) {
                cells.add(itCell);
                if (orientation.equals(Orientation.HORIZONTAL)) {
                    itCell = itCell.getNeighRight();
                } else {
                    itCell = itCell.getNeighDown();
                }
            } else {
                break;
            }
        }
    }

    public void setHighLight(boolean b) {
        for (Cell cell : this) {
            cell.setHighLight(b);
        }
    }

    @Override
    public Iterator<Cell> iterator() {
        Iterator<Cell> it = new Iterator<Cell>() {
            int idx = 0;

            @Override
            public boolean hasNext() {
                return idx < cells.size();
            }

            @Override
            public Cell next() {
                Cell next = null;
                if (hasNext()) {
                    next = cells.get(idx);
                }
                idx++;
                return next;
            }
        };
        return it;
    }

    @Override
    public void forEach(Consumer<? super Cell> action) {
        Iterable.super.forEach(action);
    }

    public boolean isFieldCellGroup() {
        boolean isFieldCellGroup = false;
        if (cells.size() < WIN_GROUP_SIZE) {
            return false;
        }
        for (Cell cell : cells) {
            if (!(cell instanceof FieldCell)) {
                return false;
            }
        }
        return true;
    }

    public boolean isFFWinGroup(FFColor ffcolor) {
        boolean isFFGroup = false;
        if (!isFieldCellGroup()) {
            return false;
        }
        for (Cell cell : cells) {
            FieldCell fcell = (FieldCell) cell;
            if (fcell.getOccColor() != ffcolor) {
                return false;
            }
        }
        return true;
    }

    public enum Orientation {HORIZONTAL, VERTICAL}

}
