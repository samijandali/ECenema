package model;

public class Showroom {
    private int id;
    private String name;
    private int nbOfSeats;

    public Showroom(){
    }

    public Showroom(int id, String name, int nbOfSeats){
        this.id = id;
        this.name = name;
        this.nbOfSeats = nbOfSeats;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNbOfSeats() {
        return nbOfSeats;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNbOfSeats(int nbOfSeats) {
        this.nbOfSeats = nbOfSeats;
    }
}
