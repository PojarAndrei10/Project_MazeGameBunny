package presenter;

import model.User;
import model.repo.DatabaseConnection;
import model.repo.UserRepo;
import view.interf.AdminInterface;
import java.util.List;
import java.util.stream.Collectors;

public class AdministratorPresenter {
    private UserRepo userRepo;
    private AdminInterface adminInterface;
    public AdministratorPresenter(AdminInterface adminInterface) {
        this.adminInterface = adminInterface;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            userRepo = new UserRepo(databaseConnection);
        }
        finally
        {
            databaseConnection.closeConnection();
        }
    }
    private User userForAdmin() {
        int id = 0;
        String name = adminInterface.getLegalName();
        boolean trueOrFalseAdmin = Boolean.parseBoolean(adminInterface.getisAdmin());
        String username = adminInterface.getUsername();
        String password = adminInterface.getPassword();
        return new User(id, name, trueOrFalseAdmin, username, password, 0);
    }
    public boolean create() {
        User newUser;
        newUser = userForAdmin();
        return userRepo.addPlayer(newUser);
    }
    public boolean update() {
        User updatedUser;
        updatedUser = userForAdmin();
        return userRepo.updatePlayer(updatedUser);
    }
    public boolean delete(String legalName) {
        return userRepo.deletePlayer(legalName);
    }

    //toti playerii
    public List<String> allPlayers() {
        return userRepo.allPlayerList().stream()
                .map(User::toString2)
                .collect(Collectors.toList());
    }
}
