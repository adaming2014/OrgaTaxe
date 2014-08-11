import component.PlaceholderTextField;
import event.UpdateEvent;
import event.UpdateEventListener;
import orgataxe.entity.Owner;
import orgataxe.metier.IManagerOwner;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by INTI0221 on 08/08/2014.
 */
public class OwnerForm {
    private JPanel mainPanel;
    private JButton buttonAdd;
    private PlaceholderTextField textFieldFamilyName;
    private PlaceholderTextField textFieldFirstName;
    private PlaceholderTextField textFieldAddress;

    private EventListenerList listenerList = new EventListenerList();
    private IManagerOwner managerOwner;
    private UpdateEvent updateEvent = new UpdateEvent(this);

    public OwnerForm() {
        textFieldFamilyName.setPlaceholder("Entrez le nom de famille ici");
        textFieldFirstName.setPlaceholder("Entrez le prénom ici");
        textFieldAddress.setPlaceholder("Entrez l'adresse ici");


        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldFamilyName.getText().length() == 0 || textFieldFirstName.getText().length() == 0 || textFieldAddress.getText().length() == 0) {
                    JOptionPane.showMessageDialog(((JFrame) SwingUtilities.getWindowAncestor(mainPanel)), "Certains champs sont vides.");

                    return;
                }

                Owner owner = new Owner(textFieldFamilyName.getText(), textFieldFirstName.getText(), textFieldAddress.getText(), null);
                managerOwner.create(owner);

                fireUpdate();

                resetFields();
            }
        });
    }

    public void resetFields() {
        textFieldFirstName.setText("");
        textFieldFamilyName.setText("");
        textFieldAddress.setText("");
    }

    public void setManagerOwner(IManagerOwner managerOwner) {
        this.managerOwner = managerOwner;
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
