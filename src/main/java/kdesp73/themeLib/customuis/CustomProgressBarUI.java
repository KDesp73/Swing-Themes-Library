package kdesp73.themeLib.customuis;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class CustomProgressBarUI extends BasicProgressBarUI {

    private Color trackColor;
    private Color progressBarColor;
    private Color textColor;

    public CustomProgressBarUI(Color trackColor, Color progressBarColor, Color textColor) {
        this.trackColor = trackColor;
        this.progressBarColor = progressBarColor;
        this.textColor = textColor;
    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        super.paintDeterminate(g, c);

        if (c instanceof JProgressBar) {
            JProgressBar progressBar = (JProgressBar) c;

            int barRectWidth = progressBar.getWidth();
            int barRectHeight = progressBar.getHeight();

            // Calculate the filled portion as a percentage
            int amountFull = (int) (barRectWidth * ((double) progressBar.getValue() / progressBar.getMaximum()));

            // Paint the track (background)
            g.setColor(trackColor);
            g.fillRect(0, 0, barRectWidth, barRectHeight);

            // Paint the progress bar (filled part)
            g.setColor(progressBarColor);
            g.fillRect(0, 0, amountFull, barRectHeight);

            // Paint the text in the center
            String text = String.valueOf(progressBar.getValue());
            FontMetrics fontMetrics = g.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(text);
            int textHeight = fontMetrics.getHeight();
            g.setColor(textColor);
            g.drawString(text, (barRectWidth - textWidth) / 2, (barRectHeight + textHeight) / 2);

            // Paint the border
            g.setColor(progressBar.getForeground());
            g.drawRect(0, 0, barRectWidth - 1, barRectHeight - 1);
        }
    }

    @Override
    protected Color getSelectionForeground() {
        return textColor;
    }

    @Override
    protected Color getSelectionBackground() {
        return progressBarColor;
    }
}
