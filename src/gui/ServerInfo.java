package gui;

/**
 * Created by WFA_ORO_BH on 10/24/2015.
 */
class ServerInfo{
    private int serverID;
    private String servername;
    private boolean isChecked;

    public ServerInfo(String name, int id, boolean isChecked){
        this.servername = name;
        this.serverID = id;
        this.isChecked = isChecked;
    }

    public String getServername(){
        return servername;
    }

    public String toString(){
        return serverID + ":" + servername;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }



}