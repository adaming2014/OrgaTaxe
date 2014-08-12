package orgataxe.gui;

import orgataxe.event.UpdateEvent;
import orgataxe.event.UpdateEventListener;
import orgataxe.model.model.ModelTableModel;
import orgataxe.entity.Model;
import orgataxe.metier.IManagerModel;
import orgataxe.ButtonColumn;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public class ModelTable implements UpdateEventListener {
    private JTable tableModel;
    private JPanel panelModel;

    private IManagerModel managerModel;

    public void setManagerModel(IManagerModel managerModel) {
        this.managerModel = managerModel;
    }

    public void setModels(ModelTableModel model) {
        tableModel.setModel(model);

        Action deleteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = tableModel.getSelectedRow();
                if (rowIndex == -1) {
                    return;
                }

                Model model = ((ModelTableModel) tableModel.getModel()).getModelAt(rowIndex);
                int result = JOptionPane.showConfirmDialog(((JFrame) SwingUtilities.getWindowAncestor(tableModel)),
                        "Valider la suppression du model :\n"
                                + "- " + model.getDesignation() + "\n"
                                + "\n"
                                + "/!\\ Les véhicules de ce model seront aussi supprimé!");
                if (result != 0) {
                    return;
                }

                boolean requestResult = managerModel.delete(model.getId());

                ((ModelTableModel) tableModel.getModel()).removeRow(rowIndex);
            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(tableModel, deleteAction, 2);
    }

    public JTable getModelTable() {
        return tableModel;
    }

    public void update() {
        TableModel tableModel = this.tableModel.getModel();
        if (!(tableModel instanceof ModelTableModel)) {
            return;
        }

        ModelTableModel modelModel = (ModelTableModel) tableModel;

        List<Model> models = managerModel.getAll();

        modelModel.removeRows();
        for (Model model : models) {
            modelModel.addRow(model);
        }
    }

    @Override
    public void update(UpdateEvent event) {
        update();
    }
}
