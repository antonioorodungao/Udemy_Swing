package gui;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

/**
 * Created by WFA_ORO_BH on 10/23/2015.
 */
public class ServerTreeCellRenderer implements TreeCellRenderer {

    private JCheckBox leafRenderer;
    private DefaultTreeCellRenderer nodeRenderer;

    private Color textForeground;
    private Color textBackground;
    private Color selectionForeground;
    private Color selectionBackground;

    public ServerTreeCellRenderer() {
        leafRenderer = new JCheckBox();
        nodeRenderer = new DefaultTreeCellRenderer();
        nodeRenderer.setClosedIcon(Utils.createIcon("/images/WebComponentAdd16.gif"));
        nodeRenderer.setLeafIcon(Utils.createIcon("/images/Server16.gif"));
        nodeRenderer.setOpenIcon(Utils.createIcon("/images/WebComponent16.gif"));

        textForeground = UIManager.getColor("Tree.textForeground");
        textBackground = UIManager.getColor("Tree.textBackground");
        selectionForeground = UIManager.getColor("Tree.selectionForeground");
        selectionBackground = UIManager.getColor("Tree.selectionBackground");
//        leafRenderer.setBackground(textBackground);
//        leafRenderer.setForeground(textForeground);


    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        if (leaf) {
            String servername = ((ServerInfo) ((DefaultMutableTreeNode) value).getUserObject()).getServername();
            boolean isChecked = ((ServerInfo) ((DefaultMutableTreeNode) value).getUserObject()).isChecked();
            leafRenderer.setText(servername);
            leafRenderer.setSelected(isChecked);
            if(selected){
                leafRenderer.setForeground(selectionForeground);
                leafRenderer.setBackground(selectionBackground);
            }else{
                leafRenderer.setForeground(textForeground);
                leafRenderer.setBackground(textBackground);
            }
            return leafRenderer;
        } else {
            return nodeRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        }

    }
}
