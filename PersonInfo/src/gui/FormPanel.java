package gui;

import model.EmploymentCategory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Oro on 8/5/2015.
 */
public class FormPanel extends JPanel{

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private FormListener listener;
    private JList ageList;
    private JComboBox empCombo;
    private JCheckBox citizenCheck;
    private JTextField taxField;
    private JLabel taxLabel;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;


    public FormPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        ageList = new JList();
        empCombo = new JComboBox(EmploymentCategory.values());
        empCombo.setEditable(true);
        citizenCheck = new JCheckBox("US Citizen");
        citizenCheck.setSelected(false);
        taxField = new JTextField(10);
        taxField.setEnabled(false);
        taxLabel = new JLabel("Tax ID:");

        maleRadio = new JRadioButton("Male");
        maleRadio.setActionCommand("Male");
        femaleRadio = new JRadioButton("Female");
        femaleRadio.setActionCommand("Female");
        genderGroup = new ButtonGroup();

        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.setSelected(maleRadio.getModel(), true);
//      List box
        DefaultListModel dm = new DefaultListModel();
        dm.addElement(new AgeCategory(0,"Under 18"));
        dm.addElement(new AgeCategory(1,"18 to 65"));
        dm.addElement(new AgeCategory(2,"65 or over"));

        ageList.setModel(dm);
        ageList.setPreferredSize(new Dimension(110,65));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(0);

        //Setup combo box

        okBtn = new JButton("OK");

        okBtn.setMnemonic(KeyEvent.VK_O);
        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        Border compound = BorderFactory.createCompoundBorder(outerBorder, innerBorder);
        setBorder(compound);

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0;
        gc.weighty = .5;

        gc.gridx = 0;
        gc.gridy = 0;

        //Row
        gc.insets = new Insets(0,0,0,5);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;

        add(nameField, gc);

        //Row
        gc.gridy++;
        gc.gridx = 0;

        gc.anchor = GridBagConstraints.LINE_END;
        add(occupationLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(occupationField, gc);

        //Row
        gc.gridy++;
        gc.gridx = 1;

        gc.anchor = GridBagConstraints.FIRST_LINE_START;

        add(ageList, gc);


        //Row
        gc.gridy++;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(empCombo, gc);

        //Row
        gc.gridy++;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(citizenCheck, gc);

        //Row
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(taxLabel, gc);

        gc.gridx = 1;
        add(taxField, gc);

        //Row
        gc.gridy++;
        gc.gridx = 0;
        gc.weighty = .1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(new JLabel("Gender"), gc);

        gc.gridx = 1;
        add(maleRadio, gc);

        //Row
        gc.gridy++;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(femaleRadio, gc);

        //Row

        gc.weightx = 0;
        gc.weighty = 2;

        gc.gridy++;
        gc.gridx = 1;


        add(okBtn, gc);



        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedAgeCat = ((AgeCategory)ageList.getSelectedValue()).getId();
                String name = nameField.getText();
                String occupation = occupationField.getText();
                int empcat =  empCombo.getSelectedIndex();
                boolean usCitizen = citizenCheck.isSelected();
                String taxId = taxField.getText();
                String gender = genderGroup.getSelection().getActionCommand();
                FormEvent fe = new FormEvent(this, name, occupation, selectedAgeCat, empcat, usCitizen, taxId, gender);
                listener.FormEventOccurred(fe);
            }
        });

        citizenCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isChecked = ((JCheckBox)e.getSource()).isSelected();
                taxField.setEnabled(isChecked);
            }
        });



    }

    public void setFormListener(FormListener f){
        this.listener = f;
    }
}

class AgeCategory{
    private int id;
    private String name;
    public AgeCategory(int id , String name){
        this.id = id;
        this.name = name;
    }

    public String toString(){
        return name;
    }

    public int getId() {
        return id;
    }
}
