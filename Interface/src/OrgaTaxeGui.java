import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.net.URL;

/**
 * Created by INTI0221 on 11/08/2014.
 */
public class OrgaTaxeGui {
    private static OwnerGui ownerGui = new OwnerGui();
    private static ModelGui modelGui = new ModelGui();
    private static VehicleGui vehicleGui = new VehicleGui();

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JTabbedPane contentPane = new JTabbedPane();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.add("Owners", ownerGui.getMainPanel());
        contentPane.add("Models", modelGui.getMainPanel());
        contentPane.add("Vehicles", vehicleGui.getMainPanel());
        contentPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int tabIndex = ((JTabbedPane) e.getSource()).getSelectedIndex();
                switch (tabIndex) {
                    case 0:
                        ownerGui.update();

                        break;
                    case 1:
                        modelGui.update();

                        break;
                    case 2:
                        vehicleGui.update();

                        break;
                }
            }
        });

        JFrame frame = new JFrame();
        frame.setTitle("OrgaTaxe");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        URL imageURL = frame.getClass().getResource("/ressource/image/valid.png");
        if (imageURL != null) {
            frame.setIconImage(new ImageIcon(imageURL).getImage());
        }

        buildMenuBar(frame);

        frame.pack();
        frame.setVisible(true);
    }

    private static void buildMenuBar(JFrame frame) {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        // The main menu bar.
        menuBar = new JMenuBar();

        // File menu.
        menu = new JMenu("Fichier");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        menuBar.add(menu);

        // File.Quit item
        menuItem = new JMenuItem("Quitter", KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(e.getSource() instanceof JMenuItem)) {
                    return;
                }

                JMenuItem menuItem = (JMenuItem) e.getSource();
                JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();
                JComponent invoker = (JComponent) popupMenu.getInvoker();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(invoker);

                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        menu.add(menuItem);

        // Edit menu.
        menu = new JMenu("Modifier");
        menu.setMnemonic(KeyEvent.VK_M);
        menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
        menuBar.add(menu);

        // Edit.Owner item.
        menuItem = new JMenuItem("Propriétaire");
        menuItem.setMnemonic(KeyEvent.VK_B);
        menu.add(menuItem);

        frame.setJMenuBar(menuBar);
    }
}
