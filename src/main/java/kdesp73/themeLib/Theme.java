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

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author KDesp73
 */
public class Theme {

	private String name;
	private String json;
	private YamlFile yaml;

	private Color bg = Color.black;
	private Color bg_2 = Color.black;
	private Color fg = Color.black;
	private Color fg_2 = Color.black;
	private Color btn = Color.black;
	private Color btn_fg = Color.black;
	private Color textbox = Color.black;
	private Color textbox_fg = Color.black;
	private Color list = Color.black;
	private Color list_fg = Color.black;
	private Color list_focus = Color.black;
	private Color scrollbar = Color.black;
	private Color progress_bar = Color.black;
	private Color slider_track = Color.black;
	private Color slider_thumb = Color.black;
	private Color extra_0 = Color.black;
	private Color extra_1 = Color.black;
	private Color extra_2 = Color.black;
	private Color extra_3 = Color.black;
	private Color extra_4 = Color.black;
	private Color extra_5 = Color.black;
	private Color extra_6 = Color.black;
	private Color extra_7 = Color.black;
	private Color extra_8 = Color.black;
	private Color extra_9 = Color.black;
	private ArrayList<Color> extras = new ArrayList<>();

	/**
	 * Creates a theme using a JSON string
	 *
	 * @param json JsonString object to be parsed
	 */
	public Theme(JsonString json) {
		this.json = json.getJson();

		// Parse json into theme
		parseJson(json);
	}

