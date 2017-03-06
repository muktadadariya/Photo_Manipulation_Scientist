package FileChooserPackage;


import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class Filter extends FileFilter {

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extn = Image_Utils.getExtension(f);
        if (extn != null) {
            if (extn.equals(Image_Utils.tiff) ||
                extn.equals(Image_Utils.tif) ||
                extn.equals(Image_Utils.gif) ||
                extn.equals(Image_Utils.jpeg) ||
                extn.equals(Image_Utils.jpg) ||
                extn.equals(Image_Utils.png)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public String getDescription() {
        return "Just Images";
    }
}
