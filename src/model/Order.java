package model;

public class Order {
    int id;
    int userID;
    double total;
    int showtimeID;
    int ccID;
    int promoID;
    int numAdult;
    int numKids;
    int numSenior;
    int numStudent;

    public Order(){

    }
    public Order(int id, int userID, double total, int showtimeID, int ccID, int promoID, int numAdult, int numKids, int numSenior, int numStudent) {
        this.id = id;
        this.userID = userID;
        this.total = total;
        this.showtimeID = showtimeID;
        this.ccID = ccID;
        this.promoID = promoID;
        this.numAdult = numAdult;
        this.numKids = numKids;
        this.numSenior = numSenior;
        this.numStudent = numStudent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getShowtimeID() {
        return showtimeID;
    }

    public void setShowtimeID(int showtimeID) {
        this.showtimeID = showtimeID;
    }

    public int getCcID() {
        return ccID;
    }

    public void setCcID(int ccID) {
        this.ccID = ccID;
    }

    public int getPromoID() {
        return promoID;
    }

    public void setPromoID(int promoID) {
        this.promoID = promoID;
    }

    public int getNumAdult() {
        return numAdult;
    }

    public void setNumAdult(int numAdult) {
        this.numAdult = numAdult;
    }

    public int getNumKids() {
        return numKids;
    }

    public void setNumKids(int numKids) {
        this.numKids = numKids;
    }

    public int getNumSenior() {
        return numSenior;
    }

    public void setNumSenior(int numSenior) {
        this.numSenior = numSenior;
    }

    public int getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(int numStudent) {
        this.numStudent = numStudent;
    }
}


