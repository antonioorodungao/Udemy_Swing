package gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Oro on 8/5/2015.
 */
public class ToolBar extends JPanel implements ActionListener{

    private JButton helloBtn;
    private JButton goodbyeBtn;
    private ToolBarListener toolBarListener;

    public ToolBar(){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        helloBtn = new JButton("Hello");
        goodbyeBtn = new JButton("Goodbye");

        add(helloBtn);
        add(goodbyeBtn);

        helloBtn.addActionListener(this);
        goodbyeBtn.addActionListener(this);

    }

    public void setToolBarListener(ToolBarListener t){
        this.toolBarListener = t;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == helloBtn){
            toolBarListener.emitText("Hello");
        }

        if(e.getSource() == goodbyeBtn){
            toolBarListener.emitText("Goodbye.");
        }
    }
}
