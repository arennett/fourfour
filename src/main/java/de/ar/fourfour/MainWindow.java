package de.ar.fourfour;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(MainWindow.class);
    public MainWindow(){
        super("FourFour V1.0");
        logger.info("FourFour V1.0, open MainWindow");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        MessagePanel messagePanel = new MessagePanel();
        Game game= new Game(messagePanel);
        BoardPanel boardPanel = new BoardPanel(game);

        setLayout(new BorderLayout());
        add(boardPanel,BorderLayout.CENTER);
        add(messagePanel,BorderLayout.SOUTH);
        pack();
        game.message("FourFour V1.0 loading");
    }


}
