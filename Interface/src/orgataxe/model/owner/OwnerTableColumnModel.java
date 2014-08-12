package orgataxe.model.owner;

import orgataxe.metier.IManagerOwner;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public class OwnerTableColumnModel extends DefaultTableColumnModel {
    private TableCellRenderer renderer;
    private TableCellEditor editor;

    public OwnerTableColumnModel(JTable table, IManagerOwner managerOwner) {
        renderer = new OwnerTableCellRenderer();
        editor = new OwnerTableCellEditor(table, managerOwner);

        setColumnMargin(0);
    }

    @Override
    public void addColumn(TableColumn column) {
        if (tableColumns.size() == 3) {
            column.setCellRenderer(renderer);
            column.setCellEditor(editor);
        }

        super.addColumn(column);
    }
}
