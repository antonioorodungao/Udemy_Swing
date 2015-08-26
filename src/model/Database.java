package model;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Created by Oro on 8/7/2015.
 */
public class Database {
    private List<Person> people;
    private Connection conn;

    public Database() {
        people = new LinkedList<Person>();
        try {
            connect();
            loadPeopleFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPerson(Person p) {
        people.add(p);
    }

    public void deletePerson(int row) {
        Person deleted = people.get(row);
        try {
            deletePerson(deleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        people.remove(row);

    }

    public List<Person> getPeople() {
        return people;
    }

    public void connect() throws SQLException {
        if(conn != null){
            return;
        }
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "udemy", "udemy123");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() throws SQLException {
        if (conn != null) {
            conn.close();
        }

    }

    public void savePeopleToDB() {
        Iterator<Person> iterator = people.iterator();
        while (iterator.hasNext()) {
            Person p = iterator.next();
            try {
                if (personExists(p.getId())) {
                    updatePerson(p);
                } else {
                    insertPerson(p);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void loadPeopleFromDB() throws SQLException {
        people.clear();
        if(conn!=null){
            PreparedStatement ps = conn.prepareStatement("select pid,firstname, occupation" +
                    ",agecat, empcat, usCitizen, taxid, gender from person");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Person p = new Person();
                p.setId(rs.getInt("pid"));
                p.setName(rs.getString("firstname"));
                p.setAgecat(AgeCategory.valueOf(rs.getInt("agecat")));
                p.setEmpcat(EmploymentCategory.valueOf(rs.getInt("empcat")));
                p.setUsCitizen(rs.getBoolean("usCitizen"));
                p.setTaxID(rs.getString("taxid"));
                p.setGender(Gender.valueOf(rs.getInt("gender")));
                p.setOccupation(rs.getString("occupation"));
                addPerson(p);
            }
            rs.close();
            ps.close();
        }
    }

    public void insertPerson(Person p) throws SQLException {
        if (conn != null) {
            PreparedStatement ps = conn.prepareStatement("insert into person(firstname," +
                    "occupation, agecat,empcat, usCitizen, taxid , gender, pid) values(?,?,?,?,?,?,?,?)");
            ps.setString(1, p.getName());
            ps.setString(2, p.getOccupation());
            ps.setInt(3, p.getAgecat().ordinal());
            ps.setInt(4, p.getEmpcat().ordinal());
            ps.setBoolean(5, p.isUsCitizen());
            ps.setString(6, p.getTaxID());
            ps.setInt(7, p.getGender().ordinal());
            ps.setInt(8, p.getId());

            ps.executeUpdate();
            conn.commit();
            ps.close();
        }


    }

    public void updatePerson(Person p) throws SQLException {
        if (conn != null) {
            PreparedStatement ps = conn.prepareStatement("update person set firstname = ?," +
                    "occupation = ?, agecat=?, empcat=?, usCitizen=?, taxid =? , gender=? where pid=?");
            ps.setString(1, p.getName());
            ps.setString(2, p.getOccupation());
            ps.setInt(3, p.getAgecat().ordinal());
            ps.setInt(4, p.getEmpcat().ordinal());
            ps.setBoolean(5, p.isUsCitizen());
            ps.setString(6, p.getTaxID());
            ps.setInt(7, p.getGender().ordinal());
            ps.setInt(8, p.getId());
            ps.executeUpdate();
            conn.commit();
            ps.close();
        }

    }

    public void deletePerson(Person p) throws SQLException {
        if(conn != null){
            PreparedStatement ps = conn.prepareStatement("delete from person where pid=?");
            ps.setInt(1,p.getId());
            ps.executeUpdate();
            conn.commit();
            ps.close();
        }
    }

    public boolean personExists(int id) {
        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement("select count(*) from person where pid= ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getInt(1) > 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("no database connection");
        }
        return false;
    }

    public void saveToFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Person[] persons = people.toArray(new Person[people.size()]);
        oos.writeObject(persons);
        oos.close();
    }

    public void loadFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            Person[] persons = (Person[]) ois.readObject();
            people.clear();
            people.addAll(Arrays.asList(persons));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ois.close();
    }
}
