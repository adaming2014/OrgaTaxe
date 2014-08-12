package orgataxe.model.vehicle;

import orgataxe.entity.Vehicle;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Vector;

public class VehicleTableModel extends AbstractTableModel {
    protected Vector<Vehicle> vehicles;
    protected Vector<String> columnIdentifiers;

    protected Icon deleteIcon;

    public VehicleTableModel() {
        this.vehicles = new Vector<>();

        this.columnIdentifiers = new Vector<>(5);
        this.columnIdentifiers.add("Licence");
        this.columnIdentifiers.add("Mise en circulation");
        this.columnIdentifiers.add("Propriètaire");
        this.columnIdentifiers.add("Modèle");
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

    public Vehicle getVehicleAt(int row) {
        return vehicles.get(row);
    }

    public void addRow(Vehicle vehicle) {
        insertRow(getRowCount(), vehicle);
    }

    public void insertRow(int row, Vehicle vehicle) {
        vehicles.insertElementAt(vehicle, row);
        fireTableRowsInserted(row, row);
    }

    public void removeRow(int row) {
        vehicles.removeElementAt(row);
        fireTableRowsDeleted(row, row);
    }

    public void removeRows() {
        int oldRowCount = getRowCount();
        if (oldRowCount == 0) {
            return;
        }

        vehicles.setSize(0);
        fireTableRowsDeleted(0, oldRowCount - 1);
    }

//
// Implementing the TableModel interface
//

    @Override
    public int getRowCount() {
        return vehicles.size();
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
        Vehicle vehicle = vehicles.elementAt(row);
        switch (column) {
            case 0:
                return vehicle.getLicencePlate();
            case 1:
                return vehicle.getStartDate();
            case 2:
                return vehicle.getOwner().getFirstName() + " " + vehicle.getOwner().getFamilyName().toUpperCase();
            case 3:
                return vehicle.getModel().getDesignation();
            case 4:
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

        Vehicle vehicle = getVehicleAt(row);
        switch (column) {
            case 0:
                vehicle.setLicencePlate(value.toString());
                break;
            case 1:
                vehicle.setStartDate((Date) value);
                break;
        }

        fireTableCellUpdated(row, column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
