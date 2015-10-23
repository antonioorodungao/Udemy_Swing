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
    int serverID;
    String servername;

    public ServerInfo(String name, int id){
        this.servername = name;
        this.serverID = id;
    }

    public String toString(){
        return serverID + ":" + servername;
    }


}
public class MessagePanel extends JPanel {

    private JTree serverTree;
    private DefaultTreeCellRenderer treeCellRenderer;

    public MessagePanel() {

        treeCellRenderer = new DefaultTreeCellRenderer();
        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);

        treeCellRenderer.setClosedIcon(Utils.createIcon("/images/WebComponentAdd16.gif"));
        treeCellRenderer.setLeafIcon(Utils.createIcon("/images/Server16.gif"));
        treeCellRenderer.setOpenIcon(Utils.createIcon("/images/WebComponent16.gif"));

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

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode(new ServerInfo("USA", 1));
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 2));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 3));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 4));

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode(new ServerInfo("UK",5));
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London", 6));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh", 7));
        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);
        return top;
    }

}



