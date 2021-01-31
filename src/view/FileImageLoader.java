package view;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.Image;

public class FileImageLoader implements ImageLoader{

    private final File[] files;

    public FileImageLoader(File folder) {
        this.files = folder.listFiles(imageTypes());
    }
    
    private FileFilter imageTypes(){
        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().endsWith(".jpg")) return true;
                return false;
            }
        };
    }
    
    private Image imageAt(int i) {
        return new Image(){
            @Override
            public String name() {
                return files[i].getName();
            }
            
            @Override
            public InputStream stream() {
                try {
                    return new BufferedInputStream(new FileInputStream(files[i]));
                } catch (FileNotFoundException e){
                    return null;
                }
            }
            
            @Override
            public int[] size(){
                int [] widthHeight = new int[2];
                BufferedImage bimg = null;
                try {
                    bimg = ImageIO.read(files[i]);
                } catch (IOException ex) {
                    Logger.getLogger(FileImageLoader.class.getName()).log(Level.SEVERE, null, ex);
                }
                widthHeight[0] = bimg.getWidth();
                widthHeight[1] = bimg.getHeight();
                return widthHeight;
            }
            
            @Override
            public Image next() {
                return i == files.length - 1 ? imageAt(0) : imageAt(i+1);
            }

            @Override
            public Image prev() {
                return i == 0 ? imageAt(files.length - 1): imageAt(i-1);
            }
        };
    }    
    
    @Override
    public Image load() {
        return imageAt(0);
    }
}
