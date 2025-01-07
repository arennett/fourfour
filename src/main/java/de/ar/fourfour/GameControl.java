package de.ar.fourfour;

import de.ar.fourfour.model.BoardModelIf;
import de.ar.fourfour.model.cell.CellGroup;
import de.ar.fourfour.model.cell.CellGroupIterator;

import java.util.Iterator;

public class GameControl {


    BoardPanel boardPanel;



    BoardModelIf boardModel;
    private Game game;

    public GameControl(Game game){
        this.game = game;
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }
    public void setBoardModel(BoardModelIf boardModel) {
        this.boardModel = boardModel;
    }
    public void start() throws FFException {
        Player player0 =null;
        game.setRunning(true);
        player0 = Player.getPlayer(0);
        game.setTurn(player0);
        boardPanel.start();

        game.clearMessages();
        game.message("Game started");
    }

    public void switchTurn() {
        if (!checkForFour()){
            game.message("switch turn to "+game.getNextTurn());
            game.switchTurn();
            boardModel.setTurn(game.getTurn());
            boardPanel.repaint();
        }else{
            game.setRunning(false);
            game.message(""+game.getTurn().getFFColor()+" YOU WIN");
        }
    }

    private boolean checkForFour() {
        CellGroupIterator cgit = new CellGroupIterator(boardModel,
                boardModel.getOccupiedFieldCells(game.getTurn().getFFColor()));
        Iterator<CellGroup> it = cgit.iterator();
        while (it.hasNext()) {
            CellGroup cgroup = it.next();
            if(cgroup.isFFWinGroup(game.getTurn().getFFColor())){
                return true;
            }
        }
        return false;
    }

    private boolean animateCheckForFour() {
        CellGroupIterator cgit = new CellGroupIterator(boardModel,boardModel.getOccupiedFieldCells(game.getTurn().getFFColor()));
        Iterator<CellGroup> it = cgit.iterator();

        Thread t = new Thread() {

            @Override
            public void run() {
                CellGroup old=null;
                while (it.hasNext()){
                    CellGroup cgroup =it.next();

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if(old!=null){
                        old.setHighLight(false);
                    }
                    cgroup.setHighLight(true);

                    boardPanel.repaint();
                    old=cgroup;
                }
            }
        };
        t.start();

        return false;
    }
}
