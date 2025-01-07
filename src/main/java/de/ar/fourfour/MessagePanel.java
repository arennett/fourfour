package de.ar.fourfour;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import static de.ar.fourfour.ConstIf.*;

public class MessagePanel extends JPanel {

    JTextArea jTextArea;
    JScrollPane scrollPane;
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
        scrollPane = new JScrollPane(jTextArea);
        scrollPane.setAutoscrolls(true);
        jTextArea.setEditable(false);
        jTextArea.setBackground(Color.WHITE);
        this.add(scrollPane);
        this.setPreferredSize(new Dimension(BOARD_LENGTH,100));

    }

    public void append(String message) {
        Date date= new Date();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss.SSS");
        jTextArea.append(" "+df.format(date) +" "+ message+"\n");
        //scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

    }

    public void clear() {
        jTextArea.setText("");
    }
}
