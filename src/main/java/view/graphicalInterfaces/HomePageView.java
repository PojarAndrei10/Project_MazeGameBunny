package view.graphicalInterfaces;

import presenter.HomePagePresenter;
import view.interf.HomePageInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageView implements HomePageInterface {

    private HomePagePresenter homePagePresenter;
    private JFrame frame;
    private JButton l3,l4,l5;
    private JButton login;
    private JTextField usernameField,passwordField;
    private JLabel user;
    private JPanel panel;
    private JPanel buttonPanel;
    private JLabel userScoreLevel1;
    private JLabel userScoreLevel2;
    private JLabel userScoreLevel3;

    public HomePageView() {

        homePagePresenter = new HomePagePresenter(this);

        homePagePresenter = new HomePagePresenter(this);
        frame = new JFrame("Rabbit Maze Game->Pojar Andrei-Gabriel");
        frame.setLayout(new BorderLayout());
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel();
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        user = new JLabel();
        user.setFont(new Font("Arial", Font.BOLD, 17));
        user.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(user, BorderLayout.PAGE_END);
        frame.add(titlePanel, BorderLayout.NORTH);

        userScoreLevel1 = new JLabel();
        userScoreLevel2 = new JLabel();
        userScoreLevel3 = new JLabel();

        titlePanel.add(userScoreLevel1, BorderLayout.LINE_START);
        titlePanel.add(userScoreLevel2, BorderLayout.CENTER);
        titlePanel.add(userScoreLevel3, BorderLayout.LINE_END);
        allButtons();
        usernameAndPassword();
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void allButtons() {
        login = new JButton("Login");
        login.setBackground(new Color(135, 206, 250)); // Albastru deschis
        login.addActionListener(createLogInButtonListener());

        l3 = new JButton("Play level 1");
        l3.setBackground(new Color(135, 206, 250));
        l3.addActionListener(createPlayLevel3ButtonListener());

        l4 = new JButton("Play level 2");
        l4.setBackground(new Color(135, 206, 250));
        l4.addActionListener(createPlayLevel4ButtonListener());

        l5 = new JButton("Play level 3");
        l5.setBackground(new Color(135, 206, 250));
        l5.addActionListener(createPlayLevel5ButtonListener());
        buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(login, BorderLayout.NORTH);
        JPanel levelButtonPanel = new JPanel(new GridLayout(3, 1));
        levelButtonPanel.add(l3);
        levelButtonPanel.add(l4);
        levelButtonPanel.add(l5);

        buttonPanel.add(levelButtonPanel, BorderLayout.EAST);
        frame.add(buttonPanel, BorderLayout.PAGE_END);
    }

    void usernameAndPassword() {
        // setez nr de coloane pentru text fielduri
        final int NUM_COLUMNS = 20;

        usernameField = new JTextField();
        usernameField.setColumns(NUM_COLUMNS);

        passwordField = new JTextField();
        passwordField.setColumns(NUM_COLUMNS);

        panel = new JPanel(new GridLayout(2, 2));
        panel.setBounds(100, 100, 100, 100);
        panel.setMaximumSize(new Dimension(100, 100));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        Font labelFont = new Font("Arial", Font.BOLD, 10);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        JLabel usernameExplanationLabel = new JLabel("Username:");
        JLabel passwordExplanationLabel = new JLabel("Password:");

        Font explanationFont = new Font("Arial", Font.PLAIN, 10);
        usernameExplanationLabel.setFont(explanationFont);
        passwordExplanationLabel.setFont(explanationFont);

        panel.add(usernameExplanationLabel);
        panel.add(usernameField);
        panel.add(passwordExplanationLabel);
        panel.add(passwordField);
        frame.add(panel, BorderLayout.EAST);
    }
    private ActionListener createLogInButtonListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(playerAndLogin()){
                    helloLogin(homePagePresenter.getUser().getLegalName(),homePagePresenter.getUser().getScore());
                    if(homePagePresenter.getUser().isAdmin())
                        homePagePresenter.adminView();
                }
            }
        };
    }
    public void updateScores(Integer scoreLevel1, Integer scoreLevel2, Integer scoreLevel3) {
        userScoreLevel1.setText("Score Level 1: " + scoreLevel1);
        userScoreLevel2.setText("Score Level 2: " + scoreLevel2);
        userScoreLevel3.setText("Score Level 3: " + scoreLevel3);
    }

    public void helloLogin(String legalName, Integer totalScore) {
        user.setText("Score on the 3 levels played: " + totalScore);
        frame.setVisible(true);
    }

    private ActionListener createPlayLevel3ButtonListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                homePagePresenter.mazeBunnyView(Integer.valueOf(3));
            }
        };
    }
    private ActionListener createPlayLevel4ButtonListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                homePagePresenter.mazeBunnyView(Integer.valueOf(4));
            }
        };
    }
    private ActionListener createPlayLevel5ButtonListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                homePagePresenter.mazeBunnyView(Integer.valueOf(5));
            }
        };
    }
    @Override
    public boolean playerAndLogin() {
        return homePagePresenter.playerLogin();
    }
    @Override
    public String getUsername() {
        return usernameField.getText();
    }

    @Override
    public String getPassword() {
        return passwordField.getText();
    }

    @Override
    public void setMessage(String msg) {
        JFrame frame;
        frame = new JFrame();
        JOptionPane.showMessageDialog(frame, msg);
    }

}