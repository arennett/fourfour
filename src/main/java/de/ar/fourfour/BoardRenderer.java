package de.ar.fourfour;

import de.ar.fourfour.model.BoardModel;
import de.ar.fourfour.model.BoardModelIf;
import de.ar.fourfour.model.cell.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.*;
import static de.ar.fourfour.ConstIf.*;

public class BoardRenderer implements BoardRendererIf{
    Logger logger = LoggerFactory.getLogger(BoardRenderer.class);
    @Override
    public void render(Graphics g, BoardModelIf bm) {



        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4*CELL_MARGIN));

        g2d.clearRect(0,0,BOARD_LENGTH,BOARD_LENGTH);
        g2d.setColor(Color.darkGray);
        g2d.fillRect(0,0,BOARD_LENGTH,BOARD_LENGTH);

        for (int row=0;row < ROW_SIZE;row++){
            for (int col=0;col < ROW_SIZE;col++){
                Cell cell=bm.getCell(row,col);
                renderCell(cell,row,col,g2d);
              }
        }
        int w = CELL_WIDTH * (ROW_SIZE-2) ;

        g2d.drawRect(CELL_WIDTH,CELL_WIDTH,w,w);
        w = CELL_WIDTH * (ROW_SIZE-4) ;
        g2d.drawRect(CELL_WIDTH*2,CELL_WIDTH*2,w,w);

    }

    private void renderCell(Cell cell, int row, int col,Graphics2D g2d) {
        Color cellColor = null;
        if (cell instanceof EmptyCell) {
            cellColor = Color.lightGray;
        } else if (cell instanceof GenCell) {
            GenCell genCell = (GenCell) cell;
            cellColor = Color.lightGray;
            if (genCell.getFFColor() != null) {
                cellColor = genCell.getFFColor().getColor();
            }
        } else if (cell instanceof StopCell) {
            cellColor = Color.DARK_GRAY;
        } else if (cell instanceof FieldCell) {
            FieldCell fc =(FieldCell)cell;
            if (fc.getOccColor()!=null){
                cellColor=fc.getOccColor().getColor();
            }else{
                cellColor = new Color(120,120,0);
            }
        }
        if (cell == null) {
            cellColor = Color.lightGray;
        } else {
            if (cell.isHighLight()) {
                cellColor = Color.orange;
            }
        }
        int x = col * CELL_WIDTH + CELL_MARGIN;
        int y = row * CELL_WIDTH + CELL_MARGIN;

        g2d.setStroke(new BasicStroke(6));
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x, y, CELL_WIDTH - 2 * CELL_MARGIN, CELL_WIDTH - 2 * CELL_MARGIN);


        g2d.setColor(cellColor);
        g2d.fillOval(x+2*CELL_MARGIN, y+2*CELL_MARGIN, CELL_WIDTH - 6 * CELL_MARGIN, CELL_WIDTH - 6 * CELL_MARGIN);


        g2d.drawString("" + row + "/" + col, x + 10, y + 10);
        g2d.setColor(Color.BLACK);
        if (cell instanceof GenCell) {
            g2d.fillOval(x+3*CELL_MARGIN, y+3*CELL_MARGIN, CELL_WIDTH - 8 * CELL_MARGIN, CELL_WIDTH - 8 * CELL_MARGIN);
        }else if (cell instanceof FieldCell){
            FieldCell fc = (FieldCell) cell;
            if (((FieldCell) cell).isOccupied()) {
                g2d.setColor(Color.gray);
                g2d.setStroke(new BasicStroke(1));
                g2d.drawOval(x+4*CELL_MARGIN, y+4*CELL_MARGIN, CELL_WIDTH - 10 * CELL_MARGIN, CELL_WIDTH - 10 * CELL_MARGIN);

            }



        }
    }
}
