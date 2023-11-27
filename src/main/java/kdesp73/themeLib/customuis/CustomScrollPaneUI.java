package kdesp73.themeLib.customuis;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollPaneUI extends BasicScrollBarUI {

	private Color trackColor;
	private Color thumbColor;

	public CustomScrollPaneUI(Color trackColor, Color thumbColor) {
		this.trackColor = trackColor;
		this.thumbColor = thumbColor;
	}

	@Override
	protected void configureScrollBarColors() {
		super.configureScrollBarColors();
		trackColor = UIManager.getColor("ScrollBar.track");
		thumbColor = UIManager.getColor("ScrollBar.thumb");
	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		g.setColor(trackColor);
		g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		g.setColor(thumbColor);
		g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Custom ScrollPane Example");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JTextArea textArea = new JTextArea(10, 30);
			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.getVerticalScrollBar().setUI(new CustomScrollPaneUI(Color.LIGHT_GRAY, Color.GRAY));

			frame.getContentPane().add(scrollPane);
			frame.setSize(400, 300);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}
