import java.sql.SQLException;

public class Controller {
    private MemeDB db = new MemeDB();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MemeDB memeDB = new MemeDB();
        memeDB.connect();
        memeDB.createTable("text", TableType.TEXT);
    }

}
