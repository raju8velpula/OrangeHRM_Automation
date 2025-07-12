package com.winUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GenericUtility {
    public static String readProperty(String proprtyName) throws IOException {
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream("./src/test/resources/config/config.properties");
        properties.load(input);
        String propValue = properties.getProperty(proprtyName);
        return  propValue;
    }
}
