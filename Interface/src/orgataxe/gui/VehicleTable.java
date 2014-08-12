package orgataxe.gui;

import orgataxe.event.UpdateEvent;
import orgataxe.event.UpdateEventListener;
import orgataxe.model.vehicle.VehicleTableColumnModel;
import orgataxe.model.vehicle.VehicleTableModel;
import orgataxe.entity.Vehicle;
import orgataxe.metier.IManagerVehicle;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public class VehicleTable implements UpdateEventListener {
    private JTable vehicleTable;
    private JPanel vehiclePanel;

    private IManagerVehicle managerVehicle;

    public void setManagerVehicle(IManagerVehicle managerVehicle) {
        this.managerVehicle = managerVehicle;
    }

    public void setModels(VehicleTableModel model, VehicleTableColumnModel columnModel) {
        vehicleTable.setColumnModel(columnModel);
        vehicleTable.setModel(model);
    }

    public JTable getVehicleTable() {
        return vehicleTable;
    }

    public void update() {
        TableModel tableModel = this.vehicleTable.getModel();
        if (!(tableModel instanceof VehicleTableModel)) {
            return;
        }

        VehicleTableModel vehicleModel = (VehicleTableModel) tableModel;

        List<Vehicle> vehicles = managerVehicle.getAll();

        vehicleModel.removeRows();
        for (Vehicle vehicle : vehicles) {
            vehicleModel.addRow(vehicle);
        }
    }

    @Override
    public void update(UpdateEvent event) {
        update();
    }
}
