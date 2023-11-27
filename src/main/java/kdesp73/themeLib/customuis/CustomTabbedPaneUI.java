package kdesp73.themeLib.customuis;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class CustomTabbedPaneUI extends BasicTabbedPaneUI {

        private Color background;
        private Color foreground;
        private Color selectedTabBackground;
        private Color selectedTabForeground;
        private Color contentAreaBackground;

        public CustomTabbedPaneUI(
                Color background,
                Color foreground,
                Color selectedTabBackground,
                Color selectedTabForeground,
                Color contentAreaBackground
        ) {
                this.background = background;
                this.foreground = foreground;
                this.selectedTabBackground = selectedTabBackground;
                this.selectedTabForeground = selectedTabForeground;
                this.contentAreaBackground = contentAreaBackground;
        }

        @Override
        protected void installDefaults() {
                super.installDefaults();
                tabPane.setBackground(background);
                tabPane.setForeground(foreground);
                this.selectedTabPadInsets = new Insets(2, 2, 2, 2);
        }

        @Override
        protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(isSelected ? selectedTabBackground : tabPane.getBackground());
                g2d.fillRect(x, y, w, h);
        }

        @Override
        protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
                g.setColor(isSelected ? selectedTabForeground : tabPane.getForeground());
                g.setFont(font);
                g.drawString(title, textRect.x, textRect.y + metrics.getAscent());
        }
}
