package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String FRAME = "A simple MVC GUI";
    private static final int PROPORTION = 5;
    private static final String PRINT = "Print";
    private static final String HISTORY = "Show history";

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
        final JPanel mainPanel = new JPanel(new BorderLayout());
        this.frame.getContentPane().add(mainPanel);
        // Text Field
        final JTextField textField = new JTextField();
        mainPanel.add(textField, BorderLayout.NORTH);
        // Text Arena
        final JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        mainPanel.add(textArea, BorderLayout.CENTER);
        // South panel
        final JPanel southPanel = new JPanel();
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        // Print button
        final JButton printButton = new JButton(PRINT);
        southPanel.add(printButton);
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setText(textField.getText());
                controller.printText();
            }   
        });
        // History button
        final JButton historyButton = new JButton(HISTORY);
        southPanel.add(historyButton);
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                textArea.setText(controller.getTextHistory().toString());
            }   
        });
    }

    public void display() {
        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        final SimpleGUI view = new SimpleGUI(new SimpleController());
        view.display();
    }
}
