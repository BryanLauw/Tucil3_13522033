import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        // Making dictionary
        Dictionary dict = new Dictionary();

        // Main frame
        JFrame f = new JFrame("Word Ladder Solver");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(430, 470);
        f.setLayout(new FlowLayout());

        // Title
        JPanel title = new JPanel();
        JLabel header = new JLabel("WORD LADDER SOLVER");
        title.add(header);
        title.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        // Input field (statring word and destination word)
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JLabel start = new JLabel("Starting word: ");
        JTextField input1 = new JTextField(20);
        panel1.add(start);
        panel1.add(input1);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JLabel end = new JLabel("Destination word: \n");
        JTextField input2 = new JTextField(18);
        panel2.add(end);
        panel2.add(input2);

        // Button for running the program
        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("FIND");
        buttonPanel.add(submitButton);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

        // Radio buttons
        JRadioButton radioButton1 = new JRadioButton("UCS");
        JRadioButton radioButton2 = new JRadioButton("G-BFS");
        JRadioButton radioButton3 = new JRadioButton("A*");

        // Initially set "UCS" radio button as selected
        radioButton1.setSelected(true);

        // Button group
        JLabel tekspilihan = new JLabel("CHOOSE ALGORITHM");
        JPanel teksSementara = new JPanel();
        teksSementara.add(tekspilihan);
        teksSementara.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        JPanel algoritma = new JPanel();
        algoritma.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        algoritma.add(radioButton1);
        algoritma.add(radioButton2);
        algoritma.add(radioButton3);
        
        // Output field
        JPanel resPanel = new JPanel();
        JLabel res = new JLabel();
        resPanel.add(res);
        resPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        JPanel nodePanel = new JPanel();
        JLabel nodeLabel = new JLabel();
        nodePanel.add(nodeLabel);
        nodePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        JPanel timePanel = new JPanel();
        JLabel timeLabel = new JLabel();
        timePanel.add(timeLabel);
        timePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        JPanel resultPanel = new JPanel();
        JLabel resultLabel = new JLabel();
        resultPanel.add(resultLabel);
        resultPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        JPanel memoryPanel = new JPanel();
        JLabel memoryLabel = new JLabel();
        memoryPanel.add(memoryLabel);
        memoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

        // Main content
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(title);
        contentPane.add(panel1);
        contentPane.add(panel2);
        contentPane.add(Box.createVerticalStrut(10)); // Add some vertical space
        contentPane.add(teksSementara);
        contentPane.add(algoritma);
        contentPane.add(Box.createVerticalStrut(10)); // Add some vertical space
        contentPane.add(buttonPanel);
        contentPane.add(Box.createVerticalStrut(10)); // Add some vertical space
        contentPane.add(resPanel);
        contentPane.add(resultPanel);
        contentPane.add(nodePanel);
        contentPane.add(timePanel);
        contentPane.add(memoryPanel);

        // Adding scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Runtime r = Runtime.getRuntime();
                Process pr;

                // Get the values of input1 and input2
                String startingWord = input1.getText().toUpperCase();
                String destinationWord = input2.getText().toUpperCase();

                // Check whether the words valid or not
                if (startingWord.length() != destinationWord.length()) {
                    JOptionPane.showMessageDialog(f, "Input words must have the same length.");
                    return;
                }
                if (!dict.isWordValid(startingWord) || !dict.isWordValid(destinationWord)) {
                    JOptionPane.showMessageDialog(f, "Input words must be valid.");
                    return;
                }

                // Starting process
                long startTime = System.currentTimeMillis();
                r.gc();
                long beforeMem = r.totalMemory() - r.freeMemory();
                pr = new Process(startingWord, destinationWord, false, false, dict);
                // Get the selected algorithm
                if (radioButton1.isSelected()) {
                    pr = new Process(startingWord, destinationWord, true, false, dict);
                } else if (radioButton2.isSelected()) {
                    pr = new Process(startingWord, destinationWord, false, true, dict);
                } else if (radioButton3.isSelected()) {
                    pr = new Process(startingWord, destinationWord, true, true, dict);
                }

                // Making child nodes untill found a path or queue is empty
                while (pr.getPath().isEmptyPath() && !(pr.getQueue().isEmptyQueue())) {
                    pr.generateChild();
                }
                
                // Continue if the path found is not minimum
                while (!pr.isMostMinimum() && !(pr.getQueue().isEmptyQueue())) {
                    pr.generateChild();
                }
                long afterMem = r.totalMemory() - r.freeMemory();
                long stopTime = System.currentTimeMillis();

                // Displaying Result
                res.setText("RESULT");
                if (pr.getPath().isEmptyPath()) {
                    resultLabel.setText("No path found");
                } else {
                    String sb = "";
                    for (int i = 0; i < pr.getPath().getResult().size(); i++) {
                        sb = sb + ((i+1) + ". " + pr.getPath().getResult().get(i) + "<br>");
                    }
                    resultLabel.setText("<html>" + sb + "</html>");
                }
                nodeLabel.setText("Visited node(s): " + pr.getQueue().getGeneratedNode());
                timeLabel.setText("Execution time: " + (stopTime-startTime) + " ms");
                memoryLabel.setText("Memory used: " + ((afterMem - beforeMem)/1024) + " kilobytes");
            }
        });

        // Adding scroller
        scrollPane.setViewportView(contentPane);
        f.add(scrollPane);
        
        // Center the frame on the screen
        f.setLocationRelativeTo(null);

        f.setVisible(true);
    }
}
