package socialNetwork.services;

import socialNetwork.Person;
import socialNetwork.UserStorage;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class RegistrationService {

    private String login;
    private ProfileService profileService = new ProfileService();

    public String showRegistrationScreen(UserStorage storage) {
        JFrame regFrame = new JFrame("Регистрация нового пользователя");
        regFrame.setSize(400, 250);
        regFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        regFrame.setLayout(new GridLayout(5, 2, 8, 8));

        JLabel loginLabel = new JLabel("Придумайте логин:");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField loginField = new JTextField();

        JLabel passwordLabel = new JLabel("Пароль");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JPasswordField passwordField = new JPasswordField();

        JLabel password2Label = new JLabel("Повторите пароль");
        password2Label.setFont(new Font("Arial", Font.PLAIN, 14));
        password2Label.setHorizontalAlignment(SwingConstants.CENTER);
        JPasswordField password2Field = new JPasswordField();

        JButton registrationButton = new JButton("Зарегистрировать");
        JLabel message = new JLabel("", SwingConstants.CENTER);

        registrationButton.addActionListener(e -> {
            String login = loginField.getText();
            String pass1 = new String(passwordField.getPassword());
            String pass2 = new String(password2Field.getPassword());

            if (login.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                message.setText("Заполните все поля!");
                message.setForeground(Color.RED);
                return;
            }

            if (storage.getLogins().containsKey(login)) {
                message.setText("Такой логин уже существует");
                message.setForeground(Color.RED);
                return;
            }

            if (!pass1.equals(pass2)) {
                message.setText("Пароли не совпадают!");
                message.setForeground(Color.RED);
                return;
            }

            storage.saveLogin(login, pass1);

            // Создаем нового пользователя
            Person newUser = new Person(login, "", "", "", LocalDate.of(2000,1,1));
            storage.savePerson(newUser);
            this.login = login;

            JOptionPane.showMessageDialog(regFrame, "Регистрация успешна! Ваш логин: " + login);
            regFrame.dispose();
            profileService.showRegistrationProfileScreen(storage, login, "", "", LocalDate.of(2000,1,1));
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(registrationButton);

        regFrame.add(loginLabel);
        regFrame.add(loginField);
        regFrame.add(passwordLabel);
        regFrame.add(passwordField);
        regFrame.add(password2Label);
        regFrame.add(password2Field);
        regFrame.add(new JLabel());
        regFrame.add(buttonPanel);
        regFrame.add(new JLabel());
        regFrame.add(message);

        regFrame.setVisible(true);

        return this.login;
    }
}
