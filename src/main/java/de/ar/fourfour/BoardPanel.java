package de.ar.fourfour;

import de.ar.fourfour.model.BoardModel;
import de.ar.fourfour.model.BoardModelIf;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BoardPanel extends JPanel {
    public static int  BOARD_WIDTH = 600;
    public static int ROW_SIZE = 15;

    public static int MAX_ROW_IDX = ROW_SIZE-1;

    //public static  int BOARD_OFFSET = BOARD_WIDTH / 30;

    public static int CELL_WIDTH   = BOARD_WIDTH / ROW_SIZE;
    public static int CELL_MARGIN   = CELL_WIDTH / 20;


    private final Game game;

    JPanel innerBoard;
    BoardRendererIf boardRenderer;
    BoardModelIf boardModel;


    public BoardPanel(Game game){
        this.game =game;
        initUi();
    }

    private void initUi() {
        boardModel = new BoardModel(ROW_SIZE);
        boardRenderer = new BoardRenderer();
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_WIDTH));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        boardRenderer.render(g,boardModel);
    }
}
