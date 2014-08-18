package orgataxe.gui;

import orgataxe.entity.Owner;
import orgataxe.entity.Vehicle;
import orgataxe.event.UpdateEvent;
import orgataxe.metier.IManagerOwner;
import orgataxe.metier.IManagerTaxe;
import orgataxe.metier.IManagerVehicle;
import orgataxe.model.owner.OwnerTableColumnModel;
import orgataxe.model.owner.OwnerTableModel;
import orgataxe.model.taxe.TaxeTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by INTI0221 on 12/08/2014.
 */
public class TaxeTable {
    private JTable taxeTable;
    private JPanel taxePanel;

    TaxeTableModel taxeTableModel;

    public void setModels(TaxeTableModel model) {
        taxeTableModel = model;

        taxeTable.setModel(model);
    }

    public JPanel getMainPanel() {
        return taxePanel;
    }

    public void update(IManagerTaxe managerTaxe, Vehicle vehicle) {
        Calendar calendar = new GregorianCalendar();

        int currentYear = calendar.get(Calendar.YEAR);

        calendar.setTime(vehicle.getStartDate());
        int startYear = calendar.get(Calendar.YEAR);

        taxeTableModel.removeRows();
        for (int year = startYear; year <= currentYear + 10; year++) {
            taxeTableModel.addRow(year, managerTaxe.getTaxeAt(vehicle, year));
        }
    }
}
