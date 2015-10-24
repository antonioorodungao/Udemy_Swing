package gui;

import model.Message;

import javax.swing.*;
import java.awt.*;

/**
 * Demonstrates arbitrary components a part of MessageList Renderer. Probably, overkill.
 */
public class MessageListRenderer implements ListCellRenderer {

    private JLabel label;
    private JPanel panel;
    private Color selectedColor;
    private Color normalColor;

    public MessageListRenderer(){

        label = new JLabel();
        label.setIcon(Utils.createIcon("/images/Information16.gif"));
        panel = new JPanel();

        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        selectedColor = Color.MAGENTA;
        normalColor = Color.GRAY;

        panel.add(label);

    }
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Message message = (Message)value;
        label.setText(message.getTitle());

        panel.setBackground(normalColor);
        if(isSelected){
            panel.setBackground(selectedColor);
        }

        return panel;



    }
}
