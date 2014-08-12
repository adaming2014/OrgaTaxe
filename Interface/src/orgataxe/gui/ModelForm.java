package orgataxe.gui;

import orgataxe.component.PlaceholderTextField;
import orgataxe.event.UpdateEvent;
import orgataxe.event.UpdateEventListener;
import orgataxe.entity.Model;
import orgataxe.metier.IManagerModel;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public class ModelForm {
    private JPanel panelModel;
    private JButton buttonAdd;
    private PlaceholderTextField textFieldDesignation;
    private PlaceholderTextField textFieldWeight;

    private EventListenerList listenerList = new EventListenerList();
    private IManagerModel managerModel;
    private UpdateEvent updateEvent = new UpdateEvent(this);

    public ModelForm() {
        textFieldDesignation.setPlaceholder("Entrez la désignation ici");
        textFieldWeight.setPlaceholder("Entrez le poids ici (en Kg)");

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (textFieldWeight.getText().length() == 0 || textFieldDesignation.getText().length() == 0) {
                    JOptionPane.showMessageDialog(((JFrame) SwingUtilities.getWindowAncestor(panelModel)), "Certains champs sont vides.");

                    return;
                }

                int weight;
                try {
                    weight = Integer.valueOf(textFieldWeight.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(((JFrame) SwingUtilities.getWindowAncestor(panelModel)), "La valeur entré dans le champ poids est invalide (ce n'est pas un entier).");

                    return;
                }

                managerModel.create(new Model(weight, textFieldDesignation.getText()));

                fireUpdate();

                resetFields();
            }
        });
    }

    public void resetFields() {
        textFieldWeight.setText("");
        textFieldDesignation.setText("");
    }

    public void setManagerModel(IManagerModel managerModel) {
        this.managerModel = managerModel;
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
}
