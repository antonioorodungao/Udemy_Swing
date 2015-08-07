package model;

/**
 * Created by Oro on 8/7/2015.
 */
public class Person {

    private static int count = 0;
    private int id;
    private String name;
    private String occupation;
    private AgeCategory agecat;
    private EmploymentCategory empcat;
    private boolean usCitizen;
    private String taxID;
    private Gender gender;

    public Person(String name, String occupation, AgeCategory agecat, EmploymentCategory empcat,
                  boolean usCitizen, String taxID, Gender gender){
        this.name = name;
        this.occupation = occupation;
        this.agecat= agecat;
        this.empcat = empcat;
        this.usCitizen = usCitizen;
        this.taxID = taxID;
        this.gender = gender;
        this.id = count;
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public AgeCategory getAgecat() {
        return agecat;
    }

    public void setAgecat(AgeCategory agecat) {
        this.agecat = agecat;
    }

    public EmploymentCategory getEmpcat() {
        return empcat;
    }

    public void setEmpcat(EmploymentCategory empcat) {
        this.empcat = empcat;
    }

    public boolean isUsCitizen() {
        return usCitizen;
    }

    public void setUsCitizen(boolean usCitizen) {
        this.usCitizen = usCitizen;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
