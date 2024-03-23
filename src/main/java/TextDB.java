import java.sql.*;

public class TextDB {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("JDBC:sqlite:storage.s3db");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ConsoleHelper.write("DB was connected!");
    }

    public void createTable(String tableName) {
        createStatement();
        execute("CREATE TABLE IF NOT EXISTS " + tableName + " (id INTEGER PRIMARY KEY AUTOINCREMENT, data TEXT)");

        ConsoleHelper.write("Table was created successfully!");
    }

    public void addRecord(String tableName, String data) {
        createStatement();
        execute("INSERT INTO " + tableName + " (data) VALUES ('" + data + "')");
    }

    public void print(String tableName, int id) {
        createStatement();
        execute("SELECT * FROM " + tableName + " WHERE id = " + id);
        updateResultSet();

        String record;
        if((record = getRecord()) != null)
            ConsoleHelper.write(record);
        else
            ConsoleHelper.write("Table is empty");
    }

    public void print(String tableName) {
        createStatement();
        execute("SELECT * FROM " + tableName);
        updateResultSet();

        String record;
        while((record = getRecord()) != null)
            ConsoleHelper.write(record);
    }

    public void printTables() {
        createStatement();
        execute("SELECT name FROM sqlite_master WHERE type='table';");
        updateResultSet();
        try {
            while (resultSet.next()) {
                ConsoleHelper.write(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTable(String tableName) {
        createStatement();
        execute("DROP TABLE " + tableName);
    }

    public void deleteRecord(String tableName, int id) {
        createStatement();
        execute("DELETE FROM " + tableName + " WHERE id = " + id);
    }

    public boolean isTableExist(String tableName) {
        createStatement();
        execute("SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "';");
        updateResultSet();
        try {
            if(resultSet.next())
                return true;
        } catch (SQLException e) {
            System.out.println("ERROR4");
        }
        return false;
    }

    private String getRecord() {
        try {
            if(resultSet.next())
                return "id = " + resultSet.getInt("id") + " data = '" + resultSet.getString("data") + "'";
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("1ERROR!");
        }
    }

    private void execute(String command) {
        try {
            statement.execute(command);
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("2ERROR!");
        }
    }

    private void updateResultSet() {
        try {
            resultSet = statement.getResultSet();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("3ERROR!");
        }
    }
}
