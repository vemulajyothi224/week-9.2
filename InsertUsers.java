import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertUsers {
    // Update these values to match your MySQL configuration.
    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        String createTableSQL =
                "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "email VARCHAR(150) NOT NULL)";

        String insertSQL =
                "INSERT INTO users (name, email) VALUES (?, ?)";

        try (Connection connection =
                     DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement createStatement = connection.createStatement()) {

            createStatement.executeUpdate(createTableSQL);

            try (PreparedStatement insertStatement =
                         connection.prepareStatement(insertSQL)) {

                insertStatement.setString(1, "John Doe");
                insertStatement.setString(2, "john@example.com");
                insertStatement.executeUpdate();

                insertStatement.setString(1, "Jane Doe");
                insertStatement.setString(2, "jane@example.com");
                insertStatement.executeUpdate();
            }

            System.out.println("Data inserted successfully.");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
