import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private static final String FILE_LOGGER_NAME = "StudentFileOutput.txt";

    static {
        File file = new File(FILE_LOGGER_NAME);
        try {
            while (true) {
                if (file.createNewFile()) break;
                else {
                    if (file.delete())
                        if (file.createNewFile()) break;
                }
            }
        } catch (IOException e) {
            System.out.print("IOException error");
        }
    }

    @Override
    public void log(String message) {
        try {
            FileWriter writer = new FileWriter(FILE_LOGGER_NAME, true);
            writer.append(message).append("\n");
            writer.close();
        } catch (IOException e) {
            System.out.print("IOException error");
        }
    }
}
