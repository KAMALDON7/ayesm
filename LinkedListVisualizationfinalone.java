import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StackVisualization extends JFrame {
    private DefaultListModel<Integer> stackModel = new DefaultListModel<>();
    private JList<Integer> stackList;

    public StackVisualization() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stack Visualization");

        stackList = new JList<>(stackModel);
        JButton pushButton = new JButton("Push");
        JButton popButton = new JButton("Pop");

        pushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pushButtonActionPerformed(e);
            }
        });

        popButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popButtonActionPerformed(e);
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a panel for stack controls
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlPanel.add(new JLabel("Stack: "));
        controlPanel.add(stackList);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(pushButton);
        buttonPanel.add(popButton);

        mainPanel.add(controlPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

        // Set a preferred size for the window
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void pushButtonActionPerformed(ActionEvent evt) {
        String input = JOptionPane.showInputDialog("Enter element to push");
        if (input != null && !input.isEmpty()) {
            int element = Integer.parseInt(input);
            stackModel.addElement(element);
        }
    }

    private void popButtonActionPerformed(ActionEvent evt) {
        int lastIndex = stackModel.getSize() - 1;
        if (lastIndex >= 0) {
            stackModel.remove(lastIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Stack is empty");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StackVisualization().setVisible(true);
            }
        });
    }
}
