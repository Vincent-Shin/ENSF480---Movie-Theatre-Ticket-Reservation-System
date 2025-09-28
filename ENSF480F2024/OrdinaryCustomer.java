import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class OrdinaryCustomer implements Customer{

    private String username;
    private String email;
    private String password;
    protected ArrayList<Ticket> tickets;
    static DatabaseConnector dbConnector;

    public OrdinaryCustomer (String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.tickets = new ArrayList<>();
    }

    public OrdinaryCustomer (String username, String email, String password, DatabaseConnector appDB){
        this.username = username;
        this.email = email;
        this.password = password;
        this.tickets = new ArrayList<>();
        this.dbConnector = appDB;
    }

    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }

    public int IsRegisteredUser(){
        return 0;
    }
/* 
    public void bookTicket(String movieTitle, Seat seat, String showingTime, Movie movie, float price, String showtimeTime) {

        if (dbConnector != null) {
            
            String seatCheckQuery = "SELECT isAvailable FROM Seats WHERE SeatID = ?;";

            try (PreparedStatement seatCheckStmt = dbConnector.getConnection().prepareStatement(seatCheckQuery)){
                seatCheckStmt.setString(1, seat.getSeatId());
                ResultSet rs = seatCheckStmt.executeQuery();
                if (rs.next() && rs.getBoolean("isAvailable")) {
                    dbConnector.updateSeatAvailability(seat.getSeatId(), false);
                    // Generate a ticket
                    String ticketId = "TICKET" + System.currentTimeMillis();
                    // Create a new Ticket instance
                    Ticket ticket = new Ticket(ticketId, movieTitle, seat, price, ticketId, showtimeTime);
                    tickets.add(ticket);
                    System.out.println("Seat booked successfully for movie: " + movieTitle);
                } else {
                    System.out.println("Seat is not available for reservation.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Trying to book a ticket failed because the app is not connected to the database.");
        }
        
    }

    @Override
    public void bookTicket(String movieTitle, String seatId, String showtime) {
        System.out.println("Booking ticket for movie: " + movieTitle + " at seat ID: " + seatId + " for showtime: " + showtime);
    }
    
    public void cancelTicket(String ticketId) {
        // Find the ticket by ticketId
        Ticket ticketToCancel = null;
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId().equals(ticketId)) {
                ticketToCancel = ticket;
                break;
            }
        }

        if (ticketToCancel != null) {
            Date currentDate = new Date();
            long diffInMillis = ticketToCancel.getShowtimeDate().getTime() - currentDate.getTime();
            long hoursDifference = diffInMillis / (60 * 60 * 1000); 

            if (hoursDifference <= 72) {
                double refundAmount = ticketToCancel.getPrice() * 0.85; 
                System.out.println("Ticket cancelled. A 15% cancellation fee will be applied.");
                System.out.println("Refund amount: $" + refundAmount);

            } else {
                System.out.println("Ticket can be cancelled without a fee.");

            }

            System.out.println("Ticket " + ticketId + " has been cancelled.");
        } else {
            System.out.println("Ticket not found.");
        }
    }*/
}