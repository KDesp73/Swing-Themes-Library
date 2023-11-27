/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.themeLib;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComponent;
import javax.swing.border.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author konstantinos
 */
public class Utils {

    private static boolean isHexadecimal(String input) {
        final Matcher matcher = Pattern.compile("\\p{XDigit}+").matcher(input);
        return matcher.matches();
    }

    public static Object or(Object obj, Object or) {
        return (obj == null) ? or : obj;
    }

    public static Color hexToColor(Object hex) {
        if (hex == null) {
            return null;
        }

        if (!isHexadecimal(hex.toString())) {
            return null;
        }
        
        hex = "#" + hex;
        return Color.decode(hex.toString());
    }

    static String ColorToHex(Color c) {
        return String.format("%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    static String getJsonValue(String JsonString, String tag) {
        tag = "\"" + tag + "\"";
        int tagIndex = JsonString.indexOf(tag);

        if (tagIndex == -1) {
            return null;
        }

        int begin = tagIndex + tag.length() + 2; // "+2" For space and comma
        int end = JsonString.indexOf(',', begin);

        if (end == -1) {
            end = JsonString.indexOf('}', begin);
        }

        String value = JsonString.substring(begin, end);
        return value.replaceAll("\\]", "").replaceAll("\\[", "").replaceAll("\\}", "")
                .replaceAll("\n", "").strip();
    }

    public static String pair(String key, String value) {
        return "" + key + ": " + value + "\n";
    }

    public static ArrayList<String> listFiles(final File folder) {
        ArrayList<String> arr = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFiles(fileEntry);
            } else {
                arr.add(fileEntry.getName());
            }
        }
        return arr;
    }

    public static String readFile(String path) {
        String content = "";
        try {
            // Create a FileReader to read the file
            FileReader fileReader = new FileReader(path);

            // Create a BufferedReader to efficiently read the file line by line
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content += line + "\n";
            }

            // Close the BufferedReader and FileReader when done
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static void changeBorderColor(JComponent component, Color newColor) {
        // Get the existing border
        Border existingBorder = component.getBorder();

        // Handle different types of borders
        if (existingBorder instanceof LineBorder) {
            changeLineColorBorder((LineBorder) existingBorder, newColor, component);
        } else if (existingBorder instanceof CompoundBorder) {
            changeCompoundBorder((CompoundBorder) existingBorder, newColor, component);
        } else if (existingBorder instanceof EtchedBorder) {
            changeEtchedBorder((EtchedBorder) existingBorder, newColor, component);
        } else if (existingBorder instanceof BevelBorder) {
            throw new RuntimeException("Not implemented yet.");
        } else if (existingBorder instanceof MatteBorder) {
            throw new RuntimeException("Not implemented yet.");
        } else {
            // For unknown border types, change to a LineBorder with the new color
            component.setBorder(new LineBorder(newColor));
        }
    }

    private static void changeLineColorBorder(LineBorder existingLineBorder, Color newColor, JComponent component) {
        // Create a new LineBorder with the desired color while keeping other properties unchanged
        LineBorder newLineBorder = new LineBorder(newColor, existingLineBorder.getThickness());

        // Set the new LineBorder on the component
        component.setBorder(newLineBorder);
    }

    private static void changeCompoundBorder(CompoundBorder existingCompoundBorder, Color newColor, JComponent component) {
        // Get the inner and outer borders
        Border innerBorder = existingCompoundBorder.getInsideBorder();
        Border outerBorder = existingCompoundBorder.getOutsideBorder();

        // Change the inner border color
        changeBorderColorComponent(innerBorder, newColor);

        // Set the modified CompoundBorder on the component
        component.setBorder(new CompoundBorder(outerBorder, innerBorder));
    }

    private static void changeEtchedBorder(EtchedBorder existingEtchedBorder, Color newColor, JComponent component) {
        // Create a new EtchedBorder with the desired color while keeping other properties unchanged
        EtchedBorder newEtchedBorder = new EtchedBorder(existingEtchedBorder.getEtchType(), newColor, existingEtchedBorder.getHighlightColor());

        // Set the new EtchedBorder on the component
        component.setBorder(newEtchedBorder);
    }

    private static void changeBorderColorComponent(Border existingBorder, Color newColor) {
        if (existingBorder instanceof LineBorder) {
            changeLineColorBorder((LineBorder) existingBorder, newColor, null);
        } else if (existingBorder instanceof CompoundBorder) {
            changeCompoundBorder((CompoundBorder) existingBorder, newColor, null);
        } else if (existingBorder instanceof EtchedBorder) {
            changeEtchedBorder((EtchedBorder) existingBorder, newColor, null);
        } else if (existingBorder instanceof BevelBorder) {
            throw new RuntimeException("Not implemented yet.");
        } else if (existingBorder instanceof MatteBorder) {
            throw new RuntimeException("Not implemented yet.");
        }
    }

    public static void changeHighlightColor(JTextComponent textComponent, Color newColor) {
        Highlighter highlighter = textComponent.getHighlighter();
        Highlighter.Highlight[] highlights = highlighter.getHighlights();

        DefaultHighlighter.DefaultHighlightPainter newPainter = new DefaultHighlighter.DefaultHighlightPainter(newColor);

        for (Highlighter.Highlight highlight : highlights) {
            int start = highlight.getStartOffset();
            int end = highlight.getEndOffset();

            // Remove the existing highlight
            highlighter.removeHighlight(highlight);

            try {
                // Apply the new highlight color
                highlighter.addHighlight(start, end, newPainter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
