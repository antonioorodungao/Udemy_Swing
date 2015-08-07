package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Oro on 8/4/2015.
 */
public class TextPanel extends JPanel {

    private JTextArea textArea;
    public TextPanel(){
        textArea = new JTextArea();

        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);


    }

    public void appendText(String text){
        textArea.append(text);
    }


}
