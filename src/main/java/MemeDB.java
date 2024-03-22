import java.sql.*;

public class MemeDB {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("JDBC:sqlite:storage.s3db");
        ConsoleHelper.write("DB was connected!");
    }

    public void createTable(String tableName, TableType type) throws SQLException {
        if(type == TableType.TEXT) {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS " + tableName + " (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT)");
        }
        ConsoleHelper.write("Table was created successfully!");
    }
}
