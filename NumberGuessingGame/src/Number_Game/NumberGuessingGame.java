package Number_Game;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NumberGuessingGame {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100;

    private JFrame frame;
    private JPanel panel;
    private JLabel attemptsLabel;
    private JTextField attemptsField;
    private JButton startButton;

    public static void main(String[] args) {
        new NumberGuessingGame().createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Number Guessing Game");
        panel = new JPanel();
        attemptsLabel = new JLabel("Enter the number of attempts:");
        attemptsField = new JTextField(5);
        startButton = new JButton("Start Game");

        panel.add(attemptsLabel);
        panel.add(attemptsField);
        panel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 150));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void startGame() {
        int attempts = Integer.parseInt(attemptsField.getText());
        int score = 0;

        boolean playAgain = true;
        while (playAgain) {
            int targetNumber = new Random().nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
            int remainingAttempts = attempts;
            boolean guessedCorrectly = false;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I have generated a number between " + MIN_RANGE + " and " + MAX_RANGE + ".");
            System.out.println("You have " + remainingAttempts + " attempts to guess the number.");

            while (remainingAttempts > 0) {
                String input = JOptionPane.showInputDialog("Enter your guess (between " + MIN_RANGE + " and " + MAX_RANGE + ") or type 'hint' for a hint:");
                if (input == null) { // The user clicked "Cancel" or closed the dialog
                    System.exit(0);
                }

                if (input.equalsIgnoreCase("hint")) {
                    JOptionPane.showMessageDialog(null, "Hint: The number is " + targetNumber % 10);
                    continue;
                }

                int guess = Integer.parseInt(input);
                remainingAttempts--;

                if (guess == targetNumber) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                    break;
                } else if (guess < targetNumber) {
                    JOptionPane.showMessageDialog(null, "Too low! You have " + remainingAttempts + " attempts left.");
                } else {
                    JOptionPane.showMessageDialog(null, "Too high! You have " + remainingAttempts + " attempts left.");
                }
            }

            if (!guessedCorrectly) {
                JOptionPane.showMessageDialog(null, "Sorry, you have used all your attempts.\nThe correct number was: " + targetNumber);
            }

            score += guessedCorrectly ? 1 : 0;
            int option = JOptionPane.showConfirmDialog(null, "Your current score: " + score + "\nDo you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
            playAgain = (option == JOptionPane.YES_OPTION);
        }

        JOptionPane.showMessageDialog(null, "Thank you for playing the Number Guessing Game!");
        System.exit(0);
    }
}

