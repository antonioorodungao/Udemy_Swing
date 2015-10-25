package gui;

import model.EmploymentCategory;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by WFA_ORO_BH on 10/25/2015.
 */
public class EmploymentCategoryCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JComboBox empCat;

    public EmploymentCategoryCellEditor(){
        empCat = new JComboBox(EmploymentCategory.values());
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        empCat.setSelectedItem(value);
        empCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });

        return empCat;
    }

    @Override
    public Object getCellEditorValue() {
        return empCat.getSelectedItem();
    }

}
