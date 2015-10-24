package model;

/**
 * Created by WFA_ORO_BH on 10/24/2015.
 */
public class Message {

    String m1, m2;

    public Message(String m1, String m2){
        this.m1 = m1;
        this.m2 = m2;
    }

    public String getTitle(){
        return m1;
    }
}
