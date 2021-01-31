package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.Image;

public class SwingImageDisplay extends JPanel implements ImageDisplay{

    private Image currentImage;

    @Override
    public Image getCurrentImage() {
        return currentImage;
    }
    
    @Override
    public void display(Image image) {
        this.currentImage = image;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (currentImage == null) return;
        g.drawImage(imageOf(currentImage), 0, 0, currentImage.size()[0], currentImage.size()[1], Color.DARK_GRAY, null);
    }

    private BufferedImage imageOf(Image image) {
        try{
            return ImageIO.read(image.stream());
        } catch (IOException e) {
            return null;
        }
    }
}
