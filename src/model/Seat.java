package model;

public class Seat {
    int id;
    int showID;
    String status;
    int seatnb;

    public Seat(){

    }

    public Seat(int id, int showID, String status, int seatnb) {
        this.id = id;
        this.showID = showID;
        this.status = status;
        this.seatnb = seatnb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSeatnb() {
        return seatnb;
    }

    public void setSeatnb(int seatnb) {
        this.seatnb = seatnb;
    }
}
