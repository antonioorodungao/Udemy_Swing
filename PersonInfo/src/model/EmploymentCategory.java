package model;

/**
 * Created by Oro on 8/7/2015.
 */
public enum EmploymentCategory {
    Unemployed("Unemployed"),
    Selfemployed("Self Employed"),
    Employed("Employed"),
    Other("Other");

    private String text;

    private EmploymentCategory(String text){
        this.text = text;

    }

    public String toString(){
        return text;
    }

    public static EmploymentCategory valueOf(int i){
        switch (i){
            case 0:
                return Unemployed;
            case 1:
                return Selfemployed;
            case 2:
                return Employed;
            case 3:
                return Other;
            default:
                return Other;
        }
    }
}
