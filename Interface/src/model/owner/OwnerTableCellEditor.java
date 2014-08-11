package model.owner;

import orgataxe.entity.Owner;
import orgataxe.metier.IManagerOwner;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public class OwnerTableCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener, MouseListener {
    private JTable table;

    private JPanel panel;
    private JButton buttonValid;
    private JButton buttonCancel;
    private JButton buttonDelete;

    private boolean isActiveEditor;
    private IManagerOwner managerOwner;

    public OwnerTableCellEditor(JTable table, IManagerOwner managerOwner) {
        this.table = table;
        this.managerOwner = managerOwner;

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel.setLayout(new GridLayout(0, 3));

        buttonValid = new JButton();
        buttonValid.addActionListener(this);
        buttonValid.setFocusPainted(false);
        buttonValid.setToolTipText("Valider la modification");
        try {
            URL imageURL = buttonValid.getClass().getResource("/ressource/image/valid.png");
            if (imageURL != null) {
                buttonValid.setIcon(new ImageIcon(ImageIO.read(imageURL)));
            }
        } catch (IOException e) {
        }

        buttonCancel = new JButton();
        buttonCancel.addActionListener(this);
        buttonCancel.setFocusPainted(false);
        buttonCancel.setToolTipText("Annuler modification");
        try {
            URL imageURL = buttonCancel.getClass().getResource("/ressource/image/cancel.png");
            if (imageURL != null) {
                buttonCancel.setIcon(new ImageIcon(ImageIO.read(imageURL)));
            }
        } catch (IOException e) {
        }

        buttonDelete = new JButton();
        buttonDelete.addActionListener(this);
        buttonDelete.setFocusPainted(false);
        buttonDelete.setToolTipText("Supprime le propriétaire");
        try {
            URL imageURL = buttonDelete.getClass().getResource("/ressource/image/delete.png");
            if (imageURL != null) {
                buttonDelete.setIcon(new ImageIcon(ImageIO.read(imageURL)));
            }
        } catch (IOException e) {
        }

        panel.add(buttonValid);
        panel.add(buttonCancel);
        panel.add(buttonDelete);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.convertRowIndexToModel(table.getEditingRow());
        fireEditingStopped();

        if (e.getSource() == buttonDelete) {
            int rowIndex = table.getSelectedRow();
            if (rowIndex == -1) {
                return;
            }

            Owner owner = ((OwnerTableModel) table.getModel()).getOwnerAt(rowIndex);
            int result = JOptionPane.showConfirmDialog(((JFrame) SwingUtilities.getWindowAncestor(table)),
                    "Valider la suppression du propriètaire :\n"
                            + "- " + owner.getFirstName() + " " + owner.getFamilyName().toUpperCase() + "\n"
                            + "- " + owner.getAddress() + "\n"
                            + "\n"
                            + "/!\\ Les vehicules de ce propriètaire seront aussi supprimé!");
            if (result != 0) {
                return;
            }

            boolean requestResult = managerOwner.delete(owner.getId());

            ((OwnerTableModel) table.getModel()).removeRow(row);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (table.isEditing() && table.getCellEditor() == this) {
            isActiveEditor = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isActiveEditor && table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }

        isActiveEditor = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
