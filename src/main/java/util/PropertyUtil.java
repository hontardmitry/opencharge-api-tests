package util;

import javax.naming.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyUtil {

    private PropertyUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ENVIRONMENT = System.getProperty("env", "prod");

    public static String getProperty(String key) {
        try {
            return loadProperties().getProperty(key);
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key, String defaultValue) {
        try {
            return loadProperties().getProperty(key, defaultValue);
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties loadProperties() throws ConfigurationException {
        Properties props = new Properties();
        var path = String.format("config/%s.properties", ENVIRONMENT);
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            if (input == null) throw new ConfigurationException(String.format("Missing %s", path));
            props.load(input);
        } catch (IOException | ConfigurationException e) {
            throw new ConfigurationException(String.format("Failed to load %s", path));
        }
        return props;
    }
}
