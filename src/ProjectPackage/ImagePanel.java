package ProjectPackage;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	int imagePanelWidth = 600;
	int imagePanelHeight = 500;
	
	public BufferedImage image;
	private BufferedImage tempImage;
	private BufferedImage originalImage;
	
	Dimension imageSize;
	Dimension frameSize;
	Dimension newImageSize;
	
	public ImagePanel() {
		
	}
	
	//read-in new image
	public void changeImage(File f){
		try {
            image = ImageIO.read(f);
            imageSize = new Dimension(image.getWidth(),image.getHeight());
            frameSize = new Dimension(imagePanelWidth, imagePanelHeight);
            newImageSize = getScaledDimension(imageSize, frameSize);
            tempImage = image;
            originalImage = image;
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void saveImage(String destinationPath, String format ){
		
		if(image == null){
			JOptionPane.showMessageDialog(this, "First choose a proper image");
			return;
		}
		try{
			System.out.println(destinationPath+"."+format);
			ImageIO.write(image, format, new File(destinationPath+"."+format));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//resetting the dimension of the image
	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
		

	    int originalW = imgSize.width;
	    int originalH = imgSize.height;
	    int boundW = boundary.width-30;
	    int boundH = boundary.height-30;
	    int newW = originalW;
	    int newH = originalH;

	    // first check if we need to scale width
	    if (originalW > boundW) {
	        //scale width to fit
	        newW = boundW;
	        //scale height to maintain aspect ratio
	        newH = (newW * originalH) / originalW;
	    }

	    // then check if we need to scale even with the new height
	    if (newH > boundH) {
	        //scale height to fit instead
	        newH = boundH;
	        //scale width to maintain aspect ratio
	        newW = (newH * originalW) / originalH;
	    }

	    return new Dimension(newW, newH);
	}
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(image != null){
			g.drawImage(image, getWidth()/2 - newImageSize.width/2, getHeight()/2 - newImageSize.height/2, newImageSize.width, newImageSize.height, this);
			//g.drawImage(image, 50, 50, image.getWidth(), image.getHeight(), this);
		}else{
			Font h = new Font("Helvetica", Font.PLAIN, 20);
			g.setFont(h);
			g.drawString("Pick Image from Gallery.", getWidth()/2, getHeight()/2);
			
		
		}
			
		
	}
	
	@Override
	// Specify preferred size
	public Dimension getPreferredSize() {
		return new Dimension(imagePanelWidth, imagePanelHeight);
	}
	
	public void applyEffect(Effect effect){
		
		if(image == null){
			JOptionPane.showMessageDialog(this, "First choose a proper image");
			return;
		}
		image = tempImage; //resets the image
		switch(effect){
		
		case SEPIA:
			image = ImageEffects.sepia(image);
			break;
		case INVERT:
			image = ImageEffects.invert(image);
			break;
		case SOLARIZE:
			image = ImageEffects.solarize(image);
			break;
		case POSTERIZE:
			image = ImageEffects.posterize(image);
			break;
		}
		repaint();
	}
	
	//discard the changes and retain the original image
	public void resetImage(){
		image = originalImage; //resets the image
		tempImage = originalImage;
		repaint();
	}
	
	//make the changes permanent
	public void applyPermanentEffect(){
		tempImage = image;
		repaint();
	}

}
