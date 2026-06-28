package socialNetwork;

import socialNetwork.services.MainMenuService;
import socialNetwork.services.RegistrationService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class Application {

    //фрейм для отображения окон
    private JFrame frame;
    //хранилище пользователей
    private UserStorage storage = new UserStorage();
    //логин авторизованного пользователя
    private String login;

    //сервис меню
    MainMenuService mainMenuService = new MainMenuService();
    RegistrationService regService = new RegistrationService();

    public static void main(String[] args) {
        new Application().showLogin();
    }

    //Отображает окно входа в систему
    //Создает GUI с полями для ввода логина и пароля, кнопками входа и регистрации
    private void showLogin() {
        //Создание и настройка главного окна приложения
        frame = new JFrame("Сеть - вход");
        try {
            Image icon = ImageIO.read(new File("src\\socialNetwork\\images\\icon.png"));
            frame.setIconImage(icon);
        } catch (IOException e) {
            //Обработка ошибки: если иконка не найдена, выводится сообщение в консоль
            System.out.println("картинка не найдена" + e.getMessage());
        }
        //Установка размеров окна и действия при закрытии
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Настройка сетки для размещения компонентов (4 строки, 2 столбца, отступы 5px)
        frame.setLayout(new GridLayout(4, 2, 5, 5));

        //Создание и настройка элементов интерфейса для ввода логина и пароля
        JLabel loginLabel = new JLabel("Логин:");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 16));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField loginField = new JTextField();
        loginField.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel passwordLabel = new JLabel("Пароль:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPasswordField passwordFieled = new JPasswordField();
        passwordFieled.setFont(new Font("Arial", Font.BOLD, 16));

        JButton loginButton = getjButton(loginField, passwordFieled);

        JButton registrationButton = new JButton("Регистрация");
        registrationButton.setFont(new Font("Arial", Font.BOLD, 16));
        registrationButton.addActionListener(e -> regService.showRegistrationScreen(storage));

        frame.add(loginLabel);
        frame.add(loginField);
        frame.add(passwordLabel);
        frame.add(passwordFieled);
        frame.add(registrationButton);
        frame.add(loginButton);
        frame.setVisible(true);
    }


    private JButton getjButton(JTextField loginField, JPasswordField passwordFieled) {
        JButton loginButton = new JButton("Войти");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.addActionListener(e -> {
            login = loginField.getText();
            String password = new String(passwordFieled.getPassword());
            if (storage.getLogins().containsKey(login)) {
                String truePassword = storage.getLogins().get(login);
                if (password.equals(truePassword)) {
                    JOptionPane.showMessageDialog(frame, "Добро пожаловать, " + login);
                    mainMenuService.showMainMenu(login, frame);
                } else {
                    JOptionPane.showMessageDialog(frame, "Неверный пароль!", "Ошибка", ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Пользователь не найден!", "Ошибка", ERROR_MESSAGE);
            }
        });
        return loginButton;
    }

}