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

import kdesp73.themeLib.exceptions.KeyNotFoundException;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author KDesp73
 */
public class Theme extends HashMap<String, Colors> {

    private String name;
    private File file = null;

    /**
     * Creates a theme using a YAML file
     *
     * @param file
     */
    public Theme(File file) {
        this.file = file;
        this.parseYaml();
    }

    /**
     * Default constructor
     */
    public Theme() {
    }

    public Theme(String name) {
        this.name = name;
    }

    public static Theme parseYaml(File file) {
        Yaml yaml = new Yaml();
        Map<String, Object> yamlData = yaml.load(Utils.readFile(file.getAbsolutePath()));

        // Extract the name of the theme
        String themeName = (String) yamlData.get("name");
        Theme theme = new Theme(themeName);

        // Remove the name entry from the map as it's already assigned to the theme
        yamlData.remove("name");

        // Iterate through the remaining entries and create Colors objects
        for (Map.Entry<String, Object> entry : yamlData.entrySet()) {
            String componentName = entry.getKey();
            Map<String, Object> colorAttributes = (LinkedHashMap<String, Object>) entry.getValue();

            Colors colors = new Colors();
            colors.key = componentName;

            colors.background = Utils.hexToColor(colorAttributes.get("background"));
            colors.foreground = Utils.hexToColor(colorAttributes.get("foreground"));
            colors.selectionBackground = Utils.hexToColor(colorAttributes.get("selectionBackground"));
            colors.selectionForeground = Utils.hexToColor(colorAttributes.get("selectionForeground"));
            colors.border = Utils.hexToColor(colorAttributes.get("border"));
            colors.focus = Utils.hexToColor(colorAttributes.get("focus"));
            colors.disabledForeground = Utils.hexToColor(colorAttributes.get("disabledForeground"));
            colors.pressed = Utils.hexToColor(colorAttributes.get("pressed"));
            colors.disabledBackground = Utils.hexToColor(colorAttributes.get("disabledBackground"));
            colors.toolTipBackground = Utils.hexToColor(colorAttributes.get("toolTipBackground"));
            colors.toolTipText = Utils.hexToColor(colorAttributes.get("toolTipText"));
            colors.shadow = Utils.hexToColor(colorAttributes.get("shadow"));
            colors.highlight = Utils.hexToColor(colorAttributes.get("highlight"));
            colors.linkColor = Utils.hexToColor(colorAttributes.get("linkColor"));
            colors.gridColor = Utils.hexToColor(colorAttributes.get("gridColor"));
            colors.thumb = Utils.hexToColor(colorAttributes.get("thumb"));
            colors.track = Utils.hexToColor(colorAttributes.get("track"));
            colors.arrowButtonBackground = Utils.hexToColor(colorAttributes.get("arrowButtonBackground"));
            colors.arrowButtonForeground = Utils.hexToColor(colorAttributes.get("arrowButtonForeground"));
            colors.caret = Utils.hexToColor(colorAttributes.get("caret"));
            colors.dragging = Utils.hexToColor(colorAttributes.get("dragging"));

            // Add Colors object to the theme
            theme.put(componentName, colors);
        }

        return theme;
    }
    
