package utilities;
import java.io.FileInputStream;
import java.util.Properties;
public class ConfigurationReader {
    private static Properties configurations;

    static {
        try {
            String path = "configuration.properties";
            FileInputStream inputStream = new FileInputStream(path);

            configurations = new Properties();
            configurations.load(inputStream);

            inputStream.close();
        } catch (Exception e) {
            System.out.println("Exception caught while reading configurations: " + e.getMessage());
        }
    }

    public static String getProperty(String keyName) {
        return configurations.getProperty(keyName);
    }

}
