package presenter;

import model.MazeGame;
import model.MazeGrid;
import model.User;
import model.repo.DatabaseConnection;
import model.repo.UserRepo;
import view.interf.MazeGameInterface;
public class MazeGamePresenter {
    private UserRepo userRepo;
    private MazeGameInterface mazeGameInterface;
    private MazeGame mazeGame;

    public MazeGamePresenter(MazeGameInterface mazeGameInterface, User user, Integer level) {
        mazeGame = new MazeGame(user, new MazeGrid(level));
        this.mazeGameInterface = mazeGameInterface;
    }

    public MazeGame getMazeGame() {
        return mazeGame;
    }

    public void setMazeGame(MazeGame mazeGame) {
        this.mazeGame = mazeGame;
    }

    //mutari interzise sau mutari dupa ce jocul s-a terminat
    public void bunnyPlay(int i) {
        if(mazeGame.getWin()||mazeGame.getFail()){
            mazeGameInterface.setMessage("Sorry, but you can't move, Game over!!");
        }
        else{
            if(!mazeGame.moveBunny(i)){
                mazeGameInterface.setMessage("You can't move to that position !!");
            }
        }
    }

    //scorul pt utilizator logat sau nu
    private void score() {
        User user;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            userRepo = new UserRepo(databaseConnection);
            if(mazeGame.getUser()==null){
                mazeGameInterface.setMessage("You need to log in to see the score !!");
            }else{
                user = mazeGame.getUser();
                user.setScore(mazeGame.getUser().getScore()+mazeGame.getScore());
                userRepo.updatePlayer(user);
            }
        } finally
        {
            databaseConnection.closeConnection();
        }
    }
    //mesaje afisate dupa victorie/esec
    public void winOrLoseGame(){
        if(mazeGame.getWin()){
            mazeGameInterface.setMessage("Moves= "+ String.valueOf(mazeGame.getScore())+" , " +
                    "Score = "+
                    String.valueOf(mazeGame.getScore()));
            score();
        }
        else{
            if(mazeGame.getFail()){
                mazeGameInterface.setMessage("Game over !!");
                score();
            }
        }
    }
}