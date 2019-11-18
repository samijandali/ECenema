package model;

public class Movie {


    private int id;
    private String title;
    private String summary;
    private String genre;
    private String length;
    private String rating;
    private String cast;
    private String director;
    private String producer;
    private String review1;
    private String review2;
    private String review3;
    private String link;


    public Movie() {
    }

    public Movie(
            int id, String title, String summary, String genre, String rating, String length,String cast,
             String director,
             String producer,
             String review1,
             String review2,
             String review3,
             String link) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.genre = genre;
        this.length = length;
        this.rating = rating;
        this.cast = cast;
        this.director = director;
        this.producer = producer;
        this.review1 = review1;
        this.review2 = review2;
        this.review3 = review3;
        this.link = link;
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

    public String getRating() {
        return rating;
    }


    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getReview1() {
        return review1;
    }

    public void setReview1(String review1) {
        this.review1 = review1;
    }

    public String getReview2() {
        return review2;
    }

    public void setReview2(String review2) {
        this.review2 = review2;
    }

    public String getReview3() {
        return review3;
    }

    public void setReview3(String review3) {
        this.review3 = review3;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}