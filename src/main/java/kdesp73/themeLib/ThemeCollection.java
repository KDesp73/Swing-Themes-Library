/*
*
* MIT License
*
* Copyright (c) 2023 Konstantinos Despoinidis
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
 */
package kdesp73.themeLib;

import kdesp73.themeLib.customuis.CustomScrollPaneUI;
import kdesp73.themeLib.customuis.CustomTabbedPaneUI;
import kdesp73.themeLib.customuis.CustomSpinnerUI;
import kdesp73.themeLib.customuis.CustomSliderUI;
import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import kdesp73.themeLib.customuis.CustomComboBoxUI;
import kdesp73.themeLib.customuis.CustomProgressBarUI;

/**
 *
 * @author KDesp73
 */
public class ThemeCollection {

    private ArrayList<Theme> themes = new ArrayList<>();

    public ThemeCollection() {

    }

    public ThemeCollection(String path) {
        loadThemes(path);
    }

    /**
     * This method searches through every child of the parent container and
     * applies the appropriate colors if the name matches the predetermined ones
     *
     * @param component The parent container the theme will be applied to
     * @param theme Theme of choice
     */
    public static void applyTheme(Component component, Theme theme) throws RuntimeException, NullPointerException {
        if (component == null) {
            throw new NullPointerException("component is null");
        }
        if (theme == null) {
            throw new NullPointerException("theme is null");
        }
        if (theme.isEmpty()) {
            throw new RuntimeException("theme doesn't contain colors");
        }

        Set<String> keys = theme.keySet();

        for (String key : keys) {
            Colors colors = theme.get(key);

            applyColors(colors, component);
        }

        // Recurse through every component
        if (component instanceof Container container) {
            for (Component child : container.getComponents()) {
                applyTheme(child, theme);
            }
        }
    }

