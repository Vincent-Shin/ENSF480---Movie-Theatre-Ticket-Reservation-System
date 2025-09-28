import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class RegisteredCustomer extends OrdinaryCustomer{
    private String name;
    private String address;
    private PaymentCard card;
    private boolean accountPaid;
    private static ArrayList<Movie> earlyMovies;

    public RegisteredCustomer (String username, String email, String password,
                                String name, String address, PaymentCard card)
    {
        super(username, email, password);
        this.name = name;
        this.address = address;
        this.card = card;
        this.accountPaid = false;
    } 

    public RegisteredCustomer (String username, String email, String password, DatabaseConnector dbConnector,
                                String name, String address, PaymentCard card)
    {
        super(username, email, password, dbConnector);
        this.name = name;
        this.address = address;
        this.card = card;
        this.accountPaid = false;
    }   

    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public PaymentCard getPaymentCard(){
        return card;
    }

    public String getPassword(){
        return super.getPassword();
    }

    public int IsRegisteredUser(){
        return 1;
    }
    /*
    @Override
    public void bookTicket(String movieTitle, Seat seat, Date showtimeDate, Movie movie, float price, String showtimeTime) {
        if (!accountPaid) {
            System.out.println("Please pay the annual account fee of $20.00 before booking.");
            return;
        }

        try (Connection connection = dbConnector.openDatabase()) {
            String seatCheckQuery = "SELECT isAvailable FROM Seats WHERE SeatID = ?;";
            PreparedStatement seatCheckStmt = connection.prepareStatement(seatCheckQuery);
            seatCheckStmt.setString(1, seat.getSeatId());
            ResultSet rs = seatCheckStmt.executeQuery();
            if (rs.next() && rs.getBoolean("isAvailable")) {
                if (EarlyBooking()) {
                    dbConnector.updateSeatAvailability(seat.getSeatId(), false);
                    // Generate a ticket
                    String ticketId = "TICKET" + System.currentTimeMillis();
                    Ticket ticket = new Ticket(seat, showtimeDate, movie, price, ticketId, showtimeTime);
                    tickets.add(ticket);
                    System.out.println("Seat booked successfully for movie: " + movieTitle);
                } else {
                    System.out.println("You are not eligible for early booking. Please wait for the public release.");
                }
            } else {
                System.out.println("Seat is not available for reservation.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void payAnnualFee() {
        if (!accountPaid) {
            System.out.println("Processing $20.00 annual fee...");
            accountPaid = true;
            System.out.println("Annual fee paid successfully. You can now book tickets.");
        } else {
            System.out.println("Annual fee has already been paid.");
        }
    }

    private boolean EarlyBooking() {
        try (Connection connection = dbConnector.openDatabase()) {
            String totalSeatsQuery = "SELECT COUNT(*) FROM Seats;";
            PreparedStatement totalSeatsStmt = connection.prepareStatement(totalSeatsQuery);
            ResultSet rs = totalSeatsStmt.executeQuery();
            if (rs.next()) {
                int totalSeats = rs.getInt(1);
                int availableSeatsForRUs = (int) (totalSeats * 0.10); // 10% of the total seats
                if (availableSeatsForRUs > tickets.size()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void cancelTicket(String ticketId) {
        Ticket ticketToCancel = null;
        for (Ticket ticket : tickets) {
            if (ticket.getTicketID().equals(ticketId)) {
                ticketToCancel = ticket;
                break;
            }
        }

        if (ticketToCancel != null) {
            Date currentDate = new Date();
            long diffInMillis = ticketToCancel.getShowingTime() - currentDate.getTime();
            long hoursDifference = diffInMillis / (60 * 60 * 1000);

            if (hoursDifference <= 72) {
                double refundAmount = ticketToCancel.getPrice();
                System.out.println("Ticket cancelled. No cancellation fee applies.");
                System.out.println("Refund amount: $" + refundAmount);
            } else {
                System.out.println("Ticket can be cancelled without a fee.");
            }

            tickets.remove(ticketToCancel);
            System.out.println("Ticket " + ticketId + " has been cancelled.");
        } else {
            System.out.println("Ticket not found.");
        }
    }
    public static void addEarlyReleaseMovie(Movie movie) {
        earlyMovies.add(movie);
        System.out.println("Movie added to early release: " + movie.getTitle());
    }

    // Getter for earlyReleaseMovies to retrieve early release movies
    public ArrayList<Movie> getEarlyReleaseMovies() {
        return earlyMovies;
    }
    
    public void receiveNoti() {
        if (!accountPaid) {
            System.out.println("Please pay your annual account fee to receive early notifications.");
            return;
        }

        if (earlyMovies.isEmpty()) {
            System.out.println("No early movie releases available at the moment.");
        } else {
            System.out.println("You have access to the following early release movies:");
            for (Movie movie : earlyMovies) {
                System.out.println("Movie Title: " + movie.getTitle() + ", Release Date: " + movie.getRelease());
            }
        }
    }*/
}
