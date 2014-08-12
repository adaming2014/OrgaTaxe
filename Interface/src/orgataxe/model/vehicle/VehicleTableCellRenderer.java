package orgataxe.model.vehicle;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public class VehicleTableCellRenderer implements TableCellRenderer {
    private JPanel renderPanel;
    private JButton renderButtonTaxe;
    private JButton renderButtonDelete;

    public VehicleTableCellRenderer() {
        renderPanel = new JPanel();
        renderPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        renderPanel.setLayout(new GridLayout(0, 2));

        renderButtonTaxe = new JButton();
        try {
            URL imageURL = renderButtonTaxe.getClass().getResource("/ressource/image/dollar.png");
            if (imageURL != null) {
                renderButtonTaxe.setIcon(new ImageIcon(ImageIO.read(imageURL)));
            } else {
                renderButtonTaxe.setText("Taxe");
            }
        } catch (IOException e) {
        }

        renderButtonDelete = new JButton();
        try {
            URL imageURL = renderButtonDelete.getClass().getResource("/ressource/image/delete.png");
            if (imageURL != null) {
                renderButtonDelete.setIcon(new ImageIcon(ImageIO.read(imageURL)));
            } else {
                renderButtonDelete.setText("Supprimer");
            }
        } catch (IOException e) {
        }

        renderPanel.add(renderButtonTaxe);
        renderPanel.add(renderButtonDelete);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return renderPanel;
    }
}
