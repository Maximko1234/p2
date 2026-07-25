package socialNetwork.services;

import socialNetwork.Person;
import socialNetwork.UserStorage;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyProfileService {

    public void showProfile(String login) {
        UserStorage storage = new UserStorage();
        List<Person> users = storage.getPersons();
        Person person = null;
        for(Person user : users){
            if(user.getLogin().equals(login)){
                person = user;
            }
        }

        JFrame mainFrame = new JFrame("Личная страница");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(5, 2, 5, 5));

        JLabel nameLabel = new JLabel(person.getFirstName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lastNameLabel = new JLabel(person.getLastName());
        lastNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel drLabel = new JLabel("день рождение:");
        drLabel.setFont(new Font("Arial", Font.BOLD, 16));
        drLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel drValueLabel = new JLabel(person.getBirthday());
        drValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        drValueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel cityLabel = new JLabel("Город:");
        cityLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cityLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel cityValueLabel = new JLabel(person.getCity());
        cityValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cityValueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel friendsLabel = new JLabel("Друзей:");
        friendsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        friendsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel friendsValueLabel = new JLabel(String.valueOf(person.getFriends().size()));
        friendsValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        friendsValueLabel.setHorizontalAlignment(SwingConstants.CENTER);


        mainFrame.add(nameLabel);
        mainFrame.add(nameValueLabel);
        mainFrame.add(lastNameLabel);
        mainFrame.add(lastNameValueLabel);
        mainFrame.add(cityLabel);
        mainFrame.add(cityValueLabel);
        mainFrame.add(drLabel);
        mainFrame.add(drValueLabel);
        mainFrame.add(friendsLabel);
        mainFrame.add(friendsValueLabel);


        mainFrame.setVisible(true);
    }
}
