import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UI {
    private JFrame frame;
    private JLabel label;
    private JTextArea textArea;
    private JButton saveButton;

    public UI() {
        frame = new JFrame("Notes App");

        label = new JLabel("Notes App");
        label.setHorizontalAlignment(JLabel.CENTER);

        textArea = new JTextArea(10, 40);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        saveButton = new JButton("Save note");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveNoteToFile();
            }
        });

        Container container = frame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(label);
        container.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        container.add(scrollPane);
        container.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        container.add(saveButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    private void saveNoteToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("notes.txt"));
            writer.write(textArea.getText());
            writer.close();
            JOptionPane.showMessageDialog(frame, "Note saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving note: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
