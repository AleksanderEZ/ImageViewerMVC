package imageviewermvc;

import java.io.File;
import model.Image;
import view.FileImageLoader;

public class Main {
    public static void main(String[] args) {
        FileImageLoader loader = new FileImageLoader(new File("images"));
        Image image = loader.load();
        MainFrame mainFrame = new MainFrame();
        mainFrame.getImageDisplay().display(image);
    }
}
