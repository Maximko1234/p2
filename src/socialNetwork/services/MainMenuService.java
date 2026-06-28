package socialNetwork.services;

import javax.swing.*;
import java.awt.*;

public class MainMenuService {

    public void showMainMenu(String login, JFrame frame) {
        frame.setVisible(false);

        JFrame mainFrame = new JFrame("Главное меню");
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(5, 1, 10, 10));

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

        mainFrame.add(welcomeLabel);
        mainFrame.add(friendsButton);
        mainFrame.add(findUserButton);
        mainFrame.add(allUsersButton);
        mainFrame.add(exitButton);

        mainFrame.setVisible(true);
    }
}
