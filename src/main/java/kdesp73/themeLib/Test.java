/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.themeLib;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;

/**
 *
 * @author konstantinos
 */
public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Theme theme = Theme.parseYaml(new YamlFile("/home/konstantinos/Documents/TestTheme.yml"));

		System.out.println(theme);
	}
}
