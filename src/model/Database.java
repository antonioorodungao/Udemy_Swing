package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oro on 8/7/2015.
 */
public class Database {
    private ArrayList<Person> people;

    public Database(){
        people = new ArrayList<Person>();
    }

    public void addPerson(Person p){
        people.add(p);
    }

    public List<Person> getPeople(){
        return people;
    }
}
