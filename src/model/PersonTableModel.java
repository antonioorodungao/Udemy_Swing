package model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Oro on 8/8/2015.
 */
public class PersonTableModel extends AbstractTableModel {

    private  List<Person> db;

    public void setData(List<Person> people){
        this.db = people;
    }

    private String[] col = {"ID", "Name", "Occupation","Age Category", "Employment Category",
    "US Citizen", "Tax ID", "Gender"};

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return col.length;
    }

    @Override
    public String getColumnName(int column) {
        return col[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person p = db.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getName();
            case 2:
                return p.getOccupation();
            case 3:
                return p.getAgecat();
            case 4:
                return p.getEmpcat();
            case 5:
                return p.isUsCitizen();
            case 6:
                return p.getTaxID();
            case 7:
                return p.getGender();
            default:
                return null;
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Person p = db.get(rowIndex);
        switch (columnIndex){
            case 1:
                p.setName((String)aValue);
                break;
            case 5:
                p.setUsCitizen((Boolean)aValue);
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        switch(columnIndex){
            case 1:
                return true;
            case 4:
                return true;
            case 5:
                return true;
            default:
                return false;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return EmploymentCategory.class;
            case 5:
                return Boolean.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            default:
                return null;
        }
    }
}
