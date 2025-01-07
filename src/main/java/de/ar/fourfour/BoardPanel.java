package de.ar.fourfour;

import de.ar.fourfour.model.BoardModelIf;
import de.ar.fourfour.model.BoardModelReaderIf;
import de.ar.fourfour.model.cell.BoardModelReader;
import de.ar.fourfour.model.cell.Cell;
import de.ar.fourfour.model.cell.FieldCell;
import de.ar.fourfour.model.cell.GenCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static de.ar.fourfour.ConstIf.*;

public class BoardPanel extends JPanel implements MouseListener, MouseMotionListener {

    private final Game game;
    Logger logger = LoggerFactory.getLogger(BoardPanel.class);
    JPanel innerBoard;
    BoardRendererIf boardRenderer;

    public void setBoardModel(BoardModelIf boardModel) {
        this.boardModel = boardModel;
    }

    BoardModelIf boardModel;

    BoardModelReaderIf boardModelReader;
    private GameControl gameControl;

    private Cell cellMoved = null;

    public BoardPanel(Game game) throws FFException {
        this.game = game;
        initUi();
        logger.debug("initialized");
    }

    public void setGameControl(GameControl gameControl) {

        this.gameControl = gameControl;
    }

    private void initUi() throws FFException {

        boardModelReader = new BoardModelReader();
        boardRenderer = new BoardRenderer();
        setPreferredSize(new Dimension(BOARD_LENGTH, BOARD_LENGTH));
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void start() throws FFException {
        boardModel.clean();
        try {
            boardModelReader.readModel(boardModel, "main");
            boardModel.setTurn(game.getTurn());
            repaint();
        } catch (Exception e) {
            throw new FFException("readModel failed", e);
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        boardRenderer.render(g, boardModel);
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        logger.debug(" mouseClicked: x <{}> y <{}>", x, y);
        int row = y * ROW_SIZE / BOARD_LENGTH;
        int col = x * ROW_SIZE / BOARD_LENGTH;
        cellClicked(row, col);


    }

    private void cellClicked(int row, int col) {
        logger.debug(" cellClicked: row <{}> col <{}>", row, col);
        Cell cell = boardModel.getCell(row, col);
        if(game.isRunning()) {
            if (cell instanceof GenCell) {
                GenCell genCell = (GenCell) cell;
                if (game.getTurn().getFFColor() == genCell.getFFColor()) {
                    FieldCell fcell = genCell.getLastFieldCell();
                    if (fcell != null) {
                        fcell.setOccColor(genCell.getFFColor());
                        gameControl.switchTurn();
                    }
                    repaint();
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int row = y * ROW_SIZE / BOARD_LENGTH;
        int col = x * ROW_SIZE / BOARD_LENGTH;
        Cell cell = boardModel.getCell(row, col);
        if (cell != cellMoved) {
            cellMovedOn(cell, true);
            cellMovedOn(cellMoved, false);
            cellMoved = cell;
        }
    }

    private void cellMovedOn(Cell cell, boolean b) {
        //logger.debug(" cellMovedOn: <{} {}> ", cell, b);
        if (cell != null) {
            if (cell instanceof GenCell) {
                if (game.getTurn().getFFColor() == ((GenCell) cell).getFFColor()) {
                    cell.setHighLight(b);
                   // Rectangle rec = new Rectangle(cell.getRow() * CELL_WIDTH, cell.getCol() * CELL_WIDTH, CELL_WIDTH, CELL_WIDTH);
                    repaint();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }


}
