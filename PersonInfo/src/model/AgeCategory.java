package model;

/**
 * Created by Oro on 8/7/2015.
 */
public enum AgeCategory {
    child,adult,senior;

    public static AgeCategory valueOf(int i){
        switch(i){
            case 0:
                return child;
            case 1:
                return adult;
            case 2:
                return senior;
            default:
                return child;

        }
    }

}
