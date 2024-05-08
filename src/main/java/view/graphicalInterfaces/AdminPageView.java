package view.graphicalInterfaces;

import presenter.AdministratorPresenter;
import view.interf.AdminInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminPageView implements AdminInterface {
    private AdministratorPresenter administratorPresenter;
    private JFrame frame;
    private JTextField legalName;
    private JRadioButton admin;
    private JTextField usernameTextField, passwordTextField;

    public AdminPageView() {
        administratorPresenter = new AdministratorPresenter(this);
        frame = new JFrame("Admin");
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 255, 153)); // Set background color to light yellow

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.YELLOW); // background
        JLabel titleLabel = new JLabel("Admin Panel");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.NORTH);
        gamersTable();
        GUI();
        frame.setSize(1000, 700);
        frame.setVisible(true);
    }

    private void GUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel createPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        legalName = new JTextField(10);
        JLabel legalNameLabel = new JLabel("Legal Name:");
        admin = new JRadioButton("isAdmin");
        usernameTextField = new JTextField(10);
        JLabel usernameLabel = new JLabel("Username:");
        passwordTextField = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password:");
        createPanel.add(legalNameLabel);
        createPanel.add(legalName);
        createPanel.add(admin);
        createPanel.add(usernameLabel);
        createPanel.add(usernameTextField);
        createPanel.add(passwordLabel);
        createPanel.add(passwordTextField);
        leftPanel.add(createPanel);
        panel.add(leftPanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        JButton deleteButton = new JButton("DELETE");
        deleteButton.setBackground(Color.CYAN);
        deleteButton.addActionListener(createDeleteButtonListener());
        rightPanel.add(deleteButton);

        JButton createButton = new JButton("CREATE");
        createButton.setBackground(Color.CYAN);
        createButton.addActionListener(createButtonListener());
        rightPanel.add(createButton);

        JButton updateButton = new JButton("UPDATE");
        updateButton.setBackground(Color.CYAN);
        updateButton.addActionListener(createUpdateButtonListener());
        rightPanel.add(updateButton);

        JButton backButton = new JButton("BACK");
        backButton.setBackground(Color.CYAN);
        backButton.addActionListener(createBackButtonListener());
        rightPanel.add(backButton);

        panel.add(rightPanel);
        frame.add(panel, BorderLayout.SOUTH);
    }

    private ActionListener createUpdateButtonListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (updateUser()) {
                    gamersTable();
                } else {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Incorrect input for a User");
                }
            }
        };
    }

    private ActionListener createBackButtonListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // iesire
                frame.dispose();
            }
        };
    }

    private ActionListener createButtonListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (createUser()) {
                    gamersTable();
                } else {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Incorrect input");
                }
            }
        };
    }
// tabela pt afisarea playerilor autentificati
    private void gamersTable() {

        String[] name = {"Users"};

        JPanel gamersPanel = new JPanel(new GridLayout(1, 1));
        List<String> players = getUsers();
        String[][] v = new String[players.size()][1];
        for (int i = 0; i < players.size(); i++) {
            v[i][0] = players.get(i);
        }
        JTable playerss = new JTable(v, name);
        JScrollPane userScrollPane = new JScrollPane(playerss);
        gamersPanel.add(userScrollPane);
        frame.add(gamersPanel, BorderLayout.CENTER);
        frame.revalidate();
    }

    private ActionListener createDeleteButtonListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (deleteUser()) {
                    gamersTable();
                } else {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Incorrect id");
                }
            }
        };
    }

    @Override
    public List<String> getUsers() {
        return administratorPresenter.allPlayers();
    }

    @Override
    public boolean deleteUser() {
        return administratorPresenter.delete(getLegalName());
    }

    @Override
    public boolean createUser() {
        return administratorPresenter.create();
    }

    @Override
    public boolean updateUser() {
        return administratorPresenter.update();
    }

    @Override
    public String getLegalName() {
        return legalName.getText();
    }

    @Override
    public String getisAdmin() {
        return String.valueOf(admin.isSelected());
    }

    @Override
    public String getUsername() {
        return usernameTextField.getText();
    }

    @Override
    public String getPassword() {
        return passwordTextField.getText();
    }
}
