import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AppData {
    private static final String APP_NAME = "CaddyTracker";
    private static final String DATA_FILE_NAME = "loops.txt";

    public static Path getDataFilePath() {
        String userHome = System.getProperty("user.home");

        Path appSupportDir = Path.of(
                userHome,
                "Library",
                "Application Support",
                APP_NAME
        );

        try {
            Files.createDirectories(appSupportDir);
        } catch (IOException e) {
            throw new RuntimeException("Could not create app data folder: " + appSupportDir, e);
        }

        return appSupportDir.resolve(DATA_FILE_NAME);
    }
}