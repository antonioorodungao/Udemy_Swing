package gui;

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

        return name.substring(pointIndex, name.length());


    }
}
