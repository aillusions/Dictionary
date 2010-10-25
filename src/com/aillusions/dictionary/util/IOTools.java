package com.aillusions.dictionary.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOTools {

    public static String readStreamAsString(InputStream is)
    throws java.io.IOException{
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }
}
