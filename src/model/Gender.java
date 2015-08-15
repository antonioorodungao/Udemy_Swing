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

}
