import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class StackVisualization extends JFrame {
    private Stack<Integer> stackData = new Stack<>();
    private StackPanel stackPanel;

    public StackVisualization() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stack Visualization");

        JButton pushButton = new JButton("Push");
        JButton popButton = new JButton("Pop");
        JButton topButton = new JButton("Top");

        pushButton.addActionListener(e -> pushButtonActionPerformed());
        popButton.addActionListener(e -> popButtonActionPerformed());
        topButton.addActionListener(e -> topButtonActionPerformed());

        stackPanel = new StackPanel();

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(pushButton);
        buttonPanel.add(popButton);
        buttonPanel.add(topButton);

        mainPanel.add(stackPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

        // Set a preferred size for the window
        setSize(400, 500);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void pushButtonActionPerformed() {
        String input = JOptionPane.showInputDialog("Enter element to push");
        if (input != null && !input.isEmpty()) {
            int element = Integer.parseInt(input);
            stackData.push(element);
            updateStackPanel();
        }
    }

    private void popButtonActionPerformed() {
        if (!stackData.isEmpty()) {
            stackData.pop();
            updateStackPanel();
        } else {
            JOptionPane.showMessageDialog(this, "Stack is empty");
        }
    }

    private void topButtonActionPerformed() {
        if (!stackData.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Top element: " + stackData.peek());
        } else {
            JOptionPane.showMessageDialog(this, "Stack is empty");
        }
    }

    private void updateStackPanel() {
        stackPanel.repaint();
    }

    private class StackPanel extends JPanel {
        private static final int RECT_WIDTH = 80;
        private static final int RECT_HEIGHT = 30;
        private static final int HORIZONTAL_GAP = 20;
        private static final int VERTICAL_GAP = 40;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            int startX = getWidth() / 2 - RECT_WIDTH / 2;
            int startY = 50;

            for (int i = 0; i < stackData.size(); i++) {
                int x = startX;
                int y = startY + i * VERTICAL_GAP;

                g2d.setColor(Color.WHITE);
                g2d.fillRect(x, y, RECT_WIDTH, RECT_HEIGHT);

                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, RECT_WIDTH, RECT_HEIGHT);

                g2d.drawString(String.valueOf(stackData.get(i)), x + RECT_WIDTH / 2 - 5, y + RECT_HEIGHT / 2 + 5);
            }

            g2d.dispose();
        }

        @Override
        public Dimension getPreferredSize() {
            int width = RECT_WIDTH + HORIZONTAL_GAP;
            int height = (RECT_HEIGHT + VERTICAL_GAP) * stackData.size() + 50;
            return new Dimension(width, height);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new StackVisualization().setVisible(true));
    }
}
