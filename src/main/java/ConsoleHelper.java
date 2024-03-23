import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void write(String text) {
        System.out.println(text);
    }

    public static String readString() {
        try {
            return  reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
