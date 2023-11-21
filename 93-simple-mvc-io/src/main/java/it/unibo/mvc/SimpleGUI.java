package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String FRAME = "A simple MVC GUI";
    private static final int PROPORTION = 5;
    private static final String SAVE = "Save";

    private final JFrame frame = new JFrame(FRAME);

    public SimpleGUI(final Controller controller) {
        // Frame
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Main Panel
        final JPanel panel = new JPanel(new BorderLayout());
        this.frame.getContentPane().add(panel);
        // Text Area
        final JTextArea textArea = new JTextArea();
        panel.add(textArea, BorderLayout.CENTER);
        // Save button
        final JButton saveButton = new JButton(SAVE);
        panel.add(saveButton, BorderLayout.SOUTH);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    controller.save(textArea.getText());
                } catch (final IOException e) {
                    JOptionPane.showMessageDialog(frame, e, "There was an error in writing to text",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void display() {
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        final SimpleGUI view = new SimpleGUI(new Controller());
        view.display();
    }
}
