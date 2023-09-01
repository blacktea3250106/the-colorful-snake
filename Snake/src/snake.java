
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class snake {
	public static void main(String[] args) {

		// Set interface
		JFrame JF = new JFrame("Snake Game");
		// JF.setBounds(400, 200, 893, 720);
		JF.setSize(1000, 950);
		JF.setResizable(true);
		JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JF.getContentPane().setBackground(Color.black);
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		JF.setLocation((w-1000)/2, (h-950)/2);
		//JF.getRootPane().setBorder(BorderFactory.createMatteBorder(15, 15, 15, 15, Color.black));
		//Container container = JF.getContentPane();
		//container.setBackground(Color.black);
		SnakePanel panel = new SnakePanel();
		panel.setFocusable(true);
		JF.add(panel);
		JF.setVisible(true);
	}
}