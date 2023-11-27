/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import kdesp73.themeLib.Colors;
import kdesp73.themeLib.Theme;

/**
 *
 * @author konstantinos
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException, IOException {
		Theme t = new Theme("TestTheme");
		Colors bg = new Colors();
		bg.background = Color.red;
		bg.foreground  = Color.white;
		
        
        Colors progressbar = new Colors();
        progressbar.background = Color.magenta;
        progressbar.foreground = Color.black;
        
        Colors list = new Colors();
        list.background = Color.BLACK;
        list.foreground = Color.WHITE;
        list.selectionBackground = Color.GRAY;
        list.selectionForeground = Color.white;
        
        Colors comboBox = new Colors();
        comboBox.background = Color.BLUE;
        comboBox.foreground = Color.ORANGE;
        comboBox.selectionBackground = Color.GREEN;
        comboBox.selectionForeground = Color.WHITE;
        comboBox.arrowButtonBackground = Color.BLACK;
        comboBox.arrowButtonForeground = Color.BLACK;
        
        Colors button = new Colors();
        button.background = Color.green;
        button.foreground = Color.black;
        
        Colors splitpane = new Colors();
        splitpane.dragging = Color.GREEN;
        
        
        splitpane.key = "splitpane";
        System.out.println(splitpane.toYAMLString());
        
//		t.put("bg", bg);
//        t.put("progress_bar", progressbar);
//        t.put("list", list);
//        
//		t.generateYaml("/home/konstantinos/personal/repos/java/Swing-Themes-Library/src/test/java/");
    }
}
