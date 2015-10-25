package gui;

import model.EmploymentCategory;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by Oro on 10/25/2015.
 */
public class EmploymentCategoryCellRenderer implements TableCellRenderer {

    private JComboBox empCat;

    public EmploymentCategoryCellRenderer(){
        empCat = new JComboBox(EmploymentCategory.values());
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        empCat.setSelectedItem(value);
        return empCat;
    }
}
