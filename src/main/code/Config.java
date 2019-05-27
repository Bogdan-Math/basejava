package main.code;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Config INSTANCE = new Config();

    private String pathName;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {
//        try (InputStream is = new FileInputStream("./config/resume.properties")) {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("./config/resume.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            pathName = properties.getProperty("path.name");
            dbUrl = properties.getProperty("db.url");
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getPathName() {
        return pathName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
