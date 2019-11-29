package model;

public class Payment {
    String cardNo;
    int userID;
    int exp;
    int cvv;
    String address;
    String state;
    String zipcode;
    String country;
    int defPay;

    public Payment(){

    }
    public Payment(String cardNo, int userID, int exp, int cvv, String address, String state, String zipcode, String country, int defPay) {
        this.cardNo = cardNo;
        this.userID = userID;
        this.exp = exp;
        this.cvv = cvv;
        this.address = address;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.defPay = defPay;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDefPay() {
        return defPay;
    }

    public void setDefPay(int defPay) {
        this.defPay = defPay;
    }
}


