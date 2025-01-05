package de.ar.fourfour;

import de.ar.fourfour.model.BoardModel;
import de.ar.fourfour.model.BoardModelIf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import static de.ar.fourfour.ConstIf.*;

public class MainWindow extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(MainWindow.class);
    public MainWindow() throws FFException {
        super("FourFour V1.0");
        logger.info("FourFour V1.0, open MainWindow");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
        setVisible(true);
    }

    private void initUI() throws FFException {
        MessagePanel messagePanel = new MessagePanel();
        Game game= new Game(messagePanel);
        game.message("FourFour V1.0 loading...");

        GameControl gameControl=new GameControl(game);
        JPanel borderPanel = new JPanel();
        borderPanel.setBorder(new TitledBorder("BoardPanel"));

        BoardPanel boardPanel = new BoardPanel(game);
        borderPanel.add(boardPanel);
        BoardModelIf bmodel = new BoardModel(ROW_SIZE);
        gameControl.setBoardPanel(boardPanel);
        gameControl.setBoardModel(bmodel);
        boardPanel.setGameControl(gameControl);
        boardPanel.setBoardModel(bmodel);
        setLayout(new BorderLayout());
        add(borderPanel,BorderLayout.CENTER);
        add(messagePanel,BorderLayout.SOUTH);

        ButtonPanel bpanel = new ButtonPanel();
        bpanel.setGameControl(gameControl);
        add(bpanel,BorderLayout.EAST);

        pack();

    }


}
