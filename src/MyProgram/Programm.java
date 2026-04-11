package MyProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Programm extends JFrame {

    // МЕТОД для запуска игры
    private void startGame() {
        // СОЗДАЕМ ОКНО ИГРЫ
        JFrame gameFrame = new JFrame("Камень-Ножницы-Бумага");
        gameFrame.setSize(400, 300);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        gameFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(true);
            }
        });

        // Главная панель
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Панель для надписи
        JPanel titlePanel = new JPanel(new FlowLayout());
        JLabel titleLabel = new JLabel("Камень-Ножницы-Бумага");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titlePanel.add(titleLabel);

        // Панель для кнопок игры
        JPanel gamePanel = new JPanel(new FlowLayout());

        JButton rockButton = new JButton("🪨 Камень");
        rockButton.setFont(new Font("Arial", Font.BOLD, 14));

        JButton scissorsButton = new JButton("✂️ Ножницы");
        scissorsButton.setFont(new Font("Arial", Font.BOLD, 14));

        JButton paperButton = new JButton("📄 Бумага");
        paperButton.setFont(new Font("Arial", Font.BOLD, 14));

        gamePanel.add(rockButton);
        gamePanel.add(scissorsButton);
        gamePanel.add(paperButton);

        // Кнопка возврата
        JButton backButton = new JButton("← В главное меню");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.LIGHT_GRAY);
        backButton.addActionListener(e -> {
            gameFrame.dispose();
            setVisible(true);
        });

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(backButton);

        // Собираем всё вместе
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Добавляем панель в окно
        gameFrame.add(mainPanel);

        // Действия на кнопки игры
        rockButton.addActionListener(e ->
                JOptionPane.showMessageDialog(gameFrame, "Вы выбрали Камень"));
        scissorsButton.addActionListener(e ->
                JOptionPane.showMessageDialog(gameFrame, "Вы выбрали Ножницы"));
        paperButton.addActionListener(e ->
                JOptionPane.showMessageDialog(gameFrame, "Вы выбрали Бумагу"));

        gameFrame.setVisible(true);
    }

    public Programm() {
        // Настройки окна
        setTitle("Моя программа");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // СОЗДАЕМ ЭЛЕМЕНТЫ
        JLabel label = new JLabel("Привет, это моя программа!");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.BLUE);

        JTextField textField = new JTextField(20);
        textField.setToolTipText("Введи что-нибудь здесь");

        JButton button = new JButton("Нажми меня");
        button.setBackground(Color.GREEN);
        button.setFont(new Font("Arial", Font.BOLD, 14));

        JButton clearButton = new JButton("Очистить");
        clearButton.setBackground(Color.RED);
        clearButton.setForeground(Color.WHITE);

        JButton Tbutton = new JButton("тыкни");
        Tbutton.setBackground(Color.PINK);
        Tbutton.setFont(new Font("Arial", Font.BOLD, 14));

        JButton Rbutton = new JButton("проверить тыки");
        Rbutton.setBackground(Color.PINK);
        Rbutton.setFont(new Font("Arial", Font.BOLD, 14));

        JButton Ybutton = new JButton("+1 тык");
        Ybutton.setBackground(Color.PINK);
        Ybutton.setFont(new Font("Arial", Font.BOLD, 14));

        // КНОПКА ДЛЯ ИГРЫ
        JButton gameButton = new JButton("🎮 Играть в КНБ");
        gameButton.setBackground(Color.ORANGE);
        gameButton.setFont(new Font("Arial", Font.BOLD, 14));

        gameButton.addActionListener(e -> {
            setVisible(false);  // прячем главное окно
            startGame();        // открываем окно игры
        });

        // СЧЕТЧИК
        int[] x = {0};

        // ДЕЙСТВИЯ КНОПОК
        button.addActionListener(e -> {
            String text = textField.getText();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Ты ничего не ввел!",
                        "Ошибка",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Привет, " + text + "! Ты молодец!",
                        "Приветствие",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> {
            textField.setText("");
            textField.requestFocus();
        });

        Tbutton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                    "тыкнул, ну ты крутой",
                    "действие",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        Ybutton.addActionListener(e -> {
            x[0] = x[0] + 1;
        });

        Rbutton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                    "Количество тыков: " + x[0],
                    "Счетчик",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // ПАНЕЛЬ И ДОБАВЛЕНИЕ ЭЛЕМЕНТОВ
        JPanel panel = new JPanel(new FlowLayout());

        panel.add(label);
        panel.add(textField);
        panel.add(button);
        panel.add(clearButton);
        panel.add(Tbutton);
        panel.add(Ybutton);
        panel.add(Rbutton);
        panel.add(gameButton);

        getContentPane().add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Programm();
    }
}