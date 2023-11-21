package utilities;

import groovy.beans.PropertyReader;

import java.io.*;
import java.util.Properties;

public class ReadPropertyFile {
    public static String getPropertyValue(String key) throws IOException{
        Properties prop = new Properties();
        InputStream input = PropertyReader.class.getResourceAsStream("/config.properties");
        prop.load(input);
        return prop.getProperty(key);

    }

}
