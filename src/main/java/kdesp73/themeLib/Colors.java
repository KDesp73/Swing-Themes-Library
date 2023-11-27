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
	public Color focusBorder = null;
	public Color disabledText = null;
	public Color rollover = null;
	public Color pressed = null;
	public Color disabledBackground = null;
	public Color toolTipBackground = null;
	public Color toolTipText = null;
	public boolean focusPainted = false; // For buttons
	public Color shadow = null;
	public Color highlight = null;
	public Color linkColor = null;
	public Color gridColor = null; // For components with grids
	public Color thumb = null; // For scroll bars
	public Color track = null; // For scroll bars
	public Color arrowButtonBackground = null; // For components with arrow buttons
	public Color arrowButtonForeground = null; // For components with arrow buttons
	public Color carret = null;

	public boolean checkButton() {
		boolean isComplete = true;

		isComplete = isComplete && (background != null);
		isComplete = isComplete && (foreground != null);
		isComplete = isComplete && (rollover != null);
		isComplete = isComplete && (pressed != null);

		return isComplete;
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
		appendColorPair(sb, "focusBorder", focusBorder);
		appendColorPair(sb, "disabledText", disabledText);
		appendColorPair(sb, "rollover", rollover);
		appendColorPair(sb, "pressed", pressed);
		appendColorPair(sb, "disabledBackground", disabledBackground);
		appendColorPair(sb, "toolTipBackground", toolTipBackground);
		appendColorPair(sb, "toolTipText", toolTipText);
		sb.append("    ").append("focusPainted").append(": ").append(focusPainted).append("\n");
		appendColorPair(sb, "shadow", shadow);
		appendColorPair(sb, "highlight", highlight);
		appendColorPair(sb, "linkColor", linkColor);
		appendColorPair(sb, "gridColor", gridColor);
		appendColorPair(sb, "thumb", thumb);
		appendColorPair(sb, "track", track);
		appendColorPair(sb, "arrowButtonBackground", arrowButtonBackground);
		appendColorPair(sb, "arrowButtonForeground", arrowButtonForeground);
		appendColorPair(sb, "carret", carret);

		return sb.toString();
	}

	private void appendColorPair(StringBuilder sb, String attributeName, Color color) {
		sb.append("    ").append(attributeName).append(": ").append((color == null ? "null" : Utils.ColorToHex(color))).append("\n");
	}

}
