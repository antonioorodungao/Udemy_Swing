package gui;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

/**
 * Created by WFA_ORO_BH on 10/23/2015.
 */

class ServerInfo{
    private int serverID;
    private String servername;
    private boolean isChecked;

    public ServerInfo(String name, int id, boolean isChecked){
        this.servername = name;
        this.serverID = id;
        this.isChecked = isChecked;
    }

    public String getServername(){
        return servername;
    }

    public String toString(){
        return serverID + ":" + servername;
    }

    public boolean isChecked() {
        return isChecked;
    }



}
public class MessagePanel extends JPanel {

    private JTree serverTree;
    private ServerTreeCellRenderer treeCellRenderer;

    public MessagePanel() {

        treeCellRenderer = new ServerTreeCellRenderer();
        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);



        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        serverTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent();
                ServerInfo userObject = (ServerInfo)node.getUserObject();

                System.out.println(userObject);
            }
        });

        setLayout(new BorderLayout());

        add(new JScrollPane(serverTree), BorderLayout.CENTER);

    }

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode(new ServerInfo("USA", 1, true));
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 2, false));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 3, false));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 4, true));

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode(new ServerInfo("UK",5, false));
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London", 6, true));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh", 7, true));
        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);
        return top;
    }

}



