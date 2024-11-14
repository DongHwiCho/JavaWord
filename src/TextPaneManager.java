import javax.swing.*;

public class TextPaneManager {
    private final JFrame frame;
    private JTextPane textPane;

    public TextPaneManager(JFrame frame) {
        this.frame = frame;
    }

    public void createTextPane() {
        textPane = new JTextPane();
        addTextPane();
    }

    public void addTextPane(){
        frame.add(textPane);
    }

    public JTextPane getTextPane() {
        return textPane;
    }
}
