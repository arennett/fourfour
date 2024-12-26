package de.ar.fourfour;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessagePanel extends JPanel {
    public static int PANEL_HEIGHT = 200;
    public static int PANEL_WIDTH = PANEL_HEIGHT / 3 * 4;

    JTextArea jTextArea;

    public MessagePanel(){
        initUi();
    }

    private void initUi() {
        TitledBorder tb = new TitledBorder("MessagePanel");
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        setBorder(tb);
        jTextArea = new JTextArea();
        jTextArea.setRows(20);
        jTextArea.setFont(new Font("Courier", Font.BOLD, 12));
        DefaultCaret caret = (DefaultCaret) jTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane scrollPane = new JScrollPane(jTextArea);
        scrollPane.setAutoscrolls(true);
        jTextArea.setEditable(false);
        jTextArea.setBackground(Color.WHITE);
        this.add(scrollPane);
        this.setPreferredSize(new Dimension(BoardPanel.BOARD_WIDTH,100));

    }


    public void append(String message) {
        Date date= new Date();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss.SSS");
        jTextArea.append(" "+df.format(date) +" "+ message);

    }
}
