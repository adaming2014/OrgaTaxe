import event.UpdateEvent;
import event.UpdateEventListener;
import model.vehicle.VehicleTableModel;
import orgataxe.entity.Vehicle;
import orgataxe.metier.IManagerVehicle;
import util.ButtonColumn;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
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

    public void setModels(VehicleTableModel model) {
        vehicleTable.setModel(model);

        Action deleteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = vehicleTable.getSelectedRow();
                if (rowIndex == -1) {
                    return;
                }

                Vehicle vehicle = ((VehicleTableModel) vehicleTable.getModel()).getVehicleAt(rowIndex);
                int result = JOptionPane.showConfirmDialog(((JFrame) SwingUtilities.getWindowAncestor(vehicleTable)),
                        "Valider la suppression du vehicle :\n"
                                + "- " + vehicle.getLicencePlate() + "\n"
                                + "\n"
                                + "/!\\ Les vehicules de ce modèle seront aussi supprimé!");
                if (result != 0) {
                    return;
                }

                boolean requestResult = managerVehicle.delete(vehicle.getId());

                ((VehicleTableModel) vehicleTable.getModel()).removeRow(rowIndex);
            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(vehicleTable, deleteAction, 4);
    }

    public JTable getModelTable() {
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
