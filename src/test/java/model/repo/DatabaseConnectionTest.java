package model.repo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    private DatabaseConnection databaseConnection;

    @BeforeEach
    void setUp() {
        databaseConnection = new DatabaseConnection();
    }

    @AfterEach
    void tearDown() {
        databaseConnection.closeConnection();
    }

    @Test
    void openDatabaseConnection() {
        assertNotNull(databaseConnection.getConnection(), "Connection should not be null after opening.");
    }

    @Test
    void getConnection() {
        Connection connection = databaseConnection.getConnection();
        assertNotNull(connection, "Connection should not be null.");
        try {
            assertFalse(connection.isClosed(), "Connection should be open.");
        } catch (SQLException e) {
            fail("SQLException thrown: " + e.getMessage());
        }
    }

    @Test
    void closeConnection() {
        databaseConnection.closeConnection();
        try {
            assertTrue(databaseConnection.getConnection().isClosed(), "Connection should be closed after calling closeConnection.");
        } catch (SQLException e) {
            fail("SQLException thrown: " + e.getMessage());
        }
    }
}
