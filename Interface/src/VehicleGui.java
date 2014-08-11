import model.vehicle.VehicleTableModel;
import orgataxe.database.DAOVehicle;
import orgataxe.metier.IManagerVehicle;
import orgataxe.metier.ManagerVehicle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public class VehicleGui {
    private VehicleForm vehicleForm1;
    private JPanel vehiclePanel;
    private VehicleTable vehicleTable1;

    public VehicleGui() {
        IManagerVehicle managerVehicle = new ManagerVehicle(new DAOVehicle());
        VehicleTableModel vehicleTableModel = new VehicleTableModel();

        vehicleTable1.setManagerVehicle(managerVehicle);
        vehicleTable1.setModels(vehicleTableModel);
        vehicleTable1.update();

        vehicleForm1.setManagerVehicle(managerVehicle);
        vehicleForm1.addTableModelListener(vehicleTable1);
    }

    public Component getMainPanel() {
        return vehiclePanel;
    }

    public void update() {
        vehicleTable1.update();
        vehicleForm1.resetFields();
    }
}
