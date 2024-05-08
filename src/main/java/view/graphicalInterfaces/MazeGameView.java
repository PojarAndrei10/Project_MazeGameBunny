package view.graphicalInterfaces;

import model.Point;
import model.User;
import presenter.MazeGamePresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import view.interf.MazeGameInterface;

public class MazeGameView implements MazeGameInterface {

    private MazeGamePresenter mazeGamePresenter;
    private JFrame frame;
    private JPanel panel;
    private ArrayList<JButton> buttons;

    public MazeGameView(User userLoggedIn, Integer level) {
        this.mazeGamePresenter = new MazeGamePresenter(this, userLoggedIn, level);

        frame = new JFrame("Game Page");
        frame.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Username:");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(new Color(0, 102, 204)); // Albastru deschis
        titlePanel.add(titleLabel, BorderLayout.WEST);
        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel passwordPanel = new JPanel(new BorderLayout());
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setForeground(new Color(0, 102, 204)); // Albastru deschis
        passwordPanel.add(passwordLabel, BorderLayout.WEST);
        frame.add(passwordPanel, BorderLayout.CENTER);

        setup(level);
        try {
            imageGrid(level);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        frame.setSize(1000, 700);
        frame.setVisible(true);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                int moveIndex = -1;
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        moveIndex = -mazeGamePresenter.getMazeGame().getMazeGrid().getLevelGame();
                        break;
                    case KeyEvent.VK_DOWN:
                        moveIndex = mazeGamePresenter.getMazeGame().getMazeGrid().getLevelGame();
                        break;
                    case KeyEvent.VK_LEFT:
                        moveIndex = -1;
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveIndex = 1;
                        break;
                }

                if (moveIndex != -1) {
                    mazeGamePresenter.bunnyPlay(moveIndex);
                    try {
                        imageGrid(mazeGamePresenter.getMazeGame().getMazeGrid().getLevelGame());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    mazeGamePresenter.winOrLoseGame();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }


    private void imageGrid(Integer level) throws IOException {
        int gridSize = level * level;

        for (int i = 0; i < gridSize; i++) {
            if (i < buttons.size()) { // Verificăm dacă indexul este valid
                buttons.get(i).setIcon(new ImageIcon("C://Users//andre//Documents//AN 3 SEM 2//Proiectare Software/TEMA1/drum2.png"));
            }
        }

        int levelGame = mazeGamePresenter.getMazeGame().getMazeGrid().getLevelGame();
        Point carrot = mazeGamePresenter.getMazeGame().getMazeGrid().getCarrot();
        Point rabbit = mazeGamePresenter.getMazeGame().getMazeGrid().getRabbit();

        int carrotIndex = (carrot.getA() - 1) * levelGame + (carrot.getB() - 1);
        int rabbitIndex = (rabbit.getA() - 1) * levelGame + (rabbit.getB() - 1);

        if (carrotIndex < buttons.size()) {
            buttons.get(carrotIndex).setIcon(new ImageIcon("C:/Users/andre/Documents/AN 3 SEM 2/Proiectare Software/TEMA1/carrots.png"));
        }
        if (rabbitIndex < buttons.size()) {
            buttons.get(rabbitIndex).setIcon(new ImageIcon("C:/Users/andre/Documents/AN 3 SEM 2/Proiectare Software/TEMA1/bunny.png"));
        }

        for (Point trap : mazeGamePresenter.getMazeGame().getMazeGrid().getTrap()) {
            int trapIndex = (trap.getA() - 1) * levelGame + (trap.getB() - 1);
            if (trapIndex < buttons.size()) {
                buttons.get(trapIndex).setIcon(new ImageIcon("C:/Users/andre/Documents/AN 3 SEM 2/Proiectare Software/TEMA1/trap.png"));
            }
        }

        frame.setVisible(true);
    }

    private void setup(Integer level) {
        buttons = new ArrayList<>();
        panel = new JPanel();
        panel.setLayout(new GridLayout(level, level, 10, 10));

        for (int i = 0; i < level * level; i++) {
            JButton b = new JButton(String.valueOf(i));
            b.addActionListener(createGridButtonListener(i));
            buttons.add(b);
            panel.add(buttons.get(i));
        }

        // Adăugarea unei culori de fundal galben deschis la panoul principal
        panel.setBackground(new Color(255, 255, 153)); // Galben deschis

        frame.add(panel, BorderLayout.CENTER);
    }

    private ActionListener createGridButtonListener(int i) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mazeGamePresenter.bunnyPlay(i);
                try {
                    imageGrid(mazeGamePresenter.getMazeGame().getMazeGrid().getLevelGame());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                mazeGamePresenter.winOrLoseGame();
            }
        };
    }

    @Override
    public void setMessage(String msg) {
        JFrame frame;
        frame = new JFrame();
        JOptionPane.showMessageDialog(frame, msg);
    }
}
