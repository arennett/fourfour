package de.ar.fourfour;

import de.ar.fourfour.model.BoardModelIf;

import javax.swing.*;
import java.awt.*;

import static de.ar.fourfour.BoardPanel.*;

public class BoardRenderer implements BoardRendererIf{
    @Override
    public void render(Graphics g, BoardModelIf bm) {
        Graphics2D g2d = (Graphics2D) g;
        ((Graphics2D) g).setStroke(new BasicStroke(8));

        g2d.drawRect(50,50,BOARD_WIDTH-100,BOARD_WIDTH-100);
        g2d.drawRect(80,80,BOARD_WIDTH-160,BOARD_WIDTH-160);

        for (int row=0;row < ROW_SIZE;row++){
            for (int col=0;col < ROW_SIZE;col++){
                if (row==0||col==0||row==MAX_ROW_IDX || col == MAX_ROW_IDX ){
                    g2d.setColor(Color.lightGray);
                    g2d.fillRect(col*CELL_WIDTH+CELL_MARGIN,row*CELL_WIDTH+CELL_MARGIN,CELL_WIDTH-2*CELL_MARGIN,CELL_WIDTH-2*CELL_MARGIN);
                }else if (row==1||col==1||row==MAX_ROW_IDX-1 || col == MAX_ROW_IDX-1 ){
                    g2d.setColor(Color.red);
                    g2d.fill3DRect(col*CELL_WIDTH+CELL_MARGIN,row*CELL_WIDTH+CELL_MARGIN,CELL_WIDTH-2*CELL_MARGIN,CELL_WIDTH-2*CELL_MARGIN,true);
                }else {
                    g2d.setColor(Color.green);
                    g2d.fillRect(col*CELL_WIDTH+CELL_MARGIN,row*CELL_WIDTH+CELL_MARGIN,CELL_WIDTH-2*CELL_MARGIN,CELL_WIDTH-2*CELL_MARGIN);
                }
              }
        }


    }

    void drawGrid(){


    }
}
