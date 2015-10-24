package gui;

import controller.MessageServer;
import model.Message;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

/**
 * Created by WFA_ORO_BH on 10/23/2015.
 */


public class MessagePanel extends JPanel  implements ProgressDialogListener{

    private JTree serverTree;
    private ServerTreeCellRenderer treeCellRenderer;
    private ServerTreeCellEditor treeCellEditor;
    private Set<Integer> selectedServers;
    private MessageServer messageServer;
    private ProgressDialog progressDialog;
    SwingWorker<List<Message>, Integer> worker;
    private JSplitPane upperPane;
    private JSplitPane lowerPane;
    private TextPanel textPanel;
    private JList messageList;

    public MessagePanel(Window parent) {

        messageServer = new MessageServer();
        selectedServers = new TreeSet<Integer>();
        selectedServers.add(0);
        selectedServers.add(1);
        selectedServers.add(4);
        treeCellRenderer = new ServerTreeCellRenderer();
        treeCellEditor = new ServerTreeCellEditor();
        serverTree = new JTree(createTree());
        progressDialog = new ProgressDialog(parent);
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(treeCellEditor);
        serverTree.setEditable(true);



        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

//        serverTree.addTreeSelectionListener(new TreeSelectionListener() {
//            @Override
//            public void valueChanged(TreeSelectionEvent e) {
//                DefaultMutableTreeNode node = (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent();
//                ServerInfo userObject = (ServerInfo)node.getUserObject();
//
//                System.out.println(userObject);
//            }
//        });

        treeCellEditor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                ServerInfo info = (ServerInfo) treeCellEditor.getCellEditorValue();
                System.out.println("info " + info.toString());
                if(info.isChecked()) {
                    selectedServers.add(info.getServerID());
                }else{
                    selectedServers.remove(info.getServerID());
                }

                messageServer.setSelectServers(selectedServers);
                System.out.println("Messages waiting:" + messageServer.getMessageCount());

                retrieveMessages();
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });

        progressDialog.setProgressDialogListener(this);

        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        messageList = new JList();

        lowerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, messageList, textPanel);
        upperPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(serverTree), lowerPane);


        //add(new JScrollPane(serverTree), BorderLayout.CENTER);
        textPanel.setMinimumSize(new Dimension(10,150));
        messageList.setMinimumSize(new Dimension(10,100));

        //ratio of lowerpane and upperpane
        lowerPane.setResizeWeight(0.5);
        upperPane.setResizeWeight(0.5);
        add(upperPane);

    }

    private void retrieveMessages(){

        worker = new SwingWorker<List<Message>, Integer>() {

            @Override
            protected void process(List<Integer> counts) {
                int retrieved = counts.get(counts.size()-1);
                progressDialog.setValue(retrieved + 1);
                System.out.println("Got " + retrieved + " messages");

            }

            @Override
            protected void done() {
                if(isCancelled())
                    return;
                try {
                    List<Message> retrievedMessage = get();
                    System.out.println("Retrieved " + retrievedMessage.size() + " messages.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                progressDialog.setVisible(false);
            }

            @Override
            protected List<Message> doInBackground() throws Exception {

                List<Message> retrievedMessages = new ArrayList<Message>();
                int count = 0;
                progressDialog.setMinimum(0);
                progressDialog.setMaximum(messageServer.getMessageCount());
                if(messageServer.getMessageCount() > 0)
                 progressDialog.setVisible(true);

                for(Message message : messageServer){
                    if(isCancelled()){
                        break;
                    }
                    retrievedMessages.add(message);
                    publish(count++);
                }
                return retrievedMessages;

            }
        };


        worker.execute();
//        for(Message message : messageServer){
//            System.out.println(message.getTitle());
//        }
    }

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 0,  selectedServers.contains(0)));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 1,  selectedServers.contains(1)));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 2,  selectedServers.contains(2)));

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London", 3,  selectedServers.contains(3)));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh", 6,  selectedServers.contains(4)));
        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);
        return top;
    }

    @Override
    public void cancelProgress() {
        if(worker != null) {
            System.out.println("Canceled...");
            worker.cancel(true);
            progressDialog.setVisible(false);
        }
    }

}



