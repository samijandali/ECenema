package model;

import java.sql.*;

public class MovieService extends Movie{
    private int id;
    private String title;
    private String summary;
    private String genre;
    private String length;
    private int rating;

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
            length = resultSet.getString(5);
            rating = resultSet.getInt(4);
        }
        return new Movie(id, title, summary, genre, rating, length);

    }

    public Movie getID(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select * from movie WHERE id="+id+"");
        while(resultSet.next()) {
            id = resultSet.getInt(6);
            title = resultSet.getString(1);
            summary = resultSet.getString(2);
            genre = resultSet.getString(3);
            length = resultSet.getString(5);
            rating = resultSet.getInt(4);
        }
        return new Movie(id, title, summary, genre, rating, length);
    }
    public int getNumMovies() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select COUNT(*) from movie");
        int num = 0;
        while(resultSet.next()){
            num = resultSet.getInt(1);
        }
        return num;
    }
}
