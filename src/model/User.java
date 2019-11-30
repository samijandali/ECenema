package model;

public class User {

    private int id;
    private String username;
    private String password;
    private String fname;
    private String lname;
    private String email;
    private String address;
    private String state;
    private String zipcode;
    private String country;
    private String pnumber;
    private String gender;
    private String promo;
    private int admin;
    private String activity;
    private int suspended;

    private static User obj;

    public User() {
    }

    public static synchronized User getInstance(){
        if(obj == null)
            obj = new User();
        return obj;
    }

    public User(
            int id, String username, String password, String fname, String lname, String email, String address, String state, String zipcode,
            String country, String pnumber, String gender, String promo, int admin, String activity, int suspended) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.zipcode = zipcode;
        this.country = country;
        this.pnumber = pnumber;
        this.address = address;
        this.state = state;
        this.admin = admin;
        this.gender = gender;
        this.promo = promo;
        this.activity = activity;
        this.suspended = suspended;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String gender) {
        this.state = state;
    }

    public String getPNumber() {
        return pnumber;
    }

    public void setPNumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getSuspended() {
        return suspended;
    }

    public void setSuspended(int suspended) {
        this.suspended = suspended;
    }
}