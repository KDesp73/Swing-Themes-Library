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

import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JSlider;

/**
 *
 * @author KDesp73
 */
public class ThemeCollection {

	private ArrayList<Theme> themes = new ArrayList<>();

	public ThemeCollection() {

	}

	/**
	 * This method searches through every
	 * child of the parent container and
	 * applies the appropriate colors if the
	 * name matches the predetermined ones
	 *
	 * @param component The parent container
	 *                  the theme will be applied to
	 * @param theme     Theme of choice
	 */
	public static void applyTheme(Component component, Theme theme) {
		String name = component.getName();

		try {
			switch (name) {
				case "null.glassPane":
					break;
				case "null.layeredPane":
					break;
				case "null.contentPane":
					break;
				case "bg":
					component.setBackground(theme.getBg());
					break;
				case "bg_2":
					component.setBackground(theme.getBg_2());
					break;
				case "fg":
					component.setForeground(theme.getFg());
					break;
				case "fg_2":
					component.setForeground(theme.getFg_2());
					break;
				case "btn":
					component.setBackground(theme.getBtn());
					component.setForeground(theme.getBtn_fg());
					break;
				case "textbox":
					component.setBackground(theme.getTextbox());
					component.setForeground(theme.getTextbox_fg());
					break;
				case "list":
					if (component instanceof JList jList) {
						jList.setBackground(theme.getList());
						jList.setForeground(theme.getList_fg());
						jList.setSelectionBackground(theme.getList_focus());
					}
					break;
				case "scrollbar":
					component.setBackground(theme.getBg());
					break;
				case "progress_bar":
					component.setBackground(theme.getProgress_bar());
					break;
				case "slider":
					// if(component instanceof JSlider slider){
					// 	slider.setBackground(theme.getSliderBg());
					// 	slider.setUI(new BasicSliderUI(slider) {
					// 		@Override
					// 		public void paintThumb(Graphics g) {
					// 			Graphics2D g2d = (Graphics2D) g;
					// 			g2d.setPaint(Color.ORANGE); // Customize thumb color
					// 			g2d.fill(thumbRect);
					// 		}
					// 	});
					// }
				case "extra_0":
					component.setBackground(theme.getExtras().get(0));
					break;
				case "extra_1":
					component.setBackground(theme.getExtras().get(1));
					break;
				case "extra_2":
					component.setBackground(theme.getExtras().get(2));
					break;
				case "extra_3":
					component.setBackground(theme.getExtras().get(3));
					break;
				case "extra_4":
					component.setBackground(theme.getExtras().get(4));
					break;
				case "extra_5":
					component.setBackground(theme.getExtras().get(5));
					break;
				case "extra_6":
					component.setBackground(theme.getExtras().get(6));
					break;
				case "extra_7":
					component.setBackground(theme.getExtras().get(7));
					break;
				case "extra_8":
					component.setBackground(theme.getExtras().get(8));
					break;
				case "extra_9":
					component.setBackground(theme.getExtras().get(9));
					break;
				default:
					break;
			}
		} catch (NullPointerException e) {
			// System.out.println("Null name");
		}

		// Recurse through every component
		if (component instanceof Container container) {
			for (Component child : container.getComponents()) {
				applyTheme(child, theme);
			}
		}
	}

	public Theme matchTheme(String themeName) {
		for (Theme theme : themes) {
			if (theme.getName().equals(themeName))
				return theme;
		}
		return null;
	}

	/**
	 * Adds a theme to the themes field of
	 * the ThemeCollection object
	 *
	 * @param theme Theme to be added
	 */
	public void addTheme(Theme theme) {
		themes.add(theme);
	}

	public void loadThemes(File folder_dir) {
		themes.clear();
		ArrayList<String> yamlFiles = Utils.listFiles(folder_dir);

		for (int i = 0; i < yamlFiles.size(); i++) {
			Theme newTheme = null;

			String osName = System.getProperty("os.name");

			// You can perform OS-specific operations based on the value of osName
			if (osName.toLowerCase().contains("windows")) {
				newTheme = new Theme(new YamlFile(folder_dir.getPath() + "\\" + yamlFiles.get(i)));
			} else if (osName.toLowerCase().contains("linux")) {
				newTheme = new Theme(new YamlFile(folder_dir.getPath() + "/" + yamlFiles.get(i)));
			} else {
				System.out.println("Unsupported operating system.");
			}

			themes.add(newTheme);
		}
	}

	/**
	 * Loads themes from a Array of JSON
	 * strings
	 *
	 * @param jsons Array of appropriate JSON
	 *              strings to be loaded in the ArrayList
	 *              field of the ThemeCollection object
	 */
	public void loadThemes(String[] jsons) {
		themes.clear();
		for (String json : jsons) {
			Theme newTheme = new Theme(new JsonString(json));
			themes.add(newTheme);
		}
	}

	/**
	 * Empties the themes field of
	 * the ThemeCollection object
	 *
	 */
	public void clear() {
		themes.clear();
	}

	private class Utils {

		private static ArrayList<String> listFiles(final File folder) {
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
