package gui;

import javax.swing.*;
import java.net.URL;

/**
 * Created by Oro on 8/7/2015.
 */
public class Utils {

    public static String getFileExtension(String name){
        int pointIndex = name.lastIndexOf(".");
        if(pointIndex == -1){
            return null;
        }

        if(pointIndex == name.length() -1){
            return null;
        }

        return name.substring(pointIndex+1, name.length());


    }

    public static ImageIcon createIcon(String path){
        URL url = System.class.getResource(path);

        if(url == null){
            System.out.println("Unable to load image.");
        }
        return new ImageIcon(url);
    }



}
