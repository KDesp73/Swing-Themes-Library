package kdesp73.themeLib;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSpinnerUI;
import java.awt.*;

public class CustomSpinnerUI extends BasicSpinnerUI {

        private Color background;
        private Color foreground;
        private Color arrowButtonBackground;
        private Color arrowButtonForeground;
        private Color disabledForeground;

        public CustomSpinnerUI(Color background, Color foreground, Color arrowButtonBackground, Color arrowButtonForeground, Color disabledForeground) {
                this.background = background;
                this.foreground = foreground;
                this.arrowButtonBackground = arrowButtonBackground;
                this.arrowButtonForeground = arrowButtonForeground;
                this.disabledForeground = disabledForeground;
        }

        @Override
        protected Component createNextButton() {
                JButton button = (JButton) super.createNextButton();
                customizeButton(button);
                return button;
        }

        @Override
        protected Component createPreviousButton() {
                JButton button = (JButton) super.createPreviousButton();
                customizeButton(button);
                return button;
        }

        private void customizeButton(JButton button) {
                button.setBackground(arrowButtonBackground);
                button.setForeground(arrowButtonForeground);
                button.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        }

        @Override
        protected void installDefaults() {
                super.installDefaults();
                spinner.setBackground(background);
                spinner.setForeground(foreground);
                ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setDisabledTextColor(disabledForeground);
        }

}