    private void parseYaml() {
        Yaml yaml = new Yaml();
        Map<String, Object> yamlData = yaml.load(Utils.readFile(file.getAbsolutePath()));

        // Extract the name of the theme
        String themeName = (String) yamlData.get("name");
        this.name = themeName;

        // Remove the name entry from the map as it's already assigned to the theme
        yamlData.remove("name");

        // Iterate through the remaining entries and create Colors objects
        for (Map.Entry<String, Object> entry : yamlData.entrySet()) {
            String componentName = entry.getKey();
            Map<String, Object> colorAttributes = (LinkedHashMap<String, Object>) entry.getValue();

            Colors colors = new Colors();
            colors.key = componentName;

            colors.background = Utils.hexToColor(colorAttributes.get("background"));
            colors.foreground = Utils.hexToColor(colorAttributes.get("foreground"));
            colors.selectionBackground = Utils.hexToColor(colorAttributes.get("selectionBackground"));
            colors.selectionForeground = Utils.hexToColor(colorAttributes.get("selectionForeground"));
            colors.border = Utils.hexToColor(colorAttributes.get("border"));
            colors.focus = Utils.hexToColor(colorAttributes.get("focus"));
            colors.disabledForeground = Utils.hexToColor(colorAttributes.get("disabledForeground"));
            colors.pressed = Utils.hexToColor(colorAttributes.get("pressed"));
            colors.disabledBackground = Utils.hexToColor(colorAttributes.get("disabledBackground"));
            colors.toolTipBackground = Utils.hexToColor(colorAttributes.get("toolTipBackground"));
            colors.toolTipText = Utils.hexToColor(colorAttributes.get("toolTipText"));
            colors.shadow = Utils.hexToColor(colorAttributes.get("shadow"));
            colors.highlight = Utils.hexToColor(colorAttributes.get("highlight"));
            colors.linkColor = Utils.hexToColor(colorAttributes.get("linkColor"));
            colors.gridColor = Utils.hexToColor(colorAttributes.get("gridColor"));
            colors.thumb = Utils.hexToColor(colorAttributes.get("thumb"));
            colors.track = Utils.hexToColor(colorAttributes.get("track"));
            colors.arrowButtonBackground = Utils.hexToColor(colorAttributes.get("arrowButtonBackground"));
            colors.arrowButtonForeground = Utils.hexToColor(colorAttributes.get("arrowButtonForeground"));
            colors.caret = Utils.hexToColor(colorAttributes.get("caret"));
            colors.dragging = Utils.hexToColor(colorAttributes.get("dragging"));
            colors.rollover = Utils.hexToColor(colorAttributes.get("rollover"));

            // Add Colors object to the theme
            this.put(componentName, colors);
        }
    }

    /**
     * Generate a YAML file containing the information of this Theme object
     *
     * @param path The directory into which the YAML file will be created
     * @return YamlFile object
     * @throws FileNotFoundException
     */
    public boolean generateYaml(String path) throws FileNotFoundException {
        char slash = System.getProperty("os.name").toLowerCase().contains("linux") ? '/' : '\\';
        if (path.charAt(path.length() - 1) != slash) {
            path += slash;
        }

        path = path + name + ".yml";

        try (PrintWriter writer = new PrintWriter(new File(path))) {
            writer.write("name: " + name);
            writer.write("\n");
            writer.write("\n");

            Set<String> keys = this.keySet();

            for (String key : keys) {
                writer.write(this.get(key).toYAMLString());
                writer.write("\n");
                writer.write("\n");
            }

            writer.write("\n");
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public Colors put(String key, Colors value) {
        value.key = key;
        return super.put(key, value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Set<String> keys = this.keySet();

        sb.append(name).append(" = {").append("\n");
        for (String key : keys) {
            sb.append("    ").append(key).append(" = {").append("\n");

            appendColorAttributes(sb, this.get(key));

            sb.append("    ").append("}\n");
        }

        sb.append("}");

        return sb.toString();
    }

    private void appendColorAttributes(StringBuilder sb, Colors colors) {
        appendColorAttribute(sb, "background", colors.background);
        appendColorAttribute(sb, "foreground", colors.foreground);
        appendColorAttribute(sb, "selectionBackground", colors.selectionBackground);
        appendColorAttribute(sb, "selectionForeground", colors.selectionForeground);
        appendColorAttribute(sb, "border", colors.border);
        appendColorAttribute(sb, "focus", colors.focus);
        appendColorAttribute(sb, "disabledForeground", colors.disabledForeground);
        appendColorAttribute(sb, "pressed", colors.pressed);
        appendColorAttribute(sb, "disabledBackground", colors.disabledBackground);
        appendColorAttribute(sb, "toolTipBackground", colors.toolTipBackground);
        appendColorAttribute(sb, "toolTipText", colors.toolTipText);
        appendColorAttribute(sb, "shadow", colors.shadow);
        appendColorAttribute(sb, "highlight", colors.highlight);
        appendColorAttribute(sb, "linkColor", colors.linkColor);
        appendColorAttribute(sb, "gridColor", colors.gridColor);
        appendColorAttribute(sb, "thumb", colors.thumb);
        appendColorAttribute(sb, "track", colors.track);
        appendColorAttribute(sb, "arrowButtonBackground", colors.arrowButtonBackground);
        appendColorAttribute(sb, "arrowButtonForeground", colors.arrowButtonForeground);
        appendColorAttribute(sb, "caret", colors.caret);
        appendColorAttribute(sb, "rollover", colors.rollover);
    }

    private void appendColorAttribute(StringBuilder sb, String key, Object value) {
        sb.append("        ").append(key).append(" = ").append(value).append("\n");
    }

}
