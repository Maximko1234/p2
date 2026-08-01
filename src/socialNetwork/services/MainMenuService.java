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
        friendsButton.addActionListener(e -> {
            FriendsService friendsService = new FriendsService();
            friendsService.showProfile(login);
        });

        JButton findUserButton = new JButton("Найти пользователя");
        findUserButton.setFont(new Font("Arial", Font.BOLD, 16));
        findUserButton.addActionListener(e -> {
            SearchPersonService searchPersonService = new SearchPersonService();
            searchPersonService.showPersons(login);
        });

        JButton profileButton = new JButton("Моя страница");
        profileButton.setFont(new Font("Arial", Font.BOLD, 16));
        profileButton.addActionListener(e -> {
            MyProfileService myProfileService = new MyProfileService();
            myProfileService.showProfile(login);
        });

        JButton exitButton = new JButton("Выйти");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.addActionListener(e -> {

            System.exit(0);
        });

        mainFrame.add(welcomeLabel);
        mainFrame.add(profileButton);
        mainFrame.add(friendsButton);
        mainFrame.add(findUserButton);
        mainFrame.add(exitButton);

        mainFrame.setVisible(true);
    }
}
