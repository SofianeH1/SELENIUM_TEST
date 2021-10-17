package com.hightest.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Read config.properties and get data :
 *  -User name
 *  -password
 *  we can add others information like language ...
 */
public class GetPropertiesVelues {

    String pathFile;
    String result = "";
    InputStream inputStream;

    public GetPropertiesVelues(String pathFile){
        this.pathFile=pathFile;
    }


    public String getPropertyValue(String key) throws IOException {

        try{
            Properties properties = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(pathFile);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + pathFile + "' not found in the classpath");
            }
            result = properties.getProperty(key);
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }finally {
            inputStream.close();
        }
        return result;
    }
}