	/**
	 * Creates a theme using a YAML file
	 *
	 * @param yaml YamlFile object to be parsed
	 */
	public Theme(YamlFile yaml) {
		this.yaml = yaml;

		try {
			// Parse yaml into theme
			parseYaml(yaml);
		} catch (KeyNotFoundException | IOException ex) {
			Logger.getLogger(Theme.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Default constructor
	 */
	public Theme() {
	}

	/**
	 * Parse information from a YAML file into the Theme object
	 *
	 * @param yaml YamlFile object to be parsed
	 * @throws KeyNotFoundException
	 * @throws IOException
	 */
	public void parseYaml(YamlFile yaml) throws KeyNotFoundException, IOException {
		name = (String) yaml.getValue("name");
		bg = Utils.hexToColor(yaml.getValue("bg").toString());
		bg_2 = Utils.hexToColor(yaml.getValue("bg_2").toString());
		fg = Utils.hexToColor(yaml.getValue("fg").toString());
		fg_2 = Utils.hexToColor(yaml.getValue("fg_2").toString());
		btn = Utils.hexToColor(yaml.getValue("btn").toString());
		btn_fg = Utils.hexToColor(yaml.getValue("btn_fg").toString());
		textbox = Utils.hexToColor(yaml.getValue("textbox").toString());
		textbox_fg = Utils.hexToColor(yaml.getValue("textbox_fg").toString());
		list = Utils.hexToColor(yaml.getValue("list").toString());
		list_fg = Utils.hexToColor(yaml.getValue("list_fg").toString());
		list_focus = Utils.hexToColor(yaml.getValue("list_focus").toString());
		scrollbar = Utils.hexToColor(yaml.getValue("scrollbar").toString());
		progress_bar = Utils.hexToColor(yaml.getValue("progress_bar").toString());
		slider_track = Utils.hexToColor(yaml.getValue("slider_track").toString());
		slider_thumb = Utils.hexToColor(yaml.getValue("slider_thumb").toString());
		extra_0 = Utils.hexToColor(yaml.getValue("extra_0").toString());
		extra_1 = Utils.hexToColor(yaml.getValue("extra_1").toString());
		extra_2 = Utils.hexToColor(yaml.getValue("extra_2").toString());
		extra_3 = Utils.hexToColor(yaml.getValue("extra_3").toString());
		extra_4 = Utils.hexToColor(yaml.getValue("extra_4").toString());
		extra_5 = Utils.hexToColor(yaml.getValue("extra_5").toString());
		extra_6 = Utils.hexToColor(yaml.getValue("extra_6").toString());
		extra_7 = Utils.hexToColor(yaml.getValue("extra_7").toString());
		extra_8 = Utils.hexToColor(yaml.getValue("extra_8").toString());
		extra_9 = Utils.hexToColor(yaml.getValue("extra_9").toString());

		extras.add(extra_0);
		extras.add(extra_1);
		extras.add(extra_2);
		extras.add(extra_3);
		extras.add(extra_4);
		extras.add(extra_5);
		extras.add(extra_6);
		extras.add(extra_7);
		extras.add(extra_8);
		extras.add(extra_9);

		this.yaml = yaml;
	}

	/**
	 * Parse information from a JSON string into the Theme object
	 *
	 * @param jsonString JsonString object to be parsed
	 * @throws KeyNotFoundException
	 */
	public void parseJson(JsonString jsonString) {
		String newJson = jsonString.getJson().replaceAll(",", ", ");

		name = Utils.getJsonValue(newJson, "name").replaceAll("\"", "");
		bg = Utils.hexToColor(Utils.getJsonValue(newJson, "bg").replaceAll("\"", ""));
		fg = Utils.hexToColor(Utils.getJsonValue(newJson, "fg").replaceAll("\"", ""));
		bg_2 = Utils.hexToColor(Utils.getJsonValue(newJson, "bg_2").replaceAll("\"", ""));
		fg_2 = Utils.hexToColor(Utils.getJsonValue(newJson, "fg_2").replaceAll("\"", ""));
		btn = Utils.hexToColor(Utils.getJsonValue(newJson, "btn").replaceAll("\"", ""));
		btn_fg = Utils.hexToColor(Utils.getJsonValue(newJson, "btn_fg").replaceAll("\"", ""));
		textbox = Utils.hexToColor(Utils.getJsonValue(newJson, "textbox").replaceAll("\"", ""));
		textbox_fg = Utils.hexToColor(Utils.getJsonValue(newJson, "textbox_fg").replaceAll("\"", ""));
		list = Utils.hexToColor(Utils.getJsonValue(newJson, "list").replaceAll("\"", ""));
		list_fg = Utils.hexToColor(Utils.getJsonValue(newJson, "list_fg").replaceAll("\"", ""));
		list_focus = Utils.hexToColor(Utils.getJsonValue(newJson, "list_focus").replaceAll("\"", ""));
		scrollbar = Utils.hexToColor(Utils.getJsonValue(newJson, "scrollbar").replaceAll("\"", ""));
		progress_bar = Utils.hexToColor(Utils.getJsonValue(newJson, "progress_bar").replaceAll("\"", ""));
		slider_track = Utils.hexToColor(Utils.getJsonValue(newJson, "slider_track").replaceAll("\"", ""));
		slider_thumb = Utils.hexToColor(Utils.getJsonValue(newJson, "slider_thumb").replaceAll("\"", ""));
		extra_0 = Utils.hexToColor(Utils.getJsonValue(newJson, "extra_0").replaceAll("\"", ""));
		extra_1 = Utils.hexToColor(Utils.getJsonValue(newJson, "extra_1").replaceAll("\"", ""));
		extra_2 = Utils.hexToColor(Utils.getJsonValue(newJson, "extra_2").replaceAll("\"", ""));
		extra_3 = Utils.hexToColor(Utils.getJsonValue(newJson, "extra_3").replaceAll("\"", ""));
		extra_4 = Utils.hexToColor(Utils.getJsonValue(newJson, "extra_4").replaceAll("\"", ""));
		extra_5 = Utils.hexToColor(Utils.getJsonValue(newJson, "extra_5").replaceAll("\"", ""));
		extra_6 = Utils.hexToColor(Utils.getJsonValue(newJson, "extra_6").replaceAll("\"", ""));
		extra_7 = Utils.hexToColor(Utils.getJsonValue(newJson, "extra_7").replaceAll("\"", ""));
		extra_8 = Utils.hexToColor(Utils.getJsonValue(newJson, "extra_8").replaceAll("\"", ""));
		extra_9 = Utils.hexToColor(Utils.getJsonValue(newJson, "extra_9").replaceAll("\"", ""));

		extras.add(extra_0);
		extras.add(extra_1);
		extras.add(extra_2);
		extras.add(extra_3);
		extras.add(extra_4);
		extras.add(extra_5);
		extras.add(extra_6);
		extras.add(extra_7);
		extras.add(extra_8);
		extras.add(extra_9);

		this.json = newJson;
	}

	/**
	 * Generate a YAML file containing the information of this Theme object
	 *
	 * @param target_dir The directory into which the YAML file will be created
	 * @return YamlFile object
	 * @throws FileNotFoundException
	 */
	public YamlFile generateYaml(String target_dir) throws FileNotFoundException {
		try (PrintWriter writer = new PrintWriter(new File(target_dir))) {
			writer.write("name: " + name);
			writer.write("\n");
			writer.write("bg: \"" + Utils.ColorToHex(bg) + "\"");
			writer.write("\n");
			writer.write("fg: \"" + Utils.ColorToHex(fg) + "\"");
			writer.write("\n");
			writer.write("bg_2: \"" + Utils.ColorToHex(bg_2) + "\"");
			writer.write("\n");
			writer.write("fg_2: \"" + Utils.ColorToHex(fg_2) + "\"");
			writer.write("\n");
			writer.write("btn: \"" + Utils.ColorToHex(btn) + "\"");
			writer.write("\n");
			writer.write("btn_fg: \"" + Utils.ColorToHex(btn_fg) + "\"");
			writer.write("\n");
			writer.write("textbox: \"" + Utils.ColorToHex(textbox) + "\"");
			writer.write("\n");
			writer.write("textbox_fg: \"" + Utils.ColorToHex(textbox_fg) + "\"");
			writer.write("\n");
			writer.write("list: \"" + Utils.ColorToHex(list) + "\"");
			writer.write("\n");
			writer.write("list_fg: \"" + Utils.ColorToHex(list_fg) + "\"");
			writer.write("\n");
			writer.write("list_focus: \"" + Utils.ColorToHex(list_focus) + "\"");
			writer.write("\n");
			writer.write("scrollbar: \"" + Utils.ColorToHex(scrollbar) + "\"");
			writer.write("\n");
			writer.write("progress_bar: \"" + Utils.ColorToHex(progress_bar) + "\"");
			writer.write("\n");
			writer.write("slider_track: \"" + Utils.ColorToHex(slider_track) + "\"");
			writer.write("\n");
			writer.write("slider_thumb: \"" + Utils.ColorToHex(slider_thumb) + "\"");
			writer.write("\n");
			writer.write("extra_0: \"" + Utils.ColorToHex(extra_0) + "\"");
			writer.write("\n");
			writer.write("extra_1: \"" + Utils.ColorToHex(extra_1) + "\"");
			writer.write("\n");
			writer.write("extra_2: \"" + Utils.ColorToHex(extra_2) + "\"");
			writer.write("\n");
			writer.write("extra_3: \"" + Utils.ColorToHex(extra_3) + "\"");
			writer.write("\n");
			writer.write("extra_4: \"" + Utils.ColorToHex(extra_4) + "\"");
			writer.write("\n");
			writer.write("extra_5: \"" + Utils.ColorToHex(extra_5) + "\"");
			writer.write("\n");
			writer.write("extra_6: \"" + Utils.ColorToHex(extra_6) + "\"");
			writer.write("\n");
			writer.write("extra_7: \"" + Utils.ColorToHex(extra_7) + "\"");
			writer.write("\n");
			writer.write("extra_8: \"" + Utils.ColorToHex(extra_8) + "\"");
			writer.write("\n");
			writer.write("extra_9: \"" + Utils.ColorToHex(extra_9) + "\"");
			writer.write("\n");
		}

		return new YamlFile(target_dir);
	}

	/**
	 * Generate a JSON string containing the information of this Theme object
	 *
	 * @return JsonString
	 */
	public JsonString generateJson() {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("name", name);
		jsonObject.put("bg", Utils.ColorToHex(bg));
		jsonObject.put("fg", Utils.ColorToHex(fg));
		jsonObject.put("bg_2", Utils.ColorToHex(bg_2));
		jsonObject.put("fg_2", Utils.ColorToHex(fg_2));
		jsonObject.put("btn", Utils.ColorToHex(btn));
		jsonObject.put("btn_fg", Utils.ColorToHex(btn_fg));
		jsonObject.put("textbox", Utils.ColorToHex(textbox));
		jsonObject.put("textbox_fg", Utils.ColorToHex(textbox_fg));
		jsonObject.put("list", Utils.ColorToHex(list));
		jsonObject.put("list_fg", Utils.ColorToHex(list_fg));
		jsonObject.put("list_focus", Utils.ColorToHex(list_focus));
		jsonObject.put("scrollbar", Utils.ColorToHex(scrollbar));
		jsonObject.put("progress_bar", Utils.ColorToHex(progress_bar));
		jsonObject.put("slider_track", Utils.ColorToHex(slider_track));
		jsonObject.put("slider_thumb", Utils.ColorToHex(slider_thumb));
		jsonObject.put("extra_0", Utils.ColorToHex(extra_0));
		jsonObject.put("extra_1", Utils.ColorToHex(extra_1));
		jsonObject.put("extra_2", Utils.ColorToHex(extra_2));
		jsonObject.put("extra_3", Utils.ColorToHex(extra_3));
		jsonObject.put("extra_4", Utils.ColorToHex(extra_4));
		jsonObject.put("extra_5", Utils.ColorToHex(extra_5));
		jsonObject.put("extra_6", Utils.ColorToHex(extra_6));
		jsonObject.put("extra_7", Utils.ColorToHex(extra_7));
		jsonObject.put("extra_8", Utils.ColorToHex(extra_8));
		jsonObject.put("extra_9", Utils.ColorToHex(extra_9));

		this.json = jsonObject.toString();

		return new JsonString(jsonObject.toString());
	}

	private class Utils {

		static Color hexToColor(String hex) {
			hex = "#" + hex;
			return Color.decode(hex);
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

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public YamlFile getYaml() {
		return yaml;
	}

	public void setYaml(YamlFile yaml) {
		this.yaml = yaml;
	}

	public Color getBg() {
		return bg;
	}

	public void setBg(Color bg) {
		this.bg = bg;
	}

	public Color getBg_2() {
		return bg_2;
	}

	public void setBg_2(Color bg_2) {
		this.bg_2 = bg_2;
	}

	public Color getFg() {
		return fg;
	}

	public void setFg(Color fg) {
		this.fg = fg;
	}

	public Color getFg_2() {
		return fg_2;
	}

	public void setFg_2(Color fg_2) {
		this.fg_2 = fg_2;
	}

	public Color getBtn() {
		return btn;
	}

	public void setBtn(Color btn) {
		this.btn = btn;
	}

	public Color getBtn_fg() {
		return btn_fg;
	}

	public void setBtn_fg(Color btn_fg) {
		this.btn_fg = btn_fg;
	}

	public Color getTextbox() {
		return textbox;
	}

	public void setTextbox(Color textbox) {
		this.textbox = textbox;
	}

	public Color getTextbox_fg() {
		return textbox_fg;
	}

	public void setTextbox_fg(Color textbox_fg) {
		this.textbox_fg = textbox_fg;
	}

	public Color getList() {
		return list;
	}

	public void setList(Color list) {
		this.list = list;
	}

	public Color getList_fg() {
		return list_fg;
	}

	public void setList_fg(Color list_fg) {
		this.list_fg = list_fg;
	}

	public Color getList_focus() {
		return list_focus;
	}

	public void setList_focus(Color list_focus) {
		this.list_focus = list_focus;
	}

	public Color getScrollbar() {
		return scrollbar;
	}

	public void setScrollbar(Color scrollbar) {
		this.scrollbar = scrollbar;
	}

	public Color getProgress_bar() {
		return progress_bar;
	}

	public void setProgress_bar(Color progress_bar) {
		this.progress_bar = progress_bar;
	}

	public ArrayList<Color> getExtras() {
		return extras;
	}

	public Color getExtra(int index) {
		return extras.get(index);
	}

	public Color getSliderTrack(){
		return this.slider_track;
	}

	public void setSliderTrack(Color color){
		this.slider_track = color;
	}

	public Color getSliderThumb(){
		return this.slider_thumb;
	}

	public void setSliderThumb(Color color){
		this.slider_thumb = color;
	}

	public void setExtras(ArrayList<Color> extras) {
		this.extras = extras;
		extra_0 = extras.get(0);
		extra_1 = extras.get(1);
		extra_2 = extras.get(2);
		extra_3 = extras.get(3);
		extra_4 = extras.get(4);
		extra_5 = extras.get(5);
		extra_6 = extras.get(6);
		extra_7 = extras.get(7);
		extra_8 = extras.get(8);
		extra_9 = extras.get(9);

	}

	public void setExtra(int index, Color extra) {
		if(index < 0 || index > 9){
			System.err.println("Incorrect index");
			return;
		}

		extras.add(index, extra);

		switch(index){
			case 0:
				extra_0 = extra;
				break;
			case 1:
				extra_1 = extra;
				break;
			case 2:
				extra_2 = extra;
				break;
			case 3:
				extra_3 = extra;
				break;
			case 4:
				extra_4 = extra;
				break;
			case 5:
				extra_5 = extra;
				break;
			case 6:
				extra_6 = extra;
				break;
			case 7:
				extra_7 = extra;
				break;
			case 8:
				extra_8 = extra;
				break;
			case 9:
				extra_9 = extra;
				break;
			default:
				System.err.println("Incorrect index");
				return;
		}
	}

	@Override
	public String toString() {
		String s = "";

		s = s + pair("Name", name);
		if (yaml != null) {
			s = s + pair("YAML", yaml.getContents().toString());
		} else {
			s = s + pair("YAML", null);
		}
		if (json != null) {
			s = s + pair("JSON", json);
		} else {
			s = s + pair("JSON", null);
		}

		return s;
	}

	private String pair(String key, String value) {
		return "" + key + ": " + value + "\n";
	}

}
