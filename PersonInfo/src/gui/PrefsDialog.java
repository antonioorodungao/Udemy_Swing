package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Oro on 8/17/2015.
 */
public class PrefsDialog extends JDialog {

    private JButton okButton;
    private JSpinner spinner;
    private SpinnerNumberModel spinnerModel;
    private JButton cancelButton;
    private JTextField username;
    private JPasswordField passwordField;
    private PreferenceListener listener;


    public PrefsDialog(JFrame parent){
        super(parent, "Preference");
        this.setModal(true);
        Dimension d = getPreferredSize();
        d.width = 300;
        d.height = 250;
        setSize(d);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        Dimension dim = cancelButton.getPreferredSize();
        okButton.setPreferredSize(dim);

        spinnerModel = new SpinnerNumberModel(3306, 1, 9999, 1);
        spinner = new JSpinner(spinnerModel);

        username = new JTextField(10);
        passwordField = new JPasswordField(10);

        layoutControls();
////////////////////////////////////////////////////////////////////

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer port = (Integer)spinner.getValue();
                String user = username.getText();
                char[] pass = passwordField.getPassword();
                System.out.println(user + new String(pass));
                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

    }


    public void addPreferenceListener(PreferenceListener listener){
        this.listener = listener;
    }

    public void setDefaults(String name, String password, int port){
        username.setText(name);
        passwordField.setText(password);
        spinnerModel.setValue(port);
    }

    private void layoutControls(){

        JPanel controlsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        controlsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        Insets spaceright = new Insets(0,0,0,15);
        Insets nospace = new Insets(0,0,0,0);

        Border title = BorderFactory.createTitledBorder("Database Preference");
        Border spaceBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, title));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
////////////////////////////////////////////////////////////////////
        gc.weighty =1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = spaceright;
        controlsPanel.add(new JLabel("Port"), gc);

        gc.gridx++;
        gc.insets = nospace;
        gc.anchor = GridBagConstraints.WEST;
        controlsPanel.add(spinner, gc);
////////////////////////////////////////////////////////////////////
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = spaceright;
        controlsPanel.add(new JLabel("Username"), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = nospace;
        controlsPanel.add(username, gc);
////////////////////////////////////////////////////////////////////
        gc.gridy++;
        gc.gridx = 0;
        gc.insets = spaceright;
        gc.anchor = GridBagConstraints.EAST;
        controlsPanel.add(new JLabel("Password"), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = nospace;
        controlsPanel.add(passwordField, gc);
////////////////////////////////////////////////////////////////////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(okButton);

        setLayout(new BorderLayout());
        add(controlsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);



    }
}
