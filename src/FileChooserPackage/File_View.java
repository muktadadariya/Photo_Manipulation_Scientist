package FileChooserPackage;


import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class File_View extends FileView {
    ImageIcon jpgIcon = Image_Utils.createImageIcon("jpgIcon.gif");
    ImageIcon gifIcon = Image_Utils.createImageIcon("gifIcon.gif");
    ImageIcon tiffIcon = Image_Utils.createImageIcon("tiffIcon.gif");
    ImageIcon pngIcon = Image_Utils.createImageIcon("pngIcon.png");

    public String getName(File f) {
        return null; 
    }

    public String getDescription(File f) {
        return null;
    }

    public Boolean isTraversable(File f) {
        return null; 
    }

    public String getTypeDescription(File f) {
        String extn = Image_Utils.getExtension(f);
        String type = null;

        if (extn != null) {
            if (extn.equals(Image_Utils.jpeg) ||
                extn.equals(Image_Utils.jpg)) {
                type = "JPEG Image";
            } else if (extn.equals(Image_Utils.gif)){
                type = "GIF Image";
            } else if (extn.equals(Image_Utils.tiff) ||
                       extn.equals(Image_Utils.tif)) {
                type = "TIFF Image";
            } else if (extn.equals(Image_Utils.png)){
                type = "PNG Image";
            }
        }
        return type;
    }

    public Icon getIcon(File f) {
        String extn = Image_Utils.getExtension(f);
        Icon icon = null;

        if (extn != null) {
            if (extn.equals(Image_Utils.jpeg) ||
                extn.equals(Image_Utils.jpg)) {
                icon = jpgIcon;
            } else if (extn.equals(Image_Utils.gif)) {
                icon = gifIcon;
            } else if (extn.equals(Image_Utils.tiff) ||
                       extn.equals(Image_Utils.tif)) {
                icon = tiffIcon;
            } else if (extn.equals(Image_Utils.png)) {
                icon = pngIcon;
            }
        }
        return icon;
    }
}
