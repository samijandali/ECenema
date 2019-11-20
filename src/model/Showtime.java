package model;

public class Showtime {
    int id;
    int dayID;
    int showroomID;
    int timeID;
    int movieID;

    public Showtime(){
    }

    public Showtime(int id, int dayID, int showroomID, int timeID, int movieID) {
        this.id = id;
        this.dayID = dayID;
        this.showroomID = showroomID;
        this.timeID = timeID;
        this.movieID = movieID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDayID() {
        return dayID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }

    public int getShowroomID() {
        return showroomID;
    }

    public void setShowroomID(int showroomID) {
        this.showroomID = showroomID;
    }

    public int getTimeID() {
        return timeID;
    }

    public void setTimeID(int timeID) {
        this.timeID = timeID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}

