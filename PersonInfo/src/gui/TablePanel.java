package gui;

import model.EmploymentCategory;
import model.Person;
import model.PersonTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by Oro on 8/8/2015.
 */
public class TablePanel extends JPanel {

    private JTable table;
    private PersonTableModel tableModel;
    private JPopupMenu popup;
    private PersonTableListener tableListener;

    public TablePanel(){

        tableModel = new PersonTableModel();
        table = new JTable(tableModel);
        table.setRowHeight(25);

        table.setDefaultRenderer(EmploymentCategory.class, new EmploymentCategoryCellRenderer());
        table.setDefaultEditor(EmploymentCategory.class, new EmploymentCategoryCellEditor());

        popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete");
        popup.add(removeItem);

        table.addMouseListener(new MouseAdapter() {
            @Override

            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
//                System.out.println(row);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(table, e.getX(), e.getY());
                }
            }
        });

        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(tableListener != null){
                    tableListener.personDeleted(row);
                    tableModel.fireTableRowsDeleted(row, row);
                }

            }
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<Person> db){
        tableModel.setData(db);
    }

    public void refresh(){
        tableModel.fireTableDataChanged();
    }

    public void addPersonListener(PersonTableListener listener){
        tableListener = listener;
    }



}
