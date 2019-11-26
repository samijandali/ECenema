package model;

public class Promotion {


    private int id;
    private String name;
    private int discount;
    private String exp;
    private String description;


    public Promotion() {
    }

    public Promotion(
            int id, String name, int discount, String exp, String description) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.exp = exp;
        this.description = description;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}