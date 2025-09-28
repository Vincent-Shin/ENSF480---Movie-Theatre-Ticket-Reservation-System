import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Admin {
    private String email;
    private String username;
    private String password;
    private Date dateJoined;
    private DatabaseConnector dbConnector;

    public Admin (String username, String email, String password, DatabaseConnector dbConnector){
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateJoined = new Date();
        this.dbConnector = dbConnector;
    }

    public String getEmail(){
        return email;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    /*
    public boolean addMovie(String title, String director, int duration, String genres, Date releaseDate, boolean isEarlyRelease) {
        String query = "INSERT INTO Movies (MovieTitle, Duration, PublicReleaseDate, Director, Genres) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, duration);
            preparedStatement.setDate(3, new java.sql.Date(releaseDate.getTime()));
            preparedStatement.setString(4, director);
            preparedStatement.setString(5, genres);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0 && isEarlyRelease) {
                Movie movie = new Movie(title, duration, releaseDate, director, genres);
                RegisteredCustomer.addEarlyReleaseMovie(movie); 
                System.out.println("Movie added to early release: " + title);
            }
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/

    
}
