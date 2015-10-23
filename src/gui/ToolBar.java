package gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by Oro on 8/5/2015.
 */
public class ToolBar extends JToolBar implements ActionListener{

    private JButton save;
    private JButton refresh;
    private ToolBarListener toolBarListener;

    public ToolBar(){
        //get rid of the border if you want the toolbar draggable.
       // setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        //setFloatable(false);

        save = new JButton();
        save.setToolTipText("Save");
        save.setIcon(createIcon("/images/Save24.gif"));
        refresh = new JButton();
        refresh.setToolTipText("Refresh");
        refresh.setIcon(createIcon("/images/Refresh24.gif"));

        add(save);
        addSeparator();
        add(refresh);

        save.addActionListener(this);
        refresh.addActionListener(this);

    }

    private ImageIcon createIcon(String path){
        URL url = getClass().getResource(path);

        if(url == null){
            System.out.println("Unable to load image.");
        }
        return new ImageIcon(url);
    }

    public void setToolBarListener(ToolBarListener t){
        this.toolBarListener = t;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == save){
            toolBarListener.save();
        }

        if(e.getSource() == refresh){
            toolBarListener.refresh();
        }
    }
}
