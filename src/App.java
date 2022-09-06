import javax.swing.*;
import java.awt.*;

public class App extends JFrame{
    
    public App() {
        initUI();
    }

    private void initUI() {
        int width = 605;
        int height = 635;
        add(new Board(width, height));

        setTitle("SNAKEUSSS");
        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            App ex = new App();
            ex.setVisible(true);
        });
    }
}
