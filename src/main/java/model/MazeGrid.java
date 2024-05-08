package model;

import java.util.ArrayList;
import java.util.Random;

public class MazeGrid {

    private Point carrot,rabbit;
    private ArrayList<Point> trap;
    private Integer levelGame;

    public MazeGrid(Point carrot, Point rabbit, ArrayList<Point> trap, Integer levelGame) {
        this.carrot = carrot;
        this.rabbit = rabbit;
        this.trap = trap;
        this.levelGame = levelGame;
    }
    public Point getCarrot() {
        return carrot;
    }
    public void setCarrot(Point carrot) {
        this.carrot = carrot;
    }
    public Point getRabbit() {
        return rabbit;
    }
    public void setRabbit(Point rabbit) {
        this.rabbit = rabbit;
    }
    public ArrayList<Point> getTrap() {
        return trap;
    }
    public void setTrap(ArrayList<Point> trap) {
        this.trap = trap;
    }
    public Integer getLevelGame() {
        return levelGame;
    }
    public void setLevelGame(Integer levelGame) {
        this.levelGame = levelGame;
    }
    private Point rabbitLevel(Integer rabbit) {
        int x = (rabbit / levelGame) + 1;
        int y = (rabbit % levelGame) + 1;
        int line= x+1;
        int column=y+1;
        return new Point(line,column);
    }
    public MazeGrid(Integer b) {
        this.levelGame = b;
        Random trapRandom = new Random();
        ArrayList<Point> rabbit = new ArrayList<>();
        Integer ii;
        Integer q = b * b;
        Integer carrot = trapRandom.nextInt(q);
        this.carrot = rabbitLevel(carrot);
        rabbit.add(new Point(1, 1));
        rabbit.add(new Point(1, b));
        rabbit.add(new Point(b, 1));
        rabbit.add(new Point(b, b));
        Integer nrTrap;
        Point trapp;
        Integer trappPoz;
        while (this.carrot.equals(this.rabbit) || this.rabbit == null) {
            ii = trapRandom.nextInt(4);
            this.rabbit = rabbit.get(ii);
        }

        if (b == 3) {
            nrTrap = 2;
        } else if (b == 4) {
            nrTrap = 4;
        } else if (b == 5) {
            nrTrap = 5;
        } else {
            nrTrap = 0;
        }

        this.trap = new ArrayList<>();
        for (int i = 0; i < nrTrap; i++) {
            trapp = null;
            while (this.carrot.equals(trapp) || this.rabbit.equals(trapp) || trapp == null
                    || this.trap.contains(trapp)) {
                trappPoz = trapRandom.nextInt(q);
                trapp = rabbitLevel(trappPoz);
            }
            this.trap.add(trapp);
        }
    }
}