    private static void applyColors(Colors colors, Component component) {
        if (component.getName() == null || !component.getName().equals(colors.key)) {
            return;
        }

        if (component instanceof JComponent jcomponent) { // Component
            if (colors.background != null) {
                component.setBackground(colors.background);
            }

            if (colors.foreground != null) {
                component.setForeground(colors.foreground);
            }

            if (colors.border != null) {
                Utils.changeBorderColor(jcomponent, colors.border);
            }
        } else {
            return;
        }

        if (component instanceof JTextComponent textcomponent) { // TextComponents
            if (colors.caret != null) {
                textcomponent.setCaretColor(colors.caret);
            }

            if (colors.disabledForeground != null) {
                textcomponent.setDisabledTextColor(colors.disabledForeground);
            }

            if (colors.selectionBackground != null) {
                textcomponent.setSelectionColor(colors.selectionBackground);
            }

            if (colors.selectionForeground != null) {
                textcomponent.setSelectedTextColor(colors.selectionForeground);
            }

            if (colors.highlight != null) {
                Utils.changeHighlightColor(textcomponent, colors.highlight);
            }
        } else if (component instanceof JSlider slider) { // Slider
            if (colors.checkSlider()) {
                slider.setUI(new CustomSliderUI(
                        slider,
                        colors.track,
                        colors.thumb,
                        colors.focus)
                );
            }
        } else if (component instanceof JScrollPane scrollpane) { // Scrollbars
            if (colors.checkScrollBar()) {
                scrollpane.getVerticalScrollBar().setUI(new CustomScrollPaneUI(
                        colors.track,
                        colors.thumb)
                );
                scrollpane.getHorizontalScrollBar().setUI(new CustomScrollPaneUI(
                        colors.track,
                        colors.thumb)
                );
            }
        } else if (component instanceof JList list) { // List
            if (colors.selectionBackground != null) {
                list.setSelectionBackground(colors.selectionBackground);
            }

            if (colors.selectionForeground != null) {
                list.setSelectionForeground(colors.selectionForeground);
            }
        } else if (component instanceof JTable table) { // Table
            if (colors.gridColor != null) {
                table.setGridColor(colors.gridColor);
            }

            if (colors.selectionBackground != null) {
                table.setSelectionBackground(colors.selectionBackground);
            }

            if (colors.selectionForeground != null) {
                table.setSelectionForeground(colors.selectionForeground);
            }
        } else if (component instanceof JSpinner spinner) { // Spinner
            if (colors.checkSpinner()) {
                spinner.setUI(new CustomSpinnerUI(
                        colors.background,
                        colors.foreground,
                        colors.arrowButtonBackground,
                        colors.arrowButtonForeground,
                        colors.disabledForeground)
                );
            }
        } else if (component instanceof JTabbedPane tabbedpane) { // TabbedPane
            if (colors.checkTabbedPane()) {
                tabbedpane.setUI(new CustomTabbedPaneUI(
                        colors.background,
                        colors.foreground,
                        colors.selectionBackground,
                        colors.selectionForeground,
                        colors.background)
                );
            }
        } else if (component instanceof JSplitPane) { // SplitPane
            if (colors.background != null) {
                UIManager.put("SplitPane.background", colors.background);
            }

            if (colors.foreground != null) {
                UIManager.put("SplitPane.foreground", colors.foreground);
            }

            if (colors.thumb != null) {
                UIManager.put("SplitPaneDivider.draggingColor", colors.dragging);
            }
        } else if (component instanceof JProgressBar progressbar) { // ProgressBar
            if (colors.checkProgressBar()) {
//                UIManager.put("ProgressBar.background", colors.background);
//                UIManager.put("ProgressBar.foreground", colors.foreground);
//                UIManager.put("ProgressBar.selectionBackground", colors.selectionBackground);
//                UIManager.put("ProgressBar.selectionForeground", colors.selectionForeground);
//                progressbar.setUI(new CustomProgressBarUI(
//                        colors.foreground,
//                        colors.background,
//                        colors.selectionForeground)
//                );
            }
        } else if (component instanceof JComboBox combobox) {
            if (colors.checkComboBox()) {
                combobox.getEditor().getEditorComponent().setBackground(colors.background);
                combobox.setUI(new CustomComboBoxUI(
                        colors.background,
                        colors.foreground,
                        colors.selectionBackground,
                        colors.selectionForeground,
                        colors.arrowButtonBackground,
                        colors.arrowButtonForeground)
                );
            }
        }
    }

    public Theme matchTheme(String themeName) {
        for (Theme theme : themes) {
            if (theme.getName().equals(themeName)) {
                return theme;
            }
        }
        return null;
    }

    /**
     * Adds a theme to the themes field of the ThemeCollection object
     *
     * @param theme Theme to be added
     */
    public void addTheme(Theme theme) {
        themes.add(theme);
    }

    public final void loadThemes(String path) {
        File file = new File(path);
        themes.clear();
        ArrayList<String> yamlFiles = Utils.listFiles(file);

        for (int i = 0; i < yamlFiles.size(); i++) {
            Theme newTheme = null;

            String osName = System.getProperty("os.name");

            // You can perform OS-specific operations based on the value of osName
            if (osName.toLowerCase().contains("windows")) {
                newTheme = new Theme(new File(file.getPath() + "\\" + yamlFiles.get(i)));
            } else if (osName.toLowerCase().contains("linux")) {
                newTheme = new Theme(new File(file.getPath() + "/" + yamlFiles.get(i)));
            } else {
                System.err.println("Unsupported operating system.");
            }

            themes.add(newTheme);
        }
    }

    /**
     * Empties the themes field of the ThemeCollection object
     *
     */
    public void clear() {
        themes.clear();
    }

    public ArrayList<Theme> getThemes() {
        return themes;
    }

    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }

    @Override
    public String toString() {
        String s = "";

        s = s + "Themes{";
        for (int i = 0; i < themes.size(); i++) {
            s = s + themes.get(i);
            s = s + "\n";
        }
        s = s + "}";

        return s;
    }

}
