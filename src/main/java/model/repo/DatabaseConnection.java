package model.repo;
import javax.swing.*;
import java.sql.*;
public class DatabaseConnection {
    private Connection databaseC;
    public DatabaseConnection() {
        this.databaseC = null;
        openDatabaseConnection();
    }
    public void openDatabaseConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/pstema1labirint";
            String username = "root";
            String password = "10pojarandrei#";

            this.databaseC = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return this.databaseC;
    }
    public void closeConnection() {
        try {
            if (this.databaseC != null && !this.databaseC.isClosed()) {
                this.databaseC.close();
                System.out.println("Connection closed successfully !!");
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error closing the database connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public ResultSet getTable(String commandSQL) {
        try {
            Statement statement = this.databaseC.createStatement();
            return statement.executeQuery(commandSQL);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL query: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public boolean executeSQL(String commandSQL) {
        try {
            Statement statement = this.databaseC.createStatement();
            statement.executeUpdate(commandSQL);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error executing SQL command: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}