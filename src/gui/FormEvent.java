package gui;

import java.util.EventObject;

/**
 * Created by Oro on 8/5/2015.
 */
public class FormEvent extends EventObject {

    private String name;
    private String occupation;
    private int agecat;
    private String empcat;
    private boolean usCitizen;
    private String taxID;
    private String gender;


    public FormEvent(Object source, String name, String occupation, int agecat,
                     String empcat, boolean usCitizen, String taxID, String gender) {
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.agecat = agecat;
        this.empcat = empcat;
        this.usCitizen = usCitizen;
        this.taxID = taxID;
        this.gender = gender;

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

    public int getAgecat() {
        return agecat;
    }

    public void setAgecat(int agecat) {
        this.agecat = agecat;
    }

    public String getEmpcat() {
        return empcat;
    }

    public void setEmpcat(String empcat) {
        this.empcat = empcat;
    }

    @Override
    public String toString() {
        return "gui.FormEvent{" +
                "name='" + name + '\'' +
                ", occupation='" + occupation + '\'' +
                ", agecat=" + agecat +
                ", empcat='" + empcat + '\'' +
                '}';
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
