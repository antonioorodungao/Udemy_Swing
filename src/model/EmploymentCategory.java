package model;

/**
 * Created by Oro on 8/7/2015.
 */
public enum EmploymentCategory {
    Unemployed,
    Selfemployed,
    Employed;

    public static EmploymentCategory valueOf(int i){
        switch (i){
            case 0:
                return Unemployed;
            case 1:
                return Selfemployed;
            case 2:
                return Employed;
            default:
                return Unemployed;
        }
    }
}
