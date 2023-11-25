package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final String FRAME = "A simple MVC GUI with file chooser";
    private static final int PROPORTION = 5;
    private static final String SAVE = "Save";
    private static final String BROWSE = "Browse";

    private final JFrame frame = new JFrame(FRAME);

    public SimpleGUIWithFileChooser(final Controller controller) {
        // Frame
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Main Panel
        final JPanel mainPanel = new JPanel(new BorderLayout());
        this.frame.getContentPane().add(mainPanel);
        // Text Area
        final JTextArea textArea = new JTextArea();
        textArea.setText(controller.load());
        mainPanel.add(textArea, BorderLayout.CENTER);
        // Save button
        final JButton saveButton = new JButton(SAVE);
        mainPanel.add(saveButton, BorderLayout.SOUTH);
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
        // North Panel
        final JPanel northPanel = new JPanel(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);
        // Text Field
        final JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setText(controller.getCurrentFilePath());
        northPanel.add(textField, BorderLayout.CENTER);
        // Browse Button
        final JButton browseButton = new JButton(BROWSE);
        northPanel.add(browseButton, BorderLayout.LINE_END);
        browseButton.addActionListener(new ActionListener() {
            // Browsing
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File(System.getProperty("user.home")));
                final var selection = fc.showSaveDialog(browseButton);
                if (selection == JFileChooser.APPROVE_OPTION) {
                    final File selectedFile = fc.getSelectedFile();
                    textField.setText(selectedFile.getAbsolutePath());
                    controller.setCurrentFile(selectedFile);
                    textArea.setText(controller.load());
                } else if (selection == JFileChooser.CANCEL_OPTION) {

                } else {
                    JOptionPane.showMessageDialog(frame, e, "An error has occurred in file selection",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

    public void display() {
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        final SimpleGUIWithFileChooser view = new SimpleGUIWithFileChooser(new Controller());
        view.display();
    }
}
