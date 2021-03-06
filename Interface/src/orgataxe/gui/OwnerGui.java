package orgataxe.gui;

import orgataxe.database.IDAOOwner;
import orgataxe.model.owner.OwnerTableColumnModel;
import orgataxe.model.owner.OwnerTableModel;
import orgataxe.database.DAOOwner;
import orgataxe.metier.IManagerOwner;
import orgataxe.metier.ManagerOwner;

import javax.swing.*;

/**
 * Created by INTI0221 on 08/08/2014.
 */
public class OwnerGui {
    private JPanel mainPanel;
    private OwnerForm ownerForm1;
    private OwnerTable ownerTable1;

    public OwnerGui(IDAOOwner daoOwner) {
        IManagerOwner managerOwner = new ManagerOwner(daoOwner);

        ownerTable1.setManagerOwner(managerOwner);
        ownerTable1.setModels(new OwnerTableModel(), new OwnerTableColumnModel(ownerTable1.getOwnerTable(), managerOwner));
        ownerTable1.update();

        ownerForm1.setManagerOwner(managerOwner);
        ownerForm1.addTableModelListener(ownerTable1);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void update() {
        ownerTable1.update();
    }
}
