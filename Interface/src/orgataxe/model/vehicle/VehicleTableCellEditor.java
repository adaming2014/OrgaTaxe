package orgataxe.model.vehicle;

import orgataxe.entity.Vehicle;
import orgataxe.gui.OrgaTaxeGui;
import orgataxe.gui.TaxeTable;
import orgataxe.metier.IManagerTaxe;
import orgataxe.metier.IManagerVehicle;
import orgataxe.model.taxe.TaxeTableModel;

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
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public class VehicleTableCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener, MouseListener {
    private JTable table;

    private JPanel panel;
    private JButton buttonTaxe;
    private JButton buttonDelete;

    private boolean isActiveEditor;
    private IManagerVehicle managerVehicle;
    private IManagerTaxe managerTaxe;

    public VehicleTableCellEditor(JTable table, IManagerVehicle managerVehicle, IManagerTaxe managerTaxe) {
        this.table = table;
        this.managerVehicle = managerVehicle;
        this.managerTaxe = managerTaxe;

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel.setLayout(new GridLayout(0, 2));

        buttonTaxe = new JButton();
        buttonTaxe.addActionListener(this);
        buttonTaxe.setFocusPainted(false);
        buttonTaxe.setToolTipText("Afficher les taxes");
        try {
            URL imageURL = buttonTaxe.getClass().getResource("/ressource/image/dollar.png");
            if (imageURL != null) {
                buttonTaxe.setIcon(new ImageIcon(ImageIO.read(imageURL)));
            } else {
                buttonTaxe.setText("Taxe");
            }
        } catch (IOException e) {
        }

        buttonDelete = new JButton();
        buttonDelete.addActionListener(this);
        buttonDelete.setFocusPainted(false);
        buttonDelete.setToolTipText("Supprimer le vehicule");
        try {
            URL imageURL = buttonDelete.getClass().getResource("/ressource/image/delete.png");
            if (imageURL != null) {
                buttonDelete.setIcon(new ImageIcon(ImageIO.read(imageURL)));
            } else {
                buttonDelete.setText("Supprimer");
            }
        } catch (IOException e) {
        }

        panel.add(buttonTaxe);
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

            Vehicle vehicle = ((VehicleTableModel) table.getModel()).getVehicleAt(rowIndex);
            int result = JOptionPane.showConfirmDialog(((JFrame) SwingUtilities.getWindowAncestor(table)),
                    "Valider la suppression du vehicle :\n"
                            + "- " + vehicle.getLicencePlate());
            if (result != 0) {
                return;
            }

            boolean requestResult = managerVehicle.delete(vehicle.getId());

            ((VehicleTableModel) table.getModel()).removeRow(rowIndex);
        } else if (e.getSource() == buttonTaxe) {
            int rowIndex = table.getSelectedRow();
            if (rowIndex == -1) {
                return;
            }

            Vehicle vehicle = ((VehicleTableModel) table.getModel()).getVehicleAt(rowIndex);

            OrgaTaxeGui.displayTaxeDialog(managerTaxe, vehicle);
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
