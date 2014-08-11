package model.model;

import orgataxe.entity.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

public class ModelTableModel extends AbstractTableModel {
    protected Vector<Model> models;
    protected Vector<String> columnIdentifiers;

    protected Icon deleteIcon;

    public ModelTableModel() {
        this.models = new Vector<>();

        this.columnIdentifiers = new Vector<>(3);
        this.columnIdentifiers.add("Designation");
        this.columnIdentifiers.add("Poids");
        this.columnIdentifiers.add("");

        try {
            URL imageUrl = this.getClass().getResource("/ressource/image/delete.png");
            if (imageUrl != null) {
                this.deleteIcon = new ImageIcon(ImageIO.read(imageUrl));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        fireTableStructureChanged();
    }

//
// Manipulating rows
//

    public Model getModelAt(int row) {
        return models.get(row);
    }

    public void addRow(Model model) {
        insertRow(getRowCount(), model);
    }

    public void insertRow(int row, Model model) {
        models.insertElementAt(model, row);
        fireTableRowsInserted(row, row);
    }

    public void removeRow(int row) {
        models.removeElementAt(row);
        fireTableRowsDeleted(row, row);
    }

    public void removeRows() {
        int oldRowCount = getRowCount();
        if (oldRowCount == 0) {
            return;
        }

        models.setSize(0);
        fireTableRowsDeleted(0, oldRowCount - 1);
    }

//
// Implementing the TableModel interface
//

    @Override
    public int getRowCount() {
        return models.size();
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
        Model model = models.elementAt(row);
        switch (column) {
            case 0:
                return model.getDesignation();
            case 1:
                return model.getWeight();
            case 2:
                if (this.deleteIcon == null) {
                    return "Supprimer";
                }

                return this.deleteIcon;
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

        Model model = getModelAt(row);
        switch (column) {
            case 0:
                model.setDesignation(value.toString());
                break;
            case 1:
                model.setWeight(Integer.valueOf(((String) value)));
                break;
        }

        fireTableCellUpdated(row, column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
