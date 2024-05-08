package model.repo;

import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private DatabaseConnection databaseConnection;
    public UserRepo(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    public boolean addPlayer(User user) {
        System.out.println("aici");
        String sql = String.format("INSERT INTO Player (legalName, isAdmin, username, password, score) " +
                        "VALUES ('%s', %d, '%s', '%s', '%d')",
                user.getLegalName(), user.isAdmin() ? 1 : 0, user.getUsername(), user.getPassword(), user.getScore());
        try {
            databaseConnection.openDatabaseConnection();
            return databaseConnection.executeSQL(sql);
        }
        finally {
            databaseConnection.closeConnection();
        }
    }
    public boolean updatePlayer(User user) {
        String sql = "UPDATE Player SET isAdmin = ?, username = ?, password = ?, score = ? WHERE legalName = ?";
        try {
            databaseConnection.openDatabaseConnection();
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, user.isAdmin() ? 1 : 0);
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getScore());
            statement.setString(5, user.getLegalName());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            databaseConnection.closeConnection();
        }
    }
    public boolean deletePlayer(String legalName) {
        String sql = "DELETE FROM Player WHERE legalName = ?";
        try {
            databaseConnection.openDatabaseConnection();
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(sql);
            statement.setString(1, legalName);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            databaseConnection.closeConnection();
        }
    }
    public List<User> allPlayerList() {
        List<User> players = new ArrayList<>();
        try {
            ResultSet resultSet = gamerTable();
            while (resultSet.next()) {
                players.add(playerBunny(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
    public ResultSet gamerTable() {
        String sql = "SELECT * FROM Player";
        databaseConnection.openDatabaseConnection();
        return databaseConnection.getTable(sql);
    }
    public ResultSet getUsernameAndPasswordTable() {
        String sql = "SELECT username, password FROM Player";
        try {
            databaseConnection.openDatabaseConnection();
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(sql);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<User> getUsernameAndPasswordList() {
        List<User> players = new ArrayList<>();
        try {
            ResultSet rs = getUsernameAndPasswordTable();
            while (rs.next()) {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                players.add(new User(0,"0",false,username, password,0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
    public User searchPlayerByUsernameAndPassword(String username, String password) {
        String sql = String.format("SELECT * FROM Player WHERE username = '%s' AND password = '%s'",
                username, password);
        try {
            databaseConnection.openDatabaseConnection();
            ResultSet resultSet = databaseConnection.getTable(sql);
            if (resultSet.next()) {
                return playerBunny(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.closeConnection();
        }
        return null;
    }
    private User playerBunny(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("idPlayer");
        String legalName = resultSet.getString("legalName");
        boolean isAdmin = resultSet.getInt("isAdmin") == 1;
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        int totalScore = resultSet.getInt("score");
        return new User(id, legalName,isAdmin, username, password, totalScore);
    }
}