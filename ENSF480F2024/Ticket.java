import java.util.Date;

public class Ticket {
    private String seatID;
    private String showingTime;
    private String movieTitle;
    private float price;
    private String ticketID;
    private String showingID;
    private String receiptID;
    private String theatreName;


    public Ticket (String ticketID, String movieTitle, String theatreName, String seatID,
    String showingTime, String showingID, float price, String receiptID){
        this.seatID = seatID;
        this.showingTime = showingTime;
        this.theatreName = theatreName;
        this.movieTitle = movieTitle;
        this.price = price;
        this.ticketID = ticketID;
        this.showingID = showingID;
        this.receiptID = receiptID;
    }
    
    public String getTicketID(){
        return ticketID;
    }

    public String getSeatID(){
        return seatID;
    }
    public String getShowingID(){
        return showingID;
    }
    public String getMovieTitle(){
        return movieTitle;
    }
    public float getPrice(){
        return price;
    }
    public String getShowingTime(){
        return showingTime;
    }

    public String getReceiptID(){
        return receiptID;
    }

    public String printTicket(){

        StringBuilder ticketText = new StringBuilder(
            "Thank you for purchasing a ticket with AcmePlex!\nHere is your ticket info:");

        ticketText.append("\nTicketID: " + ticketID);
        ticketText.append("\nMovie: "+ movieTitle);
        ticketText.append("\nTheatre: "+ theatreName);
        ticketText.append("\nShowing: "+ showingTime);
        ticketText.append("\nSeat Number:"+ seatID.charAt(4)+seatID.charAt(5));

        ticketText.append("\nTickets can be cancelled only up to 72 hours prior to the show.");

        return ticketText.toString();
    }

}

