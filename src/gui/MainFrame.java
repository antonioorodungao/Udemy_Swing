package gui;

import com.sun.javafx.scene.control.Keystroke;
import controller.Controller;
import sun.security.jgss.krb5.Krb5Util;
import sun.security.krb5.internal.ktab.KeyTabInputStream;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

/**
 * Created by Oro on 8/4/2015.
 */
public class MainFrame extends JFrame{

    private TextPanel textPanel;
    private ToolBar toolBar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;
    private TablePanel tablePanel;
    private Controller controller;
    private PrefsDialog preference;
    private Preferences prefs;


    public MainFrame(){
        super("Hello World");
        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        formPanel = new FormPanel();
        tablePanel = new TablePanel();
        controller = new Controller();
        preference = new PrefsDialog(this);


        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        setJMenuBar(createMenuBar());

        add(formPanel, BorderLayout.WEST);
      //  add(textPanel, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        tablePanel.setData(controller.getPeople());

        tablePanel.addPersonListener(new PersonTableListener() {
            public void personDeleted(int row) {
                System.out.println(row);
                controller.deletePerson(row);
            }
        });

        formPanel.setFormListener(new FormListener() {
            @Override
            public void FormEventOccurred(FormEvent e) {
                controller.addPerson(e);
                tablePanel.refresh();
            }
        });

        prefs = Preferences.userRoot().node("db");

        preference.addPreferenceListener(new PreferenceListener(){
            public void preferenceSet(String name, String password, int port){
                System.out.println();

                prefs.put("username", name);
                prefs.put("password", password);
                prefs.put("port", new Integer(port).toString());
            }
        });

        preference.setDefaults(prefs.get("username", ""), prefs.get("password", ""), Integer.valueOf(prefs.get("port", "1234")));

        setSize(new Dimension(600, 600));
        setMinimumSize(new Dimension(400,400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar createMenuBar(){
        final JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenu windowMenu = new JMenu("Window");

        JMenuItem importMenuItem = new JMenuItem("Import Data...");
        importMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        JMenuItem exportMenuItem = new JMenuItem("Export Data...");
        exportMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        exit.setMnemonic(KeyEvent.VK_X);

        fileMenu.add(importMenuItem);
        fileMenu.add(exportMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        JMenu showMenu = new JMenu("Show");
        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);
        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        JMenuItem showPreference = new JMenuItem("Preferences");
        showPreference.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        windowMenu.add(showPreference);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        importMenuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int option = fileChooser.showOpenDialog(MainFrame.this);
                if(option == JFileChooser.APPROVE_OPTION){
                    System.out.println(fileChooser.getSelectedFile());
                    try {
                        controller.loadFromFile(fileChooser.getSelectedFile());
                        tablePanel.refresh();
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        exportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = fileChooser.showSaveDialog(MainFrame.this);
                if(option == JFileChooser.APPROVE_OPTION){
                    System.out.println(fileChooser.getSelectedFile());
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile());
                        tablePanel.refresh();

                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not save to file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        showFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
                formPanel.setVisible(menuItem.isSelected());
            }
        });

        showPreference.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preference.setVisible(true);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = JOptionPane.showInputDialog(MainFrame.this, "Enter your username",
                        "Enter User Name", JOptionPane.OK_OPTION | JOptionPane.QUESTION_MESSAGE);

                int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to quit ?" + text , "Exit", JOptionPane.OK_CANCEL_OPTION);
                if(action  == JOptionPane.OK_OPTION)
                    controller.saveToDB();
                    System.exit(0);

            }
        });

        return menuBar;
    }

}
