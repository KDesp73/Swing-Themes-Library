package kdesp73.themeLib.customuis;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class CustomButtonUI extends BasicButtonUI {

    private Color normalColor;
    private Color pressedColor;
    private Color rolloverColor;
    private Color disabledColor;

    public CustomButtonUI(Color normalColor, Color pressedColor, Color rolloverColor, Color disabledColor) {
        this.normalColor = normalColor;
        this.pressedColor = pressedColor;
        this.rolloverColor = rolloverColor;
        this.disabledColor = disabledColor;
    }

    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        if (b.isContentAreaFilled()) {
            g.setColor(pressedColor);
            g.fillRect(0, 0, b.getWidth(), b.getHeight());
        }
    }

    protected void paintButtonRollover(Graphics g, AbstractButton b) {
        if (b.isContentAreaFilled()) {
            g.setColor(rolloverColor);
            g.fillRect(0, 0, b.getWidth(), b.getHeight());
        }
    }

    protected void paintButtonDisabled(Graphics g, AbstractButton b) {
        if (b.isContentAreaFilled()) {
            g.setColor(disabledColor);
            g.fillRect(0, 0, b.getWidth(), b.getHeight());
        }
    }

    protected void paintButtonBackground(Graphics g, AbstractButton b) {
        ButtonModel model = b.getModel();
        if (model.isEnabled()) {
            if (model.isPressed()) {
                paintButtonPressed(g, b);
            } else if (model.isRollover()) {
                paintButtonRollover(g, b);
            } else {
                g.setColor(normalColor);
                g.fillRect(0, 0, b.getWidth(), b.getHeight());
            }
        } else {
            paintButtonDisabled(g, b);
        }
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        if (model.isEnabled()) {
            if (model.isPressed()) {
                paintButtonPressed(g, b);
            } else if (model.isRollover()) {
                paintButtonRollover(g, b);
            } else {
                g.setColor(normalColor);
                g.fillRect(0, 0, b.getWidth(), b.getHeight());
            }
        } else {
            paintButtonDisabled(g, b);
        }

        super.paint(g, c);
    }
}
