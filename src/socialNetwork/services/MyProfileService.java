package socialNetwork.services;

import socialNetwork.UserStorage;

import javax.swing.*;
import java.awt.*;

public class MyProfileService {

    public void showProfile(String login) {
        UserStorage storage = new UserStorage();

        JFrame mainFrame = new JFrame("Личная страница");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(5, 2, 5, 5));

        JLabel nameLabel = new JLabel("???");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lastNameLabel = new JLabel("???");
        lastNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel drLabel = new JLabel("День рождения:");
        drLabel.setFont(new Font("Arial", Font.BOLD, 16));
        drLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel drValueLabel = new JLabel("???");
        drValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        drValueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel cityLabel = new JLabel("Город:");
        cityLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cityLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel cityValueLabel = new JLabel("???");
        cityValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cityValueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel friendsLabel = new JLabel("Друзей:");
        friendsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        friendsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel friendsValueLabel = new JLabel("???");
        friendsValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        friendsValueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainFrame.add(nameLabel);
        mainFrame.add(lastNameLabel);

        mainFrame.setVisible(true);
    }
}
