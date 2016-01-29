package com.trg.testframework.trgsites.propertyObjects;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LandingObjValues {
    private static Properties prop = new Properties();

    static {

        InputStream input = LandingObjValues.class.getClassLoader()
                .getResourceAsStream("landing.properties");
        try {
            prop.load(input);
        } catch (IOException e) {

            System.err.println(e);
        }
    }

    public static String getElement(String key) {

        return prop.getProperty(key);

    }
}
