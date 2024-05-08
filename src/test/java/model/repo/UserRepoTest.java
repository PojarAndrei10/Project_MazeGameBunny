package model.repo;

import model.User;
import model.repo.DatabaseConnection;
import model.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepoTest {

    private DatabaseConnection databaseConnection;
    private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        // Initialize the database connection
        databaseConnection = new DatabaseConnection();
        // Initialize the user repository with the database connection
        userRepo = new UserRepo(databaseConnection);
    }

    @Test
    void addPlayer() throws SQLException {
        User user = new User(1, "John Doe", false, "john", "password", 100);
        assertTrue(userRepo.addPlayer(user));
    }

    @Test
    void updatePlayer() throws SQLException {
        User user = new User(1, "John Doe", false, "john", "password", 100);
        assertTrue(userRepo.updatePlayer(user));
    }

    @Test
    void deletePlayer() throws SQLException {
        assertTrue(userRepo.deletePlayer("John Doe"));
    }

    @Test
    void allPlayerList() {
        List<User> players = userRepo.allPlayerList();
        assertNotNull(players);
        // You can add further assertions based on your expectations
    }

    @Test
    void getUsernameAndPasswordList() {
        List<User> users = userRepo.getUsernameAndPasswordList();
        assertNotNull(users);
        // You can add further assertions based on your expectations
    }
}
