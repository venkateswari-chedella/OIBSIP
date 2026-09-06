import java.sql.*;

public class Database {

    private static final String URL = "jdbc:sqlite:reservation.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase() {

        String usersTable = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT UNIQUE NOT NULL,
                password TEXT NOT NULL
            )
            """;

        String trainsTable = """
            CREATE TABLE IF NOT EXISTS trains (
                train_number INTEGER PRIMARY KEY,
                train_name TEXT NOT NULL
            )
            """;

        String reservationsTable = """
            CREATE TABLE IF NOT EXISTS reservations (
                pnr TEXT PRIMARY KEY,
                passenger_name TEXT NOT NULL,
                train_number INTEGER NOT NULL,
                train_name TEXT NOT NULL,
                class_type TEXT NOT NULL,
                journey_date TEXT NOT NULL,
                source TEXT NOT NULL,
                destination TEXT NOT NULL
            )
            """;

        try (Connection con = connect();
             Statement st = con.createStatement()) {

            st.execute(usersTable);
            st.execute(trainsTable);
            st.execute(reservationsTable);

            st.executeUpdate("""
                INSERT OR IGNORE INTO users(username, password)
                VALUES ('admin', 'admin123')
            """);

            st.executeUpdate("""
                INSERT OR IGNORE INTO trains(train_number, train_name)
                VALUES (12701, 'Hussain Sagar Express')
            """);

            st.executeUpdate("""
                INSERT OR IGNORE INTO trains(train_number, train_name)
                VALUES (12723, 'Telangana Express')
            """);

            st.executeUpdate("""
                INSERT OR IGNORE INTO trains(train_number, train_name)
                VALUES (12861, 'Visakhapatnam Express')
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}