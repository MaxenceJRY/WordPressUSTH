package vn.edu.usth.wordpress25.ui;

import java.util.ArrayList;

public class users {
    private String Mail;
    private String PassWord ;
    private String Name;
    private ArrayList <Site> FollowsList;
    public users(String Mail, String PassWord, String Name,ArrayList FolowersList) {
        this.Mail = Mail;
        this.PassWord = PassWord;
        this.Name = Name;
        this.FollowsList = FolowersList;
    }

    public String getMail() {
        return Mail;
    }

    public String getPassWord() {
        return PassWord;
    }

    public String getName() {
        return Name;
    }

    public ArrayList<Site> getFollowsList() {
        return FollowsList;
    }
}
