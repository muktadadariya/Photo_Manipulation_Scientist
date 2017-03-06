package ProjectPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.rmi.CORBA.Util;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import FileChooserPackage.File_View;
import FileChooserPackage.Filter;
import FileChooserPackage.Preview;
import FileChooserPackage.Image_Utils;

public class Button extends JPanel implements ActionListener {

	JButton invert;
	JButton sepia;
	JButton solarize;
	JButton posterize;
	JButton reset;
	JButton apply;

	JButton open;
	JButton save;
	JButton close;
	
	ImagePanel imagePanel;
	MainClass mainClass;

	private JFileChooser fileChooser;

	public Button(ImagePanel _imagePanel, MainClass _mainClass) {
		imagePanel = _imagePanel;
		mainClass = _mainClass;
		makeObjects();
		doTheLayout();


		invert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyEffect(Effect.INVERT);
			}
		});

		sepia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyEffect(Effect.SEPIA);
			}
		});

		solarize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyEffect(Effect.SOLARIZE);
			}
		});

		posterize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyEffect(Effect.POSTERIZE);
			}
		});
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.resetImage();
			}
		});

		apply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyPermanentEffect();
			}
		});

		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileChooser == null) {
					fileChooser = new JFileChooser();
					
					fileChooser.addChoosableFileFilter(new Filter());
					fileChooser.setAcceptAllFileFilterUsed(false);
					fileChooser.setFileView(new File_View());
					fileChooser.setAccessory(new Preview(fileChooser));
				}
				int returnVal = fileChooser.showDialog(mainClass, "OPEN");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					imagePanel.changeImage(file);
				} else {

				}
				fileChooser.setSelectedFile(null);
			}
		});

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(imagePanel.image == null){
					JOptionPane.showMessageDialog(mainClass, "First choose a proper image");
					return;
				}
				
				fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG", ".png"));
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG", ".jpg"));
				fileChooser.setSelectedFile(new File("image"));
				int returnVal = fileChooser.showSaveDialog(mainClass);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					System.out.println(fileChooser.getFileFilter().getDescription());
					System.out.println(Image_Utils.getExtension(file));
					String path = "";
					String format = "";
					try {
						if (Image_Utils.getExtension(file) != null) {
							path = file.getCanonicalPath();
							int index = path.lastIndexOf('.');
							path = path.substring(0, index);
						} else {
							path = file.getCanonicalPath();
						}

						if (fileChooser.getFileFilter().getDescription().equals("PNG")) {
							format = "png";
						} else {
							format = "jpg";
						}
						
						System.out.println(path + "---" + format);
						imagePanel.saveImage(path,format);
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else {
				}

			}
		});

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void makeObjects() {
		
		
		invert = new JButton("Invert");
		sepia = new JButton("Brownish");
		solarize = new JButton("Reverse");
		posterize = new JButton("Posterize");
		reset = new JButton("Reset");
		apply = new JButton("Apply");

		open = new JButton("Select");
		save = new JButton("Save");
		close = new JButton("Close");

	}

	private void doTheLayout() {
		setLayout(new BorderLayout());
		
		JPanel text = new JPanel();
		
		
		text.setBackground(Color.BLACK);
		add(text, BorderLayout.NORTH);
		
		JPanel topPanel_1 = new JPanel();
		topPanel_1.setBackground(new Color(100,149,237));
		topPanel_1.setLayout(new FlowLayout(FlowLayout.CENTER,0,120));
		
		JPanel topPanel = new JPanel();
		JPanel topPanel_2 = new JPanel();
		
		topPanel.setBackground(new Color(100,149,237));
		topPanel_2.setBackground(new Color(100,149,237));

		topPanel.setLayout(new GridLayout(0, 2, 20, 15));
		topPanel_2.setLayout(new GridLayout(0,2));
		
		topPanel.add(invert);
		topPanel.add(sepia);
		topPanel.add(solarize);
		topPanel.add(posterize);
		
		topPanel_2.add(apply);
		topPanel_2.add(reset);
		
		
		topPanel_1.add(topPanel);
		topPanel_1.add(topPanel_2);
		
		add(topPanel_1, BorderLayout.CENTER);

	
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.add(open);
		bottomPanel.add(save);
		bottomPanel.add(close);
		
		add(bottomPanel, BorderLayout.NORTH);
		
	}


	@Override
	public Dimension getPreferredSize() {
		return new Dimension(300, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
