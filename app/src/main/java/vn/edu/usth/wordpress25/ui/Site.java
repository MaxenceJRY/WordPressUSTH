package vn.edu.usth.wordpress25.ui;

import java.util.ArrayList;

public class Site {
    private String Name;
    private ArrayList <users> FollowersList;

    public Site(String Name,ArrayList FollowersList) {
        this.Name = Name;
        this.FollowersList = FollowersList;
    }

    public String getName() {
        return Name;
    }

    public ArrayList getFollowersList() {
        return FollowersList;
    }
}
