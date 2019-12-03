package model;

import java.sql.*;
import java.util.ArrayList;

public class MovieService extends Movie{
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
    private int available;

    public Movie getByTitle(String title) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select * from movie WHERE title='"+title+"'");
        while(resultSet.next()) {
            id = resultSet.getInt(6);
            title = resultSet.getString(1);
            summary = resultSet.getString(2);
            genre = resultSet.getString(3);
            rating = resultSet.getString(4);
            length = resultSet.getString(5);
            cast = resultSet.getString(7);
            director = resultSet.getString(8);
            producer = resultSet.getString(9);
            review1 = resultSet.getString(10);
            review2 = resultSet.getString(11);
            review3 = resultSet.getString(12);
            link = resultSet.getString(13);
            available = resultSet.getInt(14);
        }
        return new Movie(id, title, summary, genre, rating, length, cast, director, producer, review1, review2, review3, link, available);

    }

    public ArrayList<Movie> getAllMovies() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from movie");
        ArrayList<Movie> movies = new ArrayList<>();
        while(rs.next()) {
            movies.add(new Movie(rs.getInt(6), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14)));
        }
        return movies;
    }
    public int getNumMovies() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select COUNT(*) from movie");
        int num = 0;
        while(resultSet.next()){
            num = resultSet.getInt(1);
        }
        return num;
    }
    public boolean exists(String title) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select COUNT(*) from movie where title='"+title+"'");
        return !resultSet.next();
    }
}
