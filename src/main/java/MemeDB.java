import java.sql.*;

public class MemeDB {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MemeDB memeDB = new MemeDB();
        memeDB.connect();
    }

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("JDBC:sqlite:storage.s3db");
    }
}
