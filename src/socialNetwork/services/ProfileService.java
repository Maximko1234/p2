package socialNetwork.services;

import socialNetwork.Person;
import socialNetwork.UserStorage;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

public class ProfileService {

    private Person user;

    public Person showProfileSettings(UserStorage storage, Person currentUser) {
        this.user = currentUser;

        JFrame profileFrame = new JFrame("Настройки профиля");
        profileFrame.setSize(400, 250);
        profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profileFrame.setLayout(new GridLayout(6, 2, 8, 8));

        JLabel lastNameLabel = new JLabel("Фамилия:");
        lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lastNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField lastNameField = new JTextField(currentUser.getLastName());

        JLabel firstNameLabel = new JLabel("Имя:");
        firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField firstNameField = new JTextField(currentUser.getFirstName());

        JLabel birthdayLabel = new JLabel("Дата рождения:");
        birthdayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        birthdayLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel birthdayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField birthdayField = new JTextField(10);
        if (currentUser.getBirthday() != null) {
            birthdayField.setText(currentUser.getBirthday().toString());
        }

        JButton calendarButton = new JButton("📅");
        calendarButton.setPreferredSize(new Dimension(40, 25));

        calendarButton.addActionListener(e -> {
            JDialog calendarDialog = new JDialog(profileFrame, "Выберите дату", true);
            calendarDialog.setSize(300, 350);
            calendarDialog.setLocationRelativeTo(profileFrame);

            JSpinner spinner = new JSpinner(new SpinnerDateModel());
            JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd");
            spinner.setEditor(editor);

            if (currentUser.getBirthday() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(Date.from(currentUser.getBirthday().atStartOfDay().toInstant(java.time.ZoneOffset.UTC)));
                spinner.setValue(cal.getTime());
            }

            JButton okButton = new JButton("OK");
            okButton.addActionListener(ev -> {
                Date selectedDate = (Date) spinner.getValue();
                Calendar cal = Calendar.getInstance();
                cal.setTime(selectedDate);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);
                birthdayField.setText(String.format("%04d-%02d-%02d", year, month, day));
                calendarDialog.dispose();
            });

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(spinner, BorderLayout.NORTH);
            panel.add(okButton, BorderLayout.SOUTH);

            calendarDialog.add(panel);
            calendarDialog.setVisible(true);
        });

        birthdayPanel.add(birthdayField);
        birthdayPanel.add(calendarButton);

        JButton saveButton = new JButton("Сохранить");
        JLabel message = new JLabel("", SwingConstants.CENTER);

        saveButton.addActionListener(e -> {
            String lastName = lastNameField.getText().trim();
            String firstName = firstNameField.getText().trim();
            String birthdayStr = birthdayField.getText().trim();

            if (lastName.isEmpty() || firstName.isEmpty()) {
                message.setText("Заполните Фамилию и Имя!");
                message.setForeground(Color.RED);
                return;
            }

            LocalDate birthday = null;
            if (!birthdayStr.isEmpty()) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    birthday = LocalDate.parse(birthdayStr, formatter);
                } catch (DateTimeParseException ex) {
                    message.setText("Неверный формат даты! Используйте YYYY-MM-dd");
                    message.setForeground(Color.RED);
                    return;
                }
            }

            user.setLastName(lastName);
            user.setFirstName(firstName);
            user.setBirthday(birthday);

            // Обновляем возраст, если изменилась дата рождения
            if (birthday != null) {
                int currentYear = LocalDate.now().getYear();
                int birthYear = birthday.getYear();
                user.setAge(currentYear - birthYear);
            }

            storage.savePerson(user);
            storage.save();

