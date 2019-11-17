package model;

import java.sql.*;

public class MovieService {

    private int id;
    private String title;
    private String summary;
    private String genre;
    private int rating;
    private int length;
    private String producer;
    private String director;
    private int search;

    public Movie getMovie(String title) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("Select * from movie WHERE search='" + title + "'");
        while (resultSet.next()) {
            id = resultSet.getInt(1);
            //title = resultSet.getString(2);
            summary = resultSet.getString(3);
            genre = resultSet.getString(4);
            rating = resultSet.getInt(5);
            length = resultSet.getInt(6);
            producer = resultSet.getString(7);
            director = resultSet.getString(8);
            search = resultSet.getInt(9);
        }
        return new Movie(id, title, summary, genre, rating, length, producer, director, search);
    }
}
