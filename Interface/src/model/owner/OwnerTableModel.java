package model.owner;

import orgataxe.entity.Owner;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class OwnerTableModel extends AbstractTableModel {
    protected Vector<Owner> owners;
    protected Vector<String> columnIdentifiers;

    public OwnerTableModel() {
        this.owners = new Vector<>();

        this.columnIdentifiers = new Vector<>(4);
        this.columnIdentifiers.add("Prenom");
        this.columnIdentifiers.add("Nom");
        this.columnIdentifiers.add("Adresse");
        this.columnIdentifiers.add("");

        fireTableStructureChanged();
    }

//
// Manipulating rows
//

    public Owner getOwnerAt(int row) {
        return owners.get(row);
    }

    public void addRow(Owner owner) {
        insertRow(getRowCount(), owner);
    }

    public void insertRow(int row, Owner owner) {
        owners.insertElementAt(owner, row);
        fireTableRowsInserted(row, row);
    }

    public void removeRow(int row) {
        owners.removeElementAt(row);
        fireTableRowsDeleted(row, row);
    }

    public void removeRows() {
        int oldRowCount = getRowCount();
        if (oldRowCount == 0) {
            return;
        }

        owners.setSize(0);
        fireTableRowsDeleted(0, oldRowCount - 1);
    }

//
// Implementing the TableModel interface
//

    @Override
    public int getRowCount() {
        return owners.size();
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
        Owner owner = owners.elementAt(row);
        switch (column) {
            case 0:
                return owner.getFirstName();
            case 1:
                return owner.getFamilyName().toUpperCase();
            case 2:
                return owner.getAddress();
            case 3:
                return null;
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

        Owner owner = getOwnerAt(row);
        switch (column) {
            case 0:
                owner.setFirstName(value.toString());
                break;
            case 1:
                owner.setFamilyName(value.toString());
                break;
            case 2:
                owner.setAddress(value.toString());
                break;
        }

        fireTableCellUpdated(row, column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
