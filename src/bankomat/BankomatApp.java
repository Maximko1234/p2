package bankomat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class BankomatApp {

    private JFrame frame;
    private Storage storage;
    private String login;

    public static void main(String[] args) {
        new BankomatApp().showLogin();
    }
    //настройка окна
    private void showLogin() {
        storage = new Storage();
        //Настройки окна
        frame = new JFrame("Банкомат - вход");
        try {
            Image icon = ImageIO.read(new File( "src\\bankomat\\icon.png"));
            frame.setIconImage(icon);
        } catch (IOException e){
            System.out.println("картинка не найдена"+ e.getMessage());
        }
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 5, 5));

        //Основные кнопки
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
        registrationButton.addActionListener(e -> showRegistrationScreen());


        frame.add(loginLabel);
        frame.add(loginField);
        frame.add(passwordLabel);
        frame.add(passwordFieled);
        frame.add(registrationButton);
        frame.add(loginButton);
        frame.setVisible(true);
    }

    private void showMainMenu() {
        frame.getContentPane().removeAll();
        frame.setTitle("Банкомат - меню пользователя " + login);
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1, 5, 5));

        //кнопки в окне банка
        JButton depositButton = new JButton("Положить деньги");
        JButton snyatieButton = new JButton("Снять деньги");
        JButton perevodButton = new JButton("Перевести деньги");
        JButton BalanceButton = new JButton("Проверить баланс");
        JButton exitButton = new JButton("Выход");

        depositButton.addActionListener(e-> popolnenie());
        snyatieButton.addActionListener(e -> snytie());
        BalanceButton.addActionListener(e -> Balance());
        perevodButton.addActionListener(e -> perevod());
        exitButton.addActionListener(e -> exit());


        //добавление кнопок на экран
        frame.add(depositButton);
        frame.add(snyatieButton);
        frame.add(perevodButton);
        frame.add(BalanceButton);
        frame.add(exitButton);

        frame.revalidate();
        frame.repaint();
    }
    private void Balance(){
        JOptionPane.showMessageDialog(frame, "баланс: " + storage.balance.get(login));
    }
    private void popolnenie() {
        String input = JOptionPane.showInputDialog(frame,"Beeдите сумму пополнения");
        try {
            int summa = Integer.parseInt(input);
            if (summa > 0) {
                System.out.println(login);
                int balance = storage.balance.get(login);
                storage.balance.put(login, balance + summa);
                JOptionPane.showMessageDialog(frame, "Счет пополнен. Новый баланс: " + storage.balance.get(login));
            } else {
                JOptionPane.showMessageDialog(frame, "Неверная сумма!", "ERROR", ERROR_MESSAGE);
            }
        }catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(frame, "Введите корректное число", "Ошибка ввода числа", ERROR_MESSAGE);

        }
    }

    private void snytie() {
        String input = JOptionPane.showInputDialog(frame,"Beeдите сумму для снятия");
        try {
            int summa = Integer.parseInt(input);
            if (summa >= 0) {
                int balance = storage.balance.get(login);
                storage.balance.put(login, balance - summa);
                JOptionPane.showMessageDialog(frame, "деньги сняты. Новый баланс: " + storage.balance.get(login));
            } else {
                JOptionPane.showMessageDialog(frame, "Неверная сумма!", "ERROR", ERROR_MESSAGE);
            }
        }catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(frame, "Введите корректное число", "Ошибка ввода числа", ERROR_MESSAGE);

        }
    }
    private void perevod() {
        String input = JOptionPane.showInputDialog(frame,"Beeдите сумму для перевода");
        String komu = JOptionPane.showInputDialog(frame,"Beeдите логин пользователя");
        if(!storage.logins.containsKey(komu)){
            JOptionPane.showMessageDialog(frame, "пользователь не найден","ERROR",ERROR_MESSAGE);
            return;
        }
        try {
            int summa = Integer.parseInt(input);
            if (summa > 0) {
                int balance = storage.balance.get(login);
                int balance2 = storage.balance.get(login);
                if (balance >= summa)  {
                    storage.balance.put(login, balance - summa);
                    storage.balance.put(komu, balance - summa);
                }
                JOptionPane.showMessageDialog(frame, "успешный перевод.ваш баланс: " + storage.balance.get(login));
            } else {
                JOptionPane.showMessageDialog(frame, "Неверная сумма!", "ERROR", ERROR_MESSAGE);
            }
        }catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(frame, "Введите корректное число", "Ошибка ввода числа", ERROR_MESSAGE);

        }
    }

    private void exit(){
        login ="";
        frame.dispose();
        showLogin();
    }

    private void showRegistrationScreen() {
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
        password2Label.setFont(new Font( "Arial", Font.PLAIN,14));
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

            if (storage.logins.containsKey(login)) {
                message.setText("Такой логин уже существует");
                message.setForeground(Color.RED);
                return;
            }

            if (!pass1.equals(pass2)) {
                message.setText("Пароли не совпадают!");
                message.setForeground(Color.RED);
                return;
            }

            storage.logins.put(login, pass1);
            storage.balance.put(login, 0);
            JOptionPane.showMessageDialog(regFrame, "Регистрация успешна! Ваш логин: " + login);
            regFrame.dispose();
            this.login = login;
            showMainMenu();
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
    }


    //перенос в новое окно и отправка сообщения при успешном\неуспешном входе
    private JButton getjButton(JTextField loginField, JPasswordField passwordFieled) {
        JButton loginButton = new JButton("Войти");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.addActionListener(e -> {
            login = loginField.getText();
            String password = new String(passwordFieled.getPassword());
            if (storage.logins.containsKey(login)) {
                String truePassword = storage.logins.get(login);
                if (password.equals(truePassword)) {
                    JOptionPane.showMessageDialog(frame, "Добро пожаловать, " + login);
                    showMainMenu();
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
