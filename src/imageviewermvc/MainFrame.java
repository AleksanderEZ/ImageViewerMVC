package imageviewermvc;

import control.PrevCommand;
import control.Command;
import control.NextCommand;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Image;
import view.ImageDisplay;
import view.SwingImageDisplay;

public class MainFrame extends JFrame{
    
    private final Map<String, Command> commands;

    private ImageDisplay imageDisplay;
    private Image image;
    
    public MainFrame() {
        commands = new HashMap<>();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ImageViewer");
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        getContentPane().add(toolbar(), BorderLayout.SOUTH);
        getContentPane().add(imageDisplay());
        commands.put("Next", new NextCommand(imageDisplay));
        commands.put("Prev", new PrevCommand(imageDisplay));
        this.setVisible(true);
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }

    private JPanel toolbar(){
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button("Prev"));
        panel.add(button("Next"));
        return panel;
    }

    
    private JPanel imageDisplay() {
        SwingImageDisplay imageDisplay = new SwingImageDisplay();
        this.imageDisplay = imageDisplay;
        return imageDisplay;
    }
    
    private JButton button(String command) {
        final JButton button = new JButton(command);
        button.addActionListener((ActionEvent e) -> {
            commands.get(command).execute();
        });
        return button;
    }
}
