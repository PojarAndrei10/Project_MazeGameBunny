package presenter;
import model.User;
import model.repo.DatabaseConnection;
import model.repo.UserRepo;
import view.graphicalInterfaces.AdminPageView;
import view.graphicalInterfaces.MazeGameView;
import view.interf.HomePageInterface;
public class HomePagePresenter {
    private UserRepo userRepo;
    private User user;
    private HomePageInterface homePageInterface;

    public HomePagePresenter(HomePageInterface homePageInterface) {
        this.homePageInterface = homePageInterface;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            userRepo = new UserRepo(databaseConnection);
        } finally
        {
            databaseConnection.closeConnection();
        }
    }
    private User searchUser(String username, String password) {
        return userRepo.searchPlayerByUsernameAndPassword(username, password);
    }

    //logare
    public boolean playerLogin() {
        String p = homePageInterface.getPassword();
        String u = homePageInterface.getUsername();
        user = searchUser(u, p);
        if (user != null)
            return true;
        homePageInterface.setMessage("Incorrect username or password !!");
        return false;
    }
    public User getUser() {
        return user;
    }
    public void adminView() {
        AdminPageView adminPageView = new AdminPageView();
    }
    public void mazeBunnyView(Integer level) {
        MazeGameView mazeGameView = new MazeGameView(user,level);
    }
}
