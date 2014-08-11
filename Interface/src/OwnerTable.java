import event.UpdateEvent;
import event.UpdateEventListener;
import model.owner.OwnerTableColumnModel;
import model.owner.OwnerTableModel;
import orgataxe.entity.Owner;
import orgataxe.metier.IManagerOwner;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * Created by INTI0221 on 08/08/2014.
 */
public class OwnerTable implements UpdateEventListener {
    private JTable ownerTable;
    private JPanel mainPanel;

    private IManagerOwner managerOwner;

    public void setManagerOwner(IManagerOwner managerOwner) {
        this.managerOwner = managerOwner;
    }

    public void setModels(OwnerTableModel model, OwnerTableColumnModel columnModel) {
        ownerTable.setColumnModel(columnModel);
        ownerTable.setModel(model);
    }

    public JTable getOwnerTable() {
        return ownerTable;
    }

    public void update() {
        TableModel model = ownerTable.getModel();
        if (!(model instanceof OwnerTableModel)) {
            return;
        }

        OwnerTableModel ownerModel = (OwnerTableModel) model;

        List<Owner> owners = managerOwner.getAll();

        ownerModel.removeRows();
        for (Owner owner : owners) {
            ownerModel.addRow(owner);
        }
    }

    @Override
    public void update(UpdateEvent event) {
        update();
    }
}
