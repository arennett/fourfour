package de.ar.fourfour.model.cell;

import java.util.Iterator;
import java.util.function.Consumer;

public class NeighBoursIterator implements Iterable<Cell> {

    private final Cell cell;

    public NeighBoursIterator(Cell cell) {
        assert cell!=null;
        assert cell.neighbours.size() > 0;
        this.cell = cell;
    }

    @Override
    public Iterator<Cell> iterator() {
        Iterator<Cell> it = new Iterator<Cell>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < cell.neighbours.size();
            }

            @Override
            public Cell next() {
                Cell ncell = cell.neighbours.get(i);
                i++;
                return ncell;
            }
        };
        return it;
    }

    @Override
    public void forEach(Consumer<? super Cell> action) {
        Iterable.super.forEach(action);
    }
}
