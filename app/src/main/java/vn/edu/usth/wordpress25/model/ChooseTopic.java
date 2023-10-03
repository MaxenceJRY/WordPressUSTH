package vn.edu.usth.wordpress25.model;

public class ChooseTopic {

    public int id;
    public int srcImage;
    public String name;
    public String website;

    public String title;

    public String content;

    public ChooseTopic(int srcImage, String name) {
        this.srcImage = srcImage;
        this.name = name;
    }

    public ChooseTopic(int id, int srcImage, String name, String content) {
        this.id = id;
        this.srcImage = srcImage;
        this.name = name;
        this.content = content;
    }

    public ChooseTopic(int id, int srcImage, String name, String website, String title, String content) {
        this.id = id;
        this.srcImage = srcImage;
        this.name = name;
        this.website = website;
        this.title = title;
        this.content = content;
    }
}
