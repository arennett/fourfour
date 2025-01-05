package de.ar.fourfour;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static de.ar.fourfour.ConstIf.*;

public class ButtonPanel extends JPanel implements ActionListener {

    JPanel jButtonArea;
    JButton jbStart;

    GameControl gameControl;

    public void setGameControl(GameControl gameControl) {
        this.gameControl = gameControl;
    }

    public ButtonPanel(){
        initUi();
    }

    private void initUi() {
        TitledBorder tb = new TitledBorder("Control");
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        setBorder(tb);
        jButtonArea = new JPanel();
        jButtonArea.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollPane = new JScrollPane(jButtonArea);
        this.add(scrollPane);
        this.setPreferredSize(new Dimension(100,BOARD_LENGTH));
        BoxLayout bl=new BoxLayout(jButtonArea,BoxLayout.PAGE_AXIS);

        jButtonArea.setLayout(bl);
        jbStart =new JButton("START");
        jbStart.addActionListener(this);
        jButtonArea.add(jbStart);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jbStart) {
            try {
                gameControl.start();
            } catch (FFException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
