package com.trg.testframework.trgsites.propertyObjects;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by calcey on 1/21/16.
 */
public class RedeemGiftObjValues {

    private static Properties prop = new Properties();

    static {

        InputStream input = RedeemGiftObjValues.class.getClassLoader()
                .getResourceAsStream("redeemGift.properties");
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
