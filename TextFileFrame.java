import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class TextFileFrame extends JFrame {

    private static final long serialVersionUID = 4864279339726040095L;
    private File file;
    private int returnValue;

    public TextFileFrame() {
        super("TextEditor");

        var tabPane = new JTabbedPane();
        var fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        var openItem = new JMenuItem("Open");
        openItem.setMnemonic('O');
        fileMenu.add(openItem);
        openItem.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            returnValue = jFileChooser.showOpenDialog(TextFileFrame.this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                file = jFileChooser.getSelectedFile();
                var newPanel = new TextEditorPanel(file);
                tabPane.add(file.getName(), newPanel);
            } else {
                System.err.println("Failed to open text file.");
            }
        });

        var exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('x');
        fileMenu.add(exitItem);
        exitItem.addActionListener(e -> System.exit(0));

        var jMenuBar = new JMenuBar();
        setJMenuBar(jMenuBar);
        jMenuBar.add(fileMenu);
        add(tabPane, BorderLayout.CENTER);
    }

}

class TextEditorPanel extends JPanel {

    private static final long serialVersionUID = -3940419912928700554L;

    public TextEditorPanel(File file) {
        try {
            var jEditorPane = new JEditorPane();
            var editorScrollPane = new JScrollPane(jEditorPane);
            editorScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
            jEditorPane.setText(Files.readString(Paths.get(file.toURI()), StandardCharsets.UTF_8));
            jEditorPane.setEditable(true);
            setLayout(new BorderLayout());
            add(editorScrollPane, BorderLayout.CENTER);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}