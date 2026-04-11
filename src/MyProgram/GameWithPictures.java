package MyProgram;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameWithPictures extends JFrame {
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private int playerWins = 0;
    private int computerWins = 0;
    private int draws = 0;

    public GameWithPictures() {
        setTitle("Камень-Ножницы-Бумага");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        resultLabel = new JLabel("Сделай свой выбор!", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        scoreLabel = new JLabel("Игрок: 0 | Компьютер: 0 | Ничьи: 0", SwingConstants.CENTER);

        JButton rockButton = new JButton("<html><span style='font-size:20px'>🗿 Камень</span></html>");
        JButton scissorsButton = new JButton("<html><span style='font-size:20px'>✂️ Ножницы</span></html>");
        JButton paperButton = new JButton("<html><span style='font-size:20px'>📄 Бумага</span></html>");

        rockButton.addActionListener(e -> playGame(0));
        scissorsButton.addActionListener(e -> playGame(1));
        paperButton.addActionListener(e -> playGame(2));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(rockButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(paperButton);

        setLayout(new BorderLayout(10, 10));
        add(resultLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scoreLabel, BorderLayout.SOUTH);

        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void playGame(int playerChoice) {
        String[] choices = {"Камень", "Ножницы", "Бумага"};
        Random random = new Random();
        int computerChoice = random.nextInt(3);

        String result = "Ты: " + choices[playerChoice] + " vs Компьютер: " + choices[computerChoice] + " - ";

        if (playerChoice == computerChoice) {
            result += "Ничья! 🤝";
            draws++;
        } else if ((playerChoice == 0 && computerChoice == 1) ||
                (playerChoice == 1 && computerChoice == 2) ||
                (playerChoice == 2 && computerChoice == 0)) {
            result += "Ты победил! 🎉";
            playerWins++;
        } else {
            result += "Компьютер победил! 💻";
            computerWins++;
        }

        resultLabel.setText(result);
        scoreLabel.setText("Игрок: " + playerWins + " | Компьютер: " + computerWins + " | Ничьи: " + draws);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameWithPictures().setVisible(true);
        });
    }
}
