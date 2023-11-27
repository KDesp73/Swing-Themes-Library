package kdesp73.themeLib.customuis;

import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import javax.swing.JComponent;

public class CustomProgressBarUI extends BasicProgressBarUI {

    private Color foregroundColor;
    private Color backgroundColor;
    private Color textForeground;

    public CustomProgressBarUI() {
        // Set default colors
        this.foregroundColor = Color.GREEN;
        this.backgroundColor = Color.YELLOW;
        this.textForeground = Color.BLACK;
    }

    public CustomProgressBarUI(Color foregroundColor, Color backgroundColor, Color textForeground) {
        // Set custom colors
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.textForeground = textForeground;
    }

    @Override
    protected void paintIndeterminate(Graphics g, JComponent c) {
        // Customize the indeterminate appearance if needed
        super.paintIndeterminate(g, c);
    }

    @Override
    protected Color getSelectionForeground() {
        return textForeground;
    }

    protected Color getForeground() {
        return foregroundColor;
    }

    protected Color getBackground() {
        return backgroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
        progressBar.repaint();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        progressBar.repaint();
    }

    public Color getTextForeground() {
        return textForeground;
    }

    public void setTextForeground(Color textForeground) {
        this.textForeground = textForeground;
        progressBar.repaint();
    }
}
