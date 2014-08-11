import model.model.ModelTableModel;
import orgataxe.database.DAOModel;
import orgataxe.metier.IManagerModel;
import orgataxe.metier.ManagerModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public class ModelGui {
    private ModelForm modelForm1;
    private JPanel panel1;
    private ModelTable modelTable1;

    public ModelGui() {
        IManagerModel managerModel = new ManagerModel(new DAOModel());
        ModelTableModel modelTableModel = new ModelTableModel();

        modelTable1.setManagerModel(managerModel);
        modelTable1.setModels(modelTableModel);
        modelTable1.update();

        modelForm1.setManagerModel(managerModel);
        modelForm1.addTableModelListener(modelTable1);
    }

    public Component getMainPanel() {
        return panel1;
    }

    public void update() {
        modelTable1.update();
    }
}
