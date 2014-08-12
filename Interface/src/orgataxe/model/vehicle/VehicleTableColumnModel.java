package orgataxe.model.vehicle;

import orgataxe.metier.IManagerVehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public class VehicleTableColumnModel extends DefaultTableColumnModel {
    private TableCellRenderer renderer;
    private TableCellEditor editor;

    public VehicleTableColumnModel(JTable table, IManagerVehicle managerVehicle) {
        renderer = new VehicleTableCellRenderer();
        editor = new VehicleTableCellEditor(table, managerVehicle);

        setColumnMargin(0);
    }

    @Override
    public void addColumn(TableColumn column) {
        if (tableColumns.size() == 4) {
            column.setCellRenderer(renderer);
            column.setCellEditor(editor);
        }

        super.addColumn(column);
    }
}
