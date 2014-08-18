package orgataxe.gui;

import orgataxe.database.DAOOwner;
import orgataxe.database.DAOVehicle;
import orgataxe.database.IDAOVehicle;
import orgataxe.metier.*;
import orgataxe.model.owner.OwnerTableColumnModel;
import orgataxe.model.owner.OwnerTableModel;
import orgataxe.model.vehicle.VehicleTableColumnModel;
import orgataxe.model.vehicle.VehicleTableModel;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Created by INTI0221 on 12/08/2014.
 */
public class VehicleGui {
    private VehicleForm vehicleForm1;
    private JPanel panelVehicle;
    private VehicleTable vehicleTable1;

    public VehicleGui(IDAOVehicle daoVehicle) {
        IManagerVehicle managerVehicle = new ManagerVehicle(daoVehicle);
        IManagerTaxe managerTaxe = new ManagerTaxe();

        vehicleTable1.setManagerVehicle(managerVehicle);
        vehicleTable1.setModels(new VehicleTableModel(), new VehicleTableColumnModel(vehicleTable1.getVehicleTable(), managerVehicle, managerTaxe));
        vehicleTable1.update();

        vehicleForm1.setManagerVehicle(managerVehicle);
        vehicleForm1.addTableModelListener(vehicleTable1);
    }

    public JPanel getMainPanel() {
        return panelVehicle;
    }

    public void update() {
        vehicleTable1.update();
        vehicleForm1.resetFields();
    }
}
