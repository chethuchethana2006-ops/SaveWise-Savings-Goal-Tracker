import javax.swing.*;
import java.awt.*;

public class SavingsGoalTracker extends JFrame {

    private final JTextField goalField;
    private final JTextField savedField;
    private final JLabel percentageLabel;
    private final JProgressBar progressBar;
    private final JButton updateButton;

    public SavingsGoalTracker() {

        setTitle("Savings Goal Tracker");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Set your savings goal ($):"));
        goalField = new JTextField();
        add(goalField);

        add(new JLabel("Enter your current saved amount ($):"));
        savedField = new JTextField();
        add(savedField);

        add(new JLabel("Progress:"));
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        add(progressBar);

        add(new JLabel("Percentage reached:"));
        percentageLabel = new JLabel("0%");
        add(percentageLabel);

        updateButton = new JButton("Update Progress");
        updateButton.addActionListener(e -> updateProgress());
        add(updateButton);
    }

    private void updateProgress() {
        try {
            double goal = Double.parseDouble(goalField.getText().trim());
            double saved = Double.parseDouble(savedField.getText().trim());

            if (goal <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a goal greater than 0!",
                        "Invalid Goal",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (saved < 0) {
                JOptionPane.showMessageDialog(this,
                        "Saved amount cannot be negative!",
                        "Invalid Amount",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            double percent = (saved / goal) * 100;

            if (percent > 100) {
                percent = 100;
            }

            progressBar.setValue((int) percent);
            percentageLabel.setText(String.format("%.2f%%", percent));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SavingsGoalTracker tracker = new SavingsGoalTracker();
            tracker.setVisible(true);
        });
    }
}