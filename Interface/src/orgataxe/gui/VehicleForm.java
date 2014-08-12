package orgataxe.gui;

import com.toedter.calendar.JDateChooser;
import orgataxe.event.UpdateEvent;
import orgataxe.event.UpdateEventListener;
import orgataxe.database.DAOModel;
import orgataxe.database.DAOOwner;
import orgataxe.entity.Model;
import orgataxe.entity.Owner;
import orgataxe.entity.Vehicle;
import orgataxe.metier.IManagerVehicle;
import orgataxe.metier.ManagerModel;
import orgataxe.metier.ManagerOwner;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public class VehicleForm {
    private JPanel vehiclePanel;
    private JTextField textFieldLicence;
    private JButton addButton;
    private JComboBox comboBoxOwner;
    private JComboBox comboBoxModel;
    private JDateChooser dateChooserStart;

    private EventListenerList listenerList = new EventListenerList();
    private IManagerVehicle managerVehicle;
    private UpdateEvent updateEvent = new UpdateEvent(this);

    public VehicleForm() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (textFieldLicence.getText().length() == 0 || ((OwnerContainer) comboBoxOwner.getSelectedItem()).owner == null || ((ModelContainer) comboBoxModel.getSelectedItem()).model == null) {
                    JOptionPane.showMessageDialog(((JFrame) SwingUtilities.getWindowAncestor(vehiclePanel)), "Certains champs sont vides.");

                    return;
                }

                managerVehicle.create(new Vehicle(textFieldLicence.getText(), new Date(dateChooserStart.getDate().getTime()), ((OwnerContainer) comboBoxOwner.getSelectedItem()).owner, ((ModelContainer) comboBoxModel.getSelectedItem()).model));

                fireUpdate();

                resetFields();
            }
        });

        resetFields();
    }

    public void resetFields() {
        textFieldLicence.setText("");

        ManagerOwner managerOwner = new ManagerOwner(new DAOOwner());
        List<Owner> owners = managerOwner.getAll();

        comboBoxOwner.removeAllItems();
        comboBoxOwner.addItem(new OwnerContainer(null));
        comboBoxOwner.setSelectedIndex(0);
        for (Owner owner : owners) {
            comboBoxOwner.addItem(new OwnerContainer(owner));
        }

        ManagerModel managerModel = new ManagerModel(new DAOModel());
        List<Model> models = managerModel.getAll();

        comboBoxModel.removeAllItems();
        comboBoxModel.addItem(new ModelContainer(null));
        comboBoxModel.setSelectedIndex(0);
        for (Model model : models) {
            comboBoxModel.addItem(new ModelContainer(model));
        }
    }

    public void setManagerVehicle(IManagerVehicle managerVehicle) {
        this.managerVehicle = managerVehicle;
    }

    public void addTableModelListener(UpdateEventListener l) {
        listenerList.add(UpdateEventListener.class, l);
    }

    public void removeTableModelListener(UpdateEventListener l) {
        listenerList.remove(UpdateEventListener.class, l);
    }

    private void fireUpdate() {
        UpdateEventListener[] listeners = listenerList.getListeners(UpdateEventListener.class);

        for (UpdateEventListener listener : listeners) {
            listener.update(updateEvent);
        }
    }

    private class OwnerContainer {
        public Owner owner;

        private OwnerContainer(Owner owner) {
            this.owner = owner;
        }

        @Override
        public String toString() {
            if (owner == null) {
                return "";
            }

            return owner.getFirstName() + " " + owner.getFamilyName().toUpperCase();
        }
    }

    private class ModelContainer {
        public Model model;

        private ModelContainer(Model model) {
            this.model = model;
        }

        @Override
        public String toString() {
            if (model == null) {
                return "";
            }

            return model.getDesignation();
        }
    }
}
