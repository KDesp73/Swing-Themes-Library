/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.themeLib;

import java.awt.Color;

/**
 *
 * @author konstantinos
 */
public class Colors {

        public String key = null;

        public Color background = null;
        public Color foreground = null;
        public Color selectionBackground = null;
        public Color selectionForeground = null;
        public Color border = null;
        public Color focus = null;
        public Color disabledForeground = null;
        public Color pressed = null;
        public Color disabledBackground = null;
        public Color toolTipBackground = null;
        public Color toolTipText = null;
        public Color shadow = null;
        public Color highlight = null;
        public Color linkColor = null;
        public Color gridColor = null; // For components with grids
        public Color thumb = null; // For scroll bars
        public Color track = null; // For scroll bars
        public Color arrowButtonBackground = null; // For components with arrow buttons
        public Color arrowButtonForeground = null; // For components with arrow buttons
        public Color caret = null;
        public Color dragging = null; // For split pane
        public Color rollover = null;

        private boolean checkColors(Color... colors){
                for(Color color : colors){
                        if(color == null) return false;
                }
                
                return true;
        }
        
        protected boolean checkSlider() {
                return checkColors(track, thumb, shadow);
        }

        protected boolean checkScrollBar() {
                return checkColors(track, thumb);
        }
        
        protected boolean checkSpinner() {
                return checkColors(background, foreground, arrowButtonBackground, arrowButtonForeground, disabledForeground);
        }
        
        protected boolean checkTabbedPane(){
                return checkColors(background, foreground, selectionBackground, selectionForeground);
        }
        
        protected boolean checkProgressBar(){
            return checkColors(background, pressed, selectionBackground);
        }
        
        protected boolean checkComboBox(){
            return checkColors(background, foreground, selectionBackground, selectionForeground, arrowButtonBackground, arrowButtonForeground);
        }
        
        protected boolean checkButton(){
            return checkColors(background, pressed, rollover, disabledBackground);
        }

        // Same for every component
        public String toYAMLString() {
                if (key == null) {
                        throw new NullPointerException("key is null");
                }

                StringBuilder sb = new StringBuilder();

                // Append key
                sb.append(key).append(": ").append("\n");

                // Append other attributes
                appendColorPair(sb, "background", background);
                appendColorPair(sb, "foreground", foreground);
                appendColorPair(sb, "selectionBackground", selectionBackground);
                appendColorPair(sb, "selectionForeground", selectionForeground);
                appendColorPair(sb, "border", border);
                appendColorPair(sb, "focus", focus);
                appendColorPair(sb, "disabledForeground", disabledForeground);
                appendColorPair(sb, "pressed", pressed);
                appendColorPair(sb, "disabledBackground", disabledBackground);
                appendColorPair(sb, "toolTipBackground", toolTipBackground);
                appendColorPair(sb, "toolTipText", toolTipText);
                appendColorPair(sb, "shadow", shadow);
                appendColorPair(sb, "highlight", highlight);
                appendColorPair(sb, "linkColor", linkColor);
                appendColorPair(sb, "gridColor", gridColor);
                appendColorPair(sb, "thumb", thumb);
                appendColorPair(sb, "track", track);
                appendColorPair(sb, "arrowButtonBackground", arrowButtonBackground);
                appendColorPair(sb, "arrowButtonForeground", arrowButtonForeground);
                appendColorPair(sb, "caret", caret);
                appendColorPair(sb, "dragging", dragging);
                appendColorPair(sb, "rollover", rollover);

                return sb.toString();
        }

        private void appendColorPair(StringBuilder sb, String attributeName, Color color) {
                sb.append("    ").append(attributeName).append(": ").append((color == null ? "null" : Utils.ColorToHex(color))).append("\n");
        }
        
        public static void printEmptyYamlString(){
            Colors c = new Colors();
            c.key = "key";
            System.out.println(c.toYAMLString());
        }

}
