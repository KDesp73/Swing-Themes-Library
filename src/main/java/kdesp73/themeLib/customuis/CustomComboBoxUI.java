package kdesp73.themeLib.customuis;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

public class CustomComboBoxUI extends BasicComboBoxUI {

    private Color background;
    private Color foreground;
    private Color selectionBackground;
    private Color selectionForeground;
    private Color arrowButtonBackground;
    private Color arrowButtonForeground;

    public CustomComboBoxUI(Color background, Color foreground, Color selectionBackground,
            Color selectionForeground, Color arrowButtonBackground,
            Color arrowButtonForeground) {
        this.background = background;
        this.foreground = foreground;
        this.selectionBackground = selectionBackground;
        this.selectionForeground = selectionForeground;
        this.arrowButtonBackground = arrowButtonBackground;
        this.arrowButtonForeground = arrowButtonForeground;
    }

    @Override
    protected JButton createArrowButton() {
        JButton button = new JButton();
        button.setBackground(arrowButtonBackground);
        button.setForeground(arrowButtonForeground);
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }
    
     @Override
    protected ComboPopup createPopup() {
        return new BasicComboPopup(comboBox) {
            @Override
            protected JScrollPane createScroller() {
                return new JScrollPane(list) {
                    @Override
                    public void paintComponent(Graphics g) {
                        // Customize the appearance of the selected item area here
                        g.setColor(background); // Set the background color
                        g.fillRect(0, 0, getWidth(), getHeight());

                        super.paintComponent(g);
                    }
                };
            }
        };
    }

    @Override
    protected ListCellRenderer<Object> createRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Component rendererComponent = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    rendererComponent.setBackground(selectionBackground);
                    rendererComponent.setForeground(selectionForeground);
                } else {
                    rendererComponent.setBackground(background);
                    rendererComponent.setForeground(foreground);
                }

                return rendererComponent;
            }
        };
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        JComboBox<?> comboBox = (JComboBox<?>) c;
        comboBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                comboBox.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                comboBox.repaint();
            }
        });
    }
}
