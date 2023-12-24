import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.Queue;

class QueueVisualizer extends JFrame {
    private Queue<Integer> queue = new LinkedList<>();

    private static final int NODE_WIDTH = 60;
    private static final int NODE_HEIGHT = 40;
    private static final int NODE_SPACING = 20;

    private JTextField dataField;

    public QueueVisualizer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawQueue(g);
            }
        };

        dataField = new JTextField(10);
        JButton enqueueButton = new JButton("Enqueue");
        JButton dequeueButton = new JButton("Dequeue");

        enqueueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enqueue();
                panel.repaint();
            }
        });

        dequeueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dequeue();
                panel.repaint();
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Data: "));
        inputPanel.add(dataField);
        inputPanel.add(enqueueButton);
        inputPanel.add(dequeueButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(enqueueButton);
        buttonPanel.add(dequeueButton);

        add(inputPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);

        // Center the frame on the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
    }

    private void enqueue() {
        try {
            int newData = Integer.parseInt(dataField.getText());
            queue.add(newData);
            dataField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dequeue() {
        if (!queue.isEmpty()) {
            queue.poll();
        } else {
            JOptionPane.showMessageDialog(this, "Queue is empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void drawQueue(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int xOffset = 50;
        int yOffset = getHeight() / 2 - NODE_HEIGHT / 2;

        for (int i = 0; i < queue.size(); i++) {
            RoundRectangle2D node = new RoundRectangle2D.Double(
                    xOffset + i * (NODE_WIDTH + NODE_SPACING),
                    yOffset, NODE_WIDTH, NODE_HEIGHT, 10, 10);

            g2d.setColor(Color.BLUE);
            g2d.fill(node);
            g2d.setColor(Color.BLACK);
            g2d.draw(node);

            g2d.drawString(String.valueOf(queue.toArray()[i]),
                    xOffset + i * (NODE_WIDTH + NODE_SPACING) + NODE_WIDTH / 3,
                    yOffset + NODE_HEIGHT / 2 + 5);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QueueVisualizer::new);
    }
}
