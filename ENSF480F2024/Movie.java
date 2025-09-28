import java.util.Date;

public class Movie {
    private String title;
    private int duration;
    private String releaseDate;
    private String director;
    private String genres;


    public Movie(String title, int duration, String releaseDate, String director, String genres)
    {
        this.title  = new String(title);
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.director = new String(director);
        this.genres = new String (genres);

     }
    public String getTitle (){
        return title;
    }
    public int getDuration(){
        return duration;
    }
    public String getDirector(){
        return director;
    }
    public String getGenres(){
        return genres;
    }
    public String getRelease(){
        return releaseDate;
    }
}
