package model;

public class Movie {


    private int id;
    private String title;
    private String summary;
    private String genre;
    private int rating;
    private int length;
    private String producer;
    private String director;
    private int search;


    public Movie() {
    }

    public Movie(int id, String title, String summary, String genre, int rating, int length, String producer, String director, int search) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.genre = genre;
        this.rating = rating;
        this.length = length;
        this.producer = producer;
        this.director = director;
        this.search = search;
    }

    public int getSearch() {
        return search;
    }

    public void setSearch(int search) {
        this.search = search;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