            JOptionPane.showMessageDialog(profileFrame, "Данные успешно сохранены!");
            profileFrame.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveButton);

        profileFrame.add(lastNameLabel);
        profileFrame.add(lastNameField);
        profileFrame.add(firstNameLabel);
        profileFrame.add(firstNameField);
        profileFrame.add(birthdayLabel);
        profileFrame.add(birthdayPanel);
        profileFrame.add(new JLabel());
        profileFrame.add(buttonPanel);
        profileFrame.add(new JLabel());
        profileFrame.add(message);

        profileFrame.pack();
        profileFrame.setLocationRelativeTo(null);
        profileFrame.setVisible(true);

        return this.user;
    }

    public Person showRegistrationProfileScreen(UserStorage storage, String login, String firstName, String lastName, LocalDate birthday) {
        this.user = new Person(login, firstName, lastName, "", birthday);
        storage.savePerson(this.user);

        JFrame profileFrame = new JFrame("Заполните данные профиля");
        profileFrame.setSize(400, 250);
        profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profileFrame.setLayout(new GridLayout(6, 2, 8, 8));

        JLabel lastNameLabel = new JLabel("Фамилия:");
        lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lastNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField lastNameField = new JTextField(lastName);

        JLabel firstNameLabel = new JLabel("Имя:");
        firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField firstNameField = new JTextField(firstName);

        JLabel birthdayLabel = new JLabel("Дата рождения:");
        birthdayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        birthdayLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel birthdayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField birthdayField = new JTextField(10);
        if (birthday != null) {
            birthdayField.setText(birthday.toString());
        }

        JButton calendarButton = new JButton("📅");
        calendarButton.setPreferredSize(new Dimension(40, 25));

        calendarButton.addActionListener(e -> {
            JDialog calendarDialog = new JDialog(profileFrame, "Выберите дату", true);
            calendarDialog.setSize(300, 350);
            calendarDialog.setLocationRelativeTo(profileFrame);

            JSpinner spinner = new JSpinner(new SpinnerDateModel());
            JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd");
            spinner.setEditor(editor);

            if (birthday != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(Date.from(birthday.atStartOfDay().toInstant(java.time.ZoneOffset.UTC)));
                spinner.setValue(cal.getTime());
            }

            JButton okButton = new JButton("OK");
            okButton.addActionListener(ev -> {
                Date selectedDate = (Date) spinner.getValue();
                Calendar cal = Calendar.getInstance();
                cal.setTime(selectedDate);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);
                birthdayField.setText(String.format("%04d-%02d-%02d", year, month, day));
                calendarDialog.dispose();
            });

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(spinner, BorderLayout.NORTH);
            panel.add(okButton, BorderLayout.SOUTH);

            calendarDialog.add(panel);
            calendarDialog.setVisible(true);
        });

        birthdayPanel.add(birthdayField);
        birthdayPanel.add(calendarButton);

        JButton finishButton = new JButton("Завершить");
        JLabel message = new JLabel("", SwingConstants.CENTER);

        finishButton.addActionListener(e -> {
            String finalLastName = lastNameField.getText().trim();
            String finalFirstName = firstNameField.getText().trim();
            String birthdayStr = birthdayField.getText().trim();

            if (finalLastName.isEmpty() || finalFirstName.isEmpty()) {
                message.setText("Заполните Фамилию и Имя!");
                message.setForeground(Color.RED);
                return;
            }

            LocalDate finalBirthday = null;
            if (!birthdayStr.isEmpty()) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    finalBirthday = LocalDate.parse(birthdayStr, formatter);
                    this.user.setBirthday(finalBirthday);

                    // Обновляем возраст
                    int currentYear = LocalDate.now().getYear();
                    int birthYear = finalBirthday.getYear();
                    this.user.setAge(currentYear - birthYear);
                } catch (DateTimeParseException ex) {
                    message.setText("Неверный формат даты! Используйте YYYY-MM-dd");
                    message.setForeground(Color.RED);
                    return;
                }
            }

            this.user.setLastName(finalLastName);
            this.user.setFirstName(finalFirstName);

            storage.savePerson(this.user);
            storage.save();

            JOptionPane.showMessageDialog(profileFrame, "Профиль успешно заполнен!");
            profileFrame.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(finishButton);

        profileFrame.add(lastNameLabel);
        profileFrame.add(lastNameField);
        profileFrame.add(firstNameLabel);
        profileFrame.add(firstNameField);
        profileFrame.add(birthdayLabel);
        profileFrame.add(birthdayPanel);
        profileFrame.add(new JLabel());
        profileFrame.add(buttonPanel);
        profileFrame.add(new JLabel());
        profileFrame.add(message);

        profileFrame.pack();
        profileFrame.setLocationRelativeTo(null);
        profileFrame.setVisible(true);

        return this.user;
    }
}
