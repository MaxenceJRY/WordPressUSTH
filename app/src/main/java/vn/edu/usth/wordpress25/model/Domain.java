package vn.edu.usth.wordpress25.model;

public class Domain {

    public int id;
    public String name;
    public boolean isCheck = false;

    public Domain(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
