package orgataxe.model.taxe;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TaxeTableModel extends AbstractTableModel {
    protected Vector<Vector<Integer>> datas;
    protected Vector<String> columnIdentifiers;

    protected Icon deleteIcon;

    public TaxeTableModel() {
        this.datas = new Vector<>();

        this.columnIdentifiers = new Vector<>(2);
        this.columnIdentifiers.add("Année");
        this.columnIdentifiers.add("Taxe");

        fireTableStructureChanged();
    }

//
// Manipulating rows
//

    public void addRow(int year, int taxe) {
        insertRow(getRowCount(), year, taxe);
    }

    public void insertRow(int row, int year, int taxe) {
        Vector<Integer> data = new Vector<>();
        data.add(year);
        data.add(taxe);

        datas.insertElementAt(data, row);
        fireTableRowsInserted(row, row);
    }

    public void removeRow(int row) {
        datas.removeElementAt(row);
        fireTableRowsDeleted(row, row);
    }

    public void removeRows() {
        int oldRowCount = getRowCount();
        if (oldRowCount == 0) {
            return;
        }

        datas.setSize(0);
        fireTableRowsDeleted(0, oldRowCount - 1);
    }

//
// Implementing the TableModel interface
//

    @Override
    public int getRowCount() {
        return datas.size();
    }

    @Override
    public int getColumnCount() {
        return columnIdentifiers.size();
    }

    @Override
    public String getColumnName(int column) {
        String id = columnIdentifiers.elementAt(column);

        return (id != null) ? id : super.getColumnName(column);
    }

    @Override
    public Object getValueAt(int row, int column) {
        Vector<Integer> data = datas.elementAt(row);
        switch (column) {
            case 0:
                return data.get(0);
            case 1:
                return data.get(1) + "$";
            default:
                break;
        }

        return null;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        if (value == null) {
            return;
        }

        Vector<Integer> data = datas.get(row);
        switch (column) {
            case 0:
                data.set(0, (int) value);
                break;
            case 1:
                String stringValue = ((String) value);
                if (stringValue.charAt(stringValue.length() - 1) == '$') {
                    stringValue = stringValue.substring(0, stringValue.length() - 1);
                }

                data.set(1, Integer.valueOf(stringValue));
                break;
        }

        fireTableCellUpdated(row, column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
