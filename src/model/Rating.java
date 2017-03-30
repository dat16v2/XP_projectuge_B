package model;

public class Rating {
    private int id;
    private String name;

    public Rating() {

    }

    public Rating(Rating rating) {
        this.id = rating.id;
        this.name = rating.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}