package socialNetwork.services;

import socialNetwork.Person;
import socialNetwork.UserStorage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FriendsService {

    public void showProfile(String login) {
        UserStorage storage = new UserStorage();
        List<Person> users = storage.getPersons();
        Person person = null;
        for (Person user : users) {
            if (user.getLogin().equals(login)) {
                person = user;
            }
        }

        //TODO выбрать из пользователей друзей
        List<Person> friends = new ArrayList<>();
        List<Integer> friendsId = person.getFriends();
        //цикл по всем пользователям, где нужных людей добавляем в friends

        JFrame mainFrame = new JFrame("Личная страница"); //TODO поменять название
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(5, 2, 5, 5));

        // Главный панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Заголовок
        JLabel titleLabel = new JLabel("Все пользователи"); //TODO поменять заголовок
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Панель со списком пользователей (с прокруткой)
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEtchedBorder());

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Добавляем каждого пользователя в список
        for (Person user : friends) {
            JPanel userRow = createUserRow(user, person, storage);
            listPanel.add(userRow);
            listPanel.add(Box.createVerticalStrut(5)); // отступ между строками
        }

        // Кнопка "Назад"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton backButton = new JButton("Назад");
        backButton.addActionListener(e -> mainFrame.dispose());
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainFrame.add(mainPanel);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private JPanel createUserRow(Person user, Person currentUser, UserStorage storage) {
        JPanel row = new JPanel();
        row.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                new EmptyBorder(8, 10, 8, 10)
        ));
        row.setPreferredSize(new Dimension(720, 40));
        row.setMaximumSize(new Dimension(720, 40));

        // Имя
        JLabel firstNameLabel = new JLabel(user.getFirstName());
        firstNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        row.add(firstNameLabel);

        // Фамилия
        JLabel lastNameLabel = new JLabel(user.getLastName());
        lastNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        row.add(lastNameLabel);

        // Кнопка "Убрать из друзей"
        JButton friendButton;
        if (currentUser != null && currentUser.getFriends().contains(user.getId())) {
            // Уже в друзьях
            friendButton = new JButton("✓ В друзьях");
            friendButton.setEnabled(false);
            friendButton.setBackground(new Color(220, 250, 220));
            friendButton.setFocusPainted(false);
        } else {
            // Можно добавить в друзья (и user не сам currentUser)
            if (currentUser != null && currentUser.getId() != user.getId()) {
                friendButton = new JButton("Добавить в друзья");
                final Person targetUser = user;
                friendButton.addActionListener(e -> {
                    currentUser.addFriend(targetUser);
                    storage.save();
                    // Обновляем кнопку на этой строке
                    friendButton.setText("✓ В друзьях");
                    friendButton.setEnabled(false);
                    friendButton.setBackground(new Color(220, 250, 220));
                });
            } else {
                // Это сам текущий пользователь
                friendButton = new JButton("Вы");
                friendButton.setEnabled(false);
                friendButton.setBackground(new Color(240, 240, 240));
                friendButton.setFocusPainted(false);
            }
        }

        friendButton.setFocusPainted(false);
        friendButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        row.add(friendButton);

        return row;
    }
}
