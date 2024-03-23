import java.sql.SQLException;

public class Controller {
    private TextDB db = new TextDB();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Controller controller = new Controller();
        controller.run();
    }

    public void run() {
        db.connect();
        String input;
        while (!(input = ConsoleHelper.readString()).equals("exit")) {
            String[] strings = input.split(" ");
            switch (strings[0]) {
                case "create" -> db.createTable(strings[1]);
                case "add" -> db.addRecord(strings[1], strings[2]);
                case "print" -> {
                    if (strings.length > 2)
                        db.print(strings[1], Integer.parseInt(strings[2]));
                    else
                        db.print(strings[1]);
                }
                case "tables" -> db.printTables();
                case "drop" -> {
                    if (!db.isTableExist(strings[1])) {
                         ConsoleHelper.write("Table is not exist");
                       break;
                    }

                    ConsoleHelper.write("Are you sure you want to delete table " + strings[1] + "? Print Yes/No");
                    String answer = ConsoleHelper.readString();
                    if (answer.equalsIgnoreCase("yes"))
                        db.deleteTable(strings[1]);
                    else
                        ConsoleHelper.write("Operation canceled");
                }
                case "delete" -> db.deleteRecord(strings[1], Integer.parseInt(strings[2]));
                case "help" -> ConsoleHelper.write("""
                        create <table name> - creates a table
                        add <table name> <string> - adds a record in a table
                        print <table name> - shows all records
                        print <table name> <id> - shows record by id
                        tables - shows all tables
                        drop <tables name> - deletes a table
                        help - shows this
                        """);
                default -> ConsoleHelper.write("Unknown command");
            }
        }
    }
}
