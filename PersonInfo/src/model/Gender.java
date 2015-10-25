package model;

/**
 * Created by Oro on 8/7/2015.
 */
public enum Gender {
    Male,
    Female;

    public String toString(){
        if(Male.equals("Male")){
            return "Male";
        }else{
            return "Female";
        }
    }

    public static Gender valueOf(int i){
        switch (i){
            case 0:
                return Male;
            case 1:
                return Female;
            default:
                return Male;
        }
    }

}
