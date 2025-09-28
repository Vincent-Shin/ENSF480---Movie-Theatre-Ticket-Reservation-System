import java.time.LocalDateTime;

public class Showing {
    private String showingID;
    private String movieTitle;
    private String theatreName;
    private int roomNum;
    private String showingTime;
    private String seatMapID;

    public Showing (String showingID, String movieTitle, String theatreName, int roomNum, String showingTime,
            String seatMapID){
                this.showingID = showingID;
                this.movieTitle = movieTitle;
                this.theatreName = theatreName;
                this.roomNum = roomNum;
                this.showingTime = showingTime;
                this.seatMapID = seatMapID;
    }

    public String getShowingID(){
        return showingID;
    }

    public String getMovieTitle(){
        return movieTitle;
    }

    public String getTheatreName(){
        return theatreName;
    }

    public int getRoomNum(){
        return roomNum;
    }

    public String getShowingTime(){
        return showingTime;
    }

    public String getSeatMapID(){
        return seatMapID;
    }
}
