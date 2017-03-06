package FileChooserPackage;


import javax.swing.*;
import java.beans.*;
import java.awt.*;
import java.io.File;

public class Preview extends JComponent
                          implements PropertyChangeListener {
    ImageIcon thumb = null;
    File file = null;

    public Preview(JFileChooser fc) {
        setPreferredSize(new Dimension(100, 50));
        fc.addPropertyChangeListener(this);
    }

    public void loadImage() {
        if (file == null) {
            thumb = null;
            return;
        }

    
        ImageIcon tmpIcon = new ImageIcon(file.getPath());
        if (tmpIcon != null) {
            if (tmpIcon.getIconWidth() > 90) {
                thumb = new ImageIcon(tmpIcon.getImage().
                                          getScaledInstance(90, -1,
                                                      Image.SCALE_DEFAULT));
            } else { 
                thumb = tmpIcon;
            }
        }
    }

    public void propertyChange(PropertyChangeEvent e) {
        boolean update = false;
        String prop = e.getPropertyName();

        if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)) {
            file = null;
            update = true;

        } else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
            file = (File) e.getNewValue();
            update = true;
        }

        if (update) {
            thumb = null;
            if (isShowing()) {
                loadImage();
                repaint();
            }
        }
    }

    protected void paintComponent(Graphics g) {
        if (thumb == null) {
            loadImage();
        }
        if (thumb != null) {
            int x = getWidth()/2 - thumb.getIconWidth()/2;
            int y = getHeight()/2 - thumb.getIconHeight()/2;

            if (y < 0) {
                y = 0;
            }

            if (x < 5) {
                x = 5;
            }
            thumb.paintIcon(this, g, x, y);
        }
    }
}
