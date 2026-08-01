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
        for (Person user : users) {
            for (Integer id : friendsId) {
                if (user.getId() == id) {
                    friends.add(user);
                }
            }
        }

        JFrame mainFrame = new JFrame("Друзья");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Главный панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Заголовок
        JLabel titleLabel = new JLabel("Все друзья");
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
        friendButton = new JButton("Убрать из друзей");
        final Person targetUser = user;
        friendButton.addActionListener(e -> {
            currentUser.deleteFriend(targetUser);
            storage.save();
            // Обновляем кнопку на этой строке
            friendButton.setText("удален из друзей");
            friendButton.setEnabled(false);
            friendButton.setBackground(Color.RED);
        });

        friendButton.setFocusPainted(false);
        friendButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        row.add(friendButton);

        return row;
    }
}
