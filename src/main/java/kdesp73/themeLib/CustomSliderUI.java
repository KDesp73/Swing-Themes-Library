package kdesp73.themeLib;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CustomSliderUI extends BasicSliderUI {

	private Color trackColor;
	private Color thumbColor;
	private Color focusColor;

	public CustomSliderUI(JSlider slider, Color trackColor, Color thumbColor, Color focusColor) {
		super(slider);
		this.trackColor = trackColor;
		this.thumbColor = thumbColor;
		this.focusColor = focusColor;
	}

	@Override
	protected Dimension getThumbSize() {
		return new Dimension(12, 12); // Set the size of the thumb
	}

	@Override
	protected Color getFocusColor() {
		return focusColor;
	}

	@Override
	public void paintTrack(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Rectangle trackBounds = trackRect;

		g2d.setColor(trackColor);
		g2d.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
	}

	@Override
	public void paintThumb(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Rectangle2D thumbBounds = thumbRect;

		g2d.setColor(thumbColor);
		g2d.fillRoundRect((int) thumbBounds.getX(), (int) thumbBounds.getY(),
				(int) thumbBounds.getWidth(), (int) thumbBounds.getHeight(), 10, 10);
	}
}
