package model.owner;

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
public class OwnerTableCellRenderer implements TableCellRenderer {
    private JPanel renderPanel;
    private JButton renderButtonValid;
    private JButton renderButtonCancel;
    private JButton renderButtonDelete;

    public OwnerTableCellRenderer() {
        renderPanel = new JPanel();
        renderPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        renderPanel.setLayout(new GridLayout(0, 3));

        renderButtonValid = new JButton();
        try {
            URL imageURL = renderButtonValid.getClass().getResource("/ressource/image/valid.png");
            if (imageURL != null) {
                renderButtonValid.setIcon(new ImageIcon(ImageIO.read(imageURL)));
            }
        } catch (IOException e) {
        }

        renderButtonCancel = new JButton();
        try {
            URL imageURL = renderButtonCancel.getClass().getResource("/ressource/image/cancel.png");
            if (imageURL != null) {
                renderButtonCancel.setIcon(new ImageIcon(ImageIO.read(imageURL)));
            }
        } catch (IOException e) {
        }

        renderButtonDelete = new JButton();
        try {
            URL imageURL = renderButtonDelete.getClass().getResource("/ressource/image/delete.png");
            if (imageURL != null) {
                renderButtonDelete.setIcon(new ImageIcon(ImageIO.read(imageURL)));
            }
        } catch (IOException e) {
        }

        renderPanel.add(renderButtonValid);
        renderPanel.add(renderButtonCancel);
        renderPanel.add(renderButtonDelete);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        if (isSelected) {
//            renderButton.setForeground(table.getSelectionForeground());
//            renderButton.setBackground(table.getSelectionBackground());
//        } else {
//            renderButton.setForeground(table.getForeground());
//            renderButton.setBackground(UIManager.getColor("Button.background"));
//        }
//
//        if (hasFocus) {
//            renderButton.setBorder(focusBorder);
//        } else {
//            renderButton.setBorder(originalBorder);
//        }

        return renderPanel;
    }
}
