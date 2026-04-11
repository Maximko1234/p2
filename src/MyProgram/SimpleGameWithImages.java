package MyProgram;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SimpleGameWithImages extends JFrame {
    private JLabel resultLabel;
    private JLabel playerImageLabel;
    private JLabel computerImageLabel;

    private ImageIcon[] images = new ImageIcon[3];
    private String[] choices = {"Камень", "Ножницы", "Бумага"};

    public SimpleGameWithImages() {
        // Загружаем картинки ПРОСТЫМ способом
        loadImagesSimple();

        setTitle("Игра с Картинками");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Надпись результата
        resultLabel = new JLabel("Выбери вариант!", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Метки для картинок
        playerImageLabel = new JLabel("", SwingConstants.CENTER);
        computerImageLabel = new JLabel("", SwingConstants.CENTER);
        playerImageLabel.setPreferredSize(new Dimension(150, 150));
        computerImageLabel.setPreferredSize(new Dimension(150, 150));

        // Панель для картинок
        JPanel imagePanel = new JPanel(new GridLayout(2, 4, 40, 40));
        imagePanel.add(createLabelPanel("ИГРОК", playerImageLabel));
        imagePanel.add(createLabelPanel("КОМПЬЮТЕР", computerImageLabel));

        // Кнопки
        JButton rockBtn = new JButton("Камень");
        JButton scissorsBtn = new JButton("Ножницы");
        JButton paperBtn = new JButton("Бумага");

        rockBtn.setFont(new Font("Arial", Font.BOLD, 30));
        scissorsBtn.setFont(new Font("Arial", Font.BOLD, 30));
        paperBtn.setFont(new Font("Arial", Font.BOLD, 30));

        rockBtn.addActionListener(e -> makeChoice(0));
        scissorsBtn.addActionListener(e -> makeChoice(1));
        paperBtn.addActionListener(e -> makeChoice(2));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(rockBtn);
        buttonPanel.add(scissorsBtn);
        buttonPanel.add(paperBtn);

        // Компоновка
        setLayout(new BorderLayout(10, 10));
        add(resultLabel, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().setBackground(Color.WHITE);
    }

    private void loadImagesSimple() {
        try {
            // ПРОСТАЯ загрузка картинок
            images[0] = new ImageIcon("resources/rock.png");
            images[1] = new ImageIcon("resources/scissors.jpg");
            images[2] = new ImageIcon("resources/paper.jpg");

            // Масштабируем если нужно
            for (int i = 0; i < images.length; i++) {
                if (images[i] != null) {
                    Image img = images[i].getImage();
                    Image scaled = img.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
                    images[i] = new ImageIcon(scaled);
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            // Если картинки не загрузятся - игра всё равно будет работать
        }
    }

    private JPanel createLabelPanel(String title, JLabel imageLabel) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(imageLabel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return panel;
    }

    private void makeChoice(int playerChoice) {
        Random random = new Random();
        int computerChoice = random.nextInt(3);

        // Устанавливаем картинки
        if (images[playerChoice] != null) {
            playerImageLabel.setIcon(images[playerChoice]);
        }
        if (images[computerChoice] != null) {
            computerImageLabel.setIcon(images[computerChoice]);
        }

        // Логика игры
        String result;
        if (playerChoice == computerChoice) {
            result = "НИЧЬЯ! 🤝";
        } else if ((playerChoice == 0 && computerChoice == 1) ||
                (playerChoice == 1 && computerChoice == 2) ||
                (playerChoice == 2 && computerChoice == 0)) {
            result = "ТЫ ПОБЕДИЛ! 🎉";
        } else {
            result = "КОМПЬЮТЕР ПОБЕДИЛ! 💻";
        }

        resultLabel.setText(result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleGameWithImages().setVisible(true);
        });
    }
}
