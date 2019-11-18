package model;

public class Movie {


    private int id;
    private String title;
    private String summary;
    private String genre;
    private String length;
    private int rating;

    public Movie() {
    }

    public Movie(
            int id, String title, String summary, String genre, int rating, String length) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.genre = genre;
        this.length = length;
        this.rating = rating;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() { return summary; }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


}