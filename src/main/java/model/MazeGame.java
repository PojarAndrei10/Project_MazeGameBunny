package model;

public class MazeGame {

    private MazeGrid mazeGrid;
    private User user;
    private Integer score;
    private Integer s;
    private Boolean victory,lose;

    public MazeGame(User gamer, MazeGrid mazeGrid) {
        this.user = gamer;
        this.mazeGrid = mazeGrid;
        this.s = 0;
        this.score = 0;
        this.victory=false;
        this.lose=false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    public Boolean getVictory() {
        return victory;
    }

    public void setVictory(Boolean victory) {
        this.victory = victory;
    }

    public Boolean getLose() {
        return lose;
    }

    public MazeGrid getMazeGrid() {
        return mazeGrid;
    }

    public void setMazeGrid(MazeGrid mazeGrid) {
        this.mazeGrid = mazeGrid;
    }

    public void setLose(Boolean lose) {
        this.lose = lose;
    }

    private boolean diagonal(int i) {
        int line = i / mazeGrid.getLevelGame() + 1;
        int column = i % mazeGrid.getLevelGame() + 1;

        int rabbit1 = mazeGrid.getRabbit().getA();
        int rabbit2 = mazeGrid.getRabbit().getB();

        // verificam daca miscarea este pe diagonala stanga-sus sau dreapta-jos
        if ((rabbit1 == line + 1 && rabbit2 == column + 1) ||
                (rabbit1 == line - 1 && rabbit2 == column - 1)) {
            return true;
        }
        // verificam dac miscarea este pe diagonala dreapta-sus sau stanga-jos
        if ((rabbit1 == line - 1 && rabbit2 == column + 1) ||
                (rabbit1 == line + 1 && rabbit2 == column - 1)) {
            return true;
        }
        return false;
    }

    // Metoda move() modificată pentru a verifica doar mișcările pe diagonală
    public boolean moveBunny(int i) {
        if (diagonal(i))
        {
            s++;
            mazeGrid.setRabbit(new Point(i / mazeGrid.getLevelGame() + 1, i % mazeGrid.getLevelGame() + 1));
            if (mazeGrid.getRabbit().equals(mazeGrid.getCarrot())) {
                victory = true;
                switch (s) {
                    case 2:
                        score = 100;
                        break;
                    case 3:
                        score = 80;
                        break;
                    case 4:
                        score = 60;
                        break;
                    case 5:
                        score = 40;
                        break;
                    case 6:
                        score = 30;
                        break;
                    case 7:
                        score = 20;
                        break;
                    case 8:
                        score = 10;
                        break;
                    default:
                        score = 0;
                }
                return true;
            }
            for (Point trap : mazeGrid.getTrap()) {
                if (trap.equals(mazeGrid.getRabbit())) {
                    lose = true;
                    score = 0;
                    return true;
                }
            }
            return true;
        }
        return false;
    }
    public Boolean getWin() {
        return victory;
    }

    public void setWin(Boolean win) {
        this.victory = win;
    }

    public Boolean getFail() {
        return lose;
    }

    public void setFail(Boolean fail) {
        this.lose = fail;
    }
}