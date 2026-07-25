package socialNetwork.services;

import javax.swing.*;
import java.awt.*;

public class MainMenuService {

    public void showMainMenu(String login, JFrame frame) {
        frame.setVisible(false);

        JFrame mainFrame = new JFrame("Главное меню");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout(10, 10));
        
        JLabel welcomeLabel = new JLabel("Добро пожаловать, " + login + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton friendsButton = new JButton("Мои друзья");
        friendsButton.setFont(new Font("Arial", Font.BOLD, 16));

        JButton findUserButton = new JButton("Найти пользователя");
        findUserButton.setFont(new Font("Arial", Font.BOLD, 16));

        JButton allUsersButton = new JButton("Все пользователи");
        allUsersButton.setFont(new Font("Arial", Font.BOLD, 16));

        JButton exitButton = new JButton("Выйти");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.addActionListener(e -> {

            System.exit(0);
        });
        
        // Панель для бокового меню (слева)
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        
        // Устанавливаем одинаковую ширину для всех кнопок
        int buttonWidth = 200;
        int buttonHeight = 40;
        friendsButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        findUserButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        allUsersButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        exitButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        
        sidebarPanel.add(friendsButton);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(findUserButton);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(allUsersButton);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(exitButton);
        sidebarPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        // Панель для кнопок по центру слева
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(welcomeLabel, BorderLayout.NORTH);
        centerPanel.add(sidebarPanel, BorderLayout.CENTER);
        
        mainFrame.add(centerPanel, BorderLayout.LINE_START);
        mainFrame.add(new JPanel(), BorderLayout.CENTER); // Пустое место справа

        mainFrame.setVisible(true);
    }
}
