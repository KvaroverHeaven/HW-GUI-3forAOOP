import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TextFileTest {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
                    | IllegalAccessException ex) {
                ex.printStackTrace();
            }
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        var textFileFrame = new TextFileFrame();
        textFileFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        textFileFrame.setSize(1280, 720);
        textFileFrame.setLocationByPlatform(true);
        textFileFrame.setVisible(true);
    }
}
