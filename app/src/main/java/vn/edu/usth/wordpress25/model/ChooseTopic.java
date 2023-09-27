package vn.edu.usth.wordpress25.model;

public class ChooseTopic {

    public int id;
    public int srcImage;
    public String name;
    public ChooseTopic(int srcImage, String name) {
        this.srcImage = srcImage;
        this.name = name;
    }

    public ChooseTopic(int id, int srcImage, String name) {
        this.id = id;
        this.srcImage = srcImage;
        this.name = name;
    }
}
