package de.ar.fourfour.model.cell;

import de.ar.fourfour.model.BoardModelIf;

import java.util.ArrayList;

public class Cell {

    private final int row;
    private final int col;
    private final BoardModelIf bmodel;
    ArrayList<Cell> neighbours = new ArrayList<>();
    private Cell neighUp = null;
    private Cell neighLeft = null;
    private Cell neighDown = null;
    private Cell neighRight = null;

    private boolean highLight;


    public enum Direction {
        up,left,down,right;
        static String[] str = {"up","left","down","right"};
        public String getString(){
            return str[this.ordinal()];
        }
    }

    protected Direction getDirection(Cell cell) {
        if(this.row < cell.row){
            return Direction.up;
        }else if(this.row > cell.row){
            return Direction.down;
        }else if(this.col < cell.col){
            return Direction.left;
        }else if(this.col > cell.col){
            return Direction.right;
        }else {
            return null;
        }
    }




    public Cell(int row, int col, BoardModelIf bmodel) {

        this.row = row;
        this.col = col;
        this.bmodel = bmodel;
    }

    public static Cell create(String shortStr, int row, int col,BoardModelIf bModel) {
        if (shortStr.equals("e")) {
            return new EmptyCell(row, col,bModel);
        } else if (shortStr.equals("f")) {
            return new FieldCell(row, col,bModel);
        } else if (shortStr.equals("g")) {
            return new GenCell(row, col,bModel);
        } else if (shortStr.equals("s")) {
            return new StopCell(row, col,bModel);
        }
        return null;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isHighLight() {
        return highLight;
    }
    public Cell getNeighbour(Direction dir) {
        Cell neigh= null;
        switch (dir){
            case left:
                neigh=neighLeft;
                break;
            case down:
                neigh=neighDown;
                break;
            case right:
                neigh=neighRight;
                break;
            case up:
                neigh=neighUp;
                break;

        }

        return neigh;
    }

    public ArrayList<Cell> getNeighbours() {
        return neighbours;
    }

    public Cell getNeighUp() {
        return neighUp;
    }

    public Cell getNeighLeft() {
        return neighLeft;
    }

    public Cell getNeighDown() {
        return neighDown;
    }

    public Cell getNeighRight() {
        return neighRight;
    }

    public void initNeighbours() {
        //up
        int nrow = row - 1;
        int ncol = col;
        neighUp = bmodel.getCell(nrow, ncol);
        //left
        nrow = row;
        ncol = col - 1;
        neighLeft = bmodel.getCell(nrow, ncol);
        //right
        nrow = row;
        ncol = col + 1;
        neighRight = bmodel.getCell(nrow, ncol);
        //down
        nrow = row + 1;
        ncol = col;
        neighDown = bmodel.getCell(nrow, ncol);
        if (neighUp != null) neighbours.add(neighUp);
        if (neighLeft != null) neighbours.add(neighLeft);
        if (neighDown != null) neighbours.add(neighDown);
        if (neighRight != null) neighbours.add(neighRight);

    }

    public String toString() {
        return String.format("<%d,%d>", row, col);
    }

    public void setHighLight(boolean b) {
        this.highLight = b;
    }

}

