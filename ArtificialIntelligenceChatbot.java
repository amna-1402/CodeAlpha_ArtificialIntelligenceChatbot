import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ArtificialIntelligenceChatbot extends JFrame {
    private JPanel chatPanel;
    private JScrollPane scrollPane;
    private JTextField inputField;
    private JButton sendButton;
    private HashMap<String, String> responses;

    public ArtificialIntelligenceChatbot() {
        setTitle("Java AI ChatBot");
        setSize(550, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(33, 150, 243));
        topPanel.setPreferredSize(new Dimension(550, 50));
        JLabel titleLabel = new JLabel("💬 Java AI ChatBot");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(chatPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        sendButton = new JButton("Send");
        sendButton.setBackground(new Color(76, 175, 80));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setFocusPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        setupResponses();
        addListeners();

        addMessage("ChatBot: Hello! I am your Java Assistant. Ask me anything about Java.", false);

        setVisible(true);
    }

    private void setupResponses() {
        responses = new HashMap<>();
        responses.put("what is java", "Java is a high-level, object-oriented programming language.");
        responses.put("oops", "OOP stands for Object-Oriented Programming with key concepts: Inheritance, Encapsulation, Abstraction, Polymorphism.");
        responses.put("inheritance", "Inheritance allows a class to acquire properties and behavior from another class using 'extends'.");
        responses.put("encapsulation", "Encapsulation means wrapping data and methods into a single unit called class and restricting access using private/public.");
        responses.put("abstraction", "Abstraction hides complexity by showing only essential features. Achieved using abstract classes and interfaces.");
        responses.put("polymorphism", "Polymorphism means one method behaves differently based on object context. Types: Compile-time and Run-time.");
        responses.put("interface", "Interface is a blueprint of a class. It contains abstract methods and is used to achieve full abstraction.");
        responses.put("constructor", "A constructor is a special method that runs when an object is created. It has the same name as the class.");
        responses.put("string", "Strings in Java are objects used to store sequences of characters. They are immutable.");
        responses.put("bye", "Goodbye! Have a nice day.");
        responses.put("exit", "Goodbye! Have a nice day.");
    }

    private void addListeners() {
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText().toLowerCase().trim();
                if (userInput.isEmpty()) return;
                addMessage("You: " + userInput, true);
                inputField.setText("");
                boolean found = false;

                for (String key : responses.keySet()) {
                    if (userInput.contains(key)) {
                        addMessage("ChatBot: " + responses.get(key), false);
                        found = true;
                        if (key.equals("bye") || key.equals("exit")) {
                            System.exit(0);
                        }
                        break;
                    }
                }

                if (!found) {
                    addMessage("ChatBot: Sorry, I don't understand. Try asking about Java topics like 'OOP', 'interface', or 'string'.", false);
                }
                SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum()));
            }
        });

        inputField.addActionListener(e -> sendButton.doClick());
    }

    private void addMessage(String message, boolean isUser) {
        JPanel messagePanel = new JPanel(new FlowLayout(isUser ? FlowLayout.RIGHT : FlowLayout.LEFT));
        messagePanel.setBackground(Color.WHITE);

        JLabel messageLabel = new JLabel("<html><div style='padding:8px; max-width:300px;'>" + message + "</div></html>");
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageLabel.setOpaque(true);

        if (isUser) {
            messageLabel.setBackground(new Color(33, 150, 243)); 
            messageLabel.setForeground(Color.WHITE);             
        } else {
            messageLabel.setBackground(new Color(230, 230, 230)); 
            messageLabel.setForeground(Color.BLACK);
        }

        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messagePanel.add(messageLabel);
        chatPanel.add(messagePanel);
        chatPanel.revalidate();
        chatPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ArtificialIntelligenceChatbot());
    }
}
