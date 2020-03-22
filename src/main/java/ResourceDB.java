import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceDB {
    String url;
    String login;
    String password;

    public ResourceDB() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("util.txt");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage(), e);
        }
        url = properties.getProperty("db.url");
        login = properties.getProperty("db.login");
        password = properties.getProperty("db.password");
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
