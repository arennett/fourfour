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
import java.awt.event.*;

import static de.ar.fourfour.ConstIf.*;

public class BoardPanel extends JPanel  {

    private final Game game;
    final Logger logger = LoggerFactory.getLogger(BoardPanel.class);

    BoardRendererIf boardRenderer;

    public void setBoardModel(BoardModelIf boardModel) {
        this.boardModel = boardModel;
    }

    BoardModelIf boardModel;

    BoardModelReaderIf boardModelReader;
    private GameControl gameControl;

    private Cell lastCellMovedOn = null;

    public BoardPanel(Game game)  {
        this.game = game;
        initUi();
        logger.debug("initialized");
    }

    public void setGameControl(GameControl gameControl) {

        this.gameControl = gameControl;
    }

    private void initUi()  {

        boardModelReader = new BoardModelReader();
        boardRenderer = new BoardRenderer();
        setPreferredSize(new Dimension(BOARD_LENGTH, BOARD_LENGTH));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                logger.debug(" mouseClicked: x <{}> y <{}>", x, y);
                int row = y * ROW_SIZE / BOARD_LENGTH;
                int col = x * ROW_SIZE / BOARD_LENGTH;
                cellClicked(row, col);
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = (int) (e.getX());
                int y = (int) (e.getY());
                //String              s = env.get( "DISPLAY_SCALE" );
                int row = y * ROW_SIZE / BOARD_LENGTH;
                int col = x * ROW_SIZE / BOARD_LENGTH;
                Cell cell = boardModel.getCell(row, col);
                if (cell == lastCellMovedOn){
                    return;
                }
                cellMovedOff(lastCellMovedOn);
                cellMovedOn(cell);

                lastCellMovedOn =cell;

            }
        });
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



    private void cellClicked(int row, int col) {
        logger.debug(" cellClicked: row <{}> col <{}>", row, col);
        Cell cell = boardModel.getCell(row, col);
        if(game.isRunning()) {
            if (cell instanceof GenCell genCell) {
                if (game.getTurn().getFFColor() == genCell.getFFColor()) {
                    if (genCell.getFieldCell()!=null) {
                        FieldCell fCell = genCell.getLastFieldCell();
                        if (fCell != null) {
                            fCell.setOccColor(genCell.getFFColor());
                            gameControl.switchTurn();
                        }
                    }
                    repaint();
                }
            }
        }
    }



    private void cellMovedOn(Cell cell) {
        logger.debug(" cellMovedOn: <{}> ", cell);
        boardModel.setCellMovedOn(cell);
        if (cell != null) {

            if (cell instanceof GenCell) {
                if (game.getTurn().getFFColor() == ((GenCell) cell).getFFColor()) {
                      cell.setHighLight(true);
                }
            }
            Rectangle rec = new Rectangle(cell.getCol() * CELL_WIDTH, cell.getRow() * CELL_WIDTH, CELL_WIDTH, CELL_WIDTH);

            repaint(rec);
        }

    }
    private void cellMovedOff(Cell cell) {
        logger.debug(" cellMovedOff: <{}> ", cell);
        boardModel.setCellMovedOff(cell);
        if (cell != null) {
            if (cell instanceof GenCell) {
                if (game.getTurn().getFFColor() == ((GenCell) cell).getFFColor()) {
                    cell.setHighLight(false);
                }
            }
            Rectangle rec = new Rectangle(cell.getCol() * CELL_WIDTH, cell.getRow() * CELL_WIDTH, CELL_WIDTH, CELL_WIDTH);
            repaint(rec);
        }

    }



}
