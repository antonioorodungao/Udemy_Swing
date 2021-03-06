package controller;

import gui.FormEvent;
import model.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Oro on 8/8/2015.
 */
public class Controller {
    Database db = new Database();
    public Controller(){

    }

    public List<Person> getPeople(){
        return Collections.unmodifiableList(db.getPeople());
    }

    public void deletePerson(int row){
        db.deletePerson(row);
    }

    public void addPerson(FormEvent e){
        String name = e.getName();
        String occupation = e.getOccupation();
        int agecat = e.getAgecat();
        int empcat = e.getEmpcat();
        boolean isUSCitizen = e.isUsCitizen();
        String taxID = e.getTaxID();
        String gen = e.getGender();

        AgeCategory ageCategory = null;
        switch (agecat){
            case 0:
                ageCategory = AgeCategory.child;
                break;
            case 1:
                ageCategory = AgeCategory.adult;
                break;
            case 2:
                ageCategory = AgeCategory.senior;
                break;
        }

        EmploymentCategory employmentCategory = null;
        switch(empcat){
            case 0:
                employmentCategory = EmploymentCategory.Employed;
                break;
            case 1:
                employmentCategory = EmploymentCategory.Selfemployed;
                break;
            case 2:
                employmentCategory = EmploymentCategory.Unemployed;
                break;
        }

        Gender gender = null;
        if(gen.equals(Gender.Female.toString())){
            gender = Gender.Female;
        }else{
            gender = Gender.Male;
        }



        Person person = new Person(name, occupation, ageCategory, employmentCategory, isUSCitizen, taxID, gender);
        db.addPerson(person);

    }

    public void saveToFile(File file) throws IOException {
        db.saveToFile(file);

    }

    public void saveToDB(){
        try {
            db.connect();
            db.savePeopleToDB();
            db.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void loadFromDB(){
        try{
            db.connect();
            db.loadPeopleFromDB();
            db.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(File file) throws IOException{
        db.loadFromFile(file);
    }


}
