package ProjectPackage;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainClass extends JFrame{
	ImagePanel imagePanel;
	
	public MainClass() { 
		imagePanel = new ImagePanel(); 
		setLayout(new BorderLayout(10,10));
		add(imagePanel, BorderLayout.WEST);
		imagePanel.setBackground(Color.WHITE);
		
		add(new Button(imagePanel,this), BorderLayout.EAST); 
		
	}

	public static void main(String[] args) {
		JFrame f = new MainClass();//constructor is called
		f.setTitle("Photo Manipulation Scientist");//window-title is set
		f.getContentPane().setBackground(Color.WHITE);
		f.setSize(600, 500);
		f.pack();
		
		f.getContentPane().setForeground(Color.BLUE);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);//primary window cannot be re-sizable
		f.setVisible(true);//primary window is visible
	}

}
