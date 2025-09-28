import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.time.*;
import java.time.format.*;

public class DatabaseConnector{
    static Connection AcmeDB;
    static String URL = "jdbc:mysql://127.0.0.1:3306/acmeplexdb";
    static String userName;
    static String passWord;

    public DatabaseConnector(String[] mainArgs){
        userName = mainArgs[0];
        passWord = mainArgs[1];
        AcmeDB = openDatabase();
    }

    static Connection getConnection(){
        return AcmeDB;
    }


    static Connection openDatabase(){


        /*try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            // handle the error
        }*/
        
        try{
            AcmeDB = DriverManager.getConnection(URL, userName, passWord);
            if (AcmeDB != null) System.out.println("Database connection established.");
            return AcmeDB;
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to connect to database");
            return null;
        }

    }

    public void closeDataBase() {
        try {
            if (AcmeDB != null && !AcmeDB.isClosed()) {
            AcmeDB.close();
            System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Customer> fetchCustomers() {
        String query = "SELECT * FROM Customers;";
        try {
            Statement statement = AcmeDB.createStatement();
            ResultSet result = statement.executeQuery(query);
            ArrayList<Customer> customers = new ArrayList<>();
            while(result.next()){
                if (result.getBoolean("ISRegisteredUser")){
                    customers.add(new RegisteredCustomer(
                        result.getString(1),
                        result.getString(3),
                        result.getString(2),
                        this,
                        result.getString(5),
                        result.getString(6),
                        (new PaymentCard(result.getString(7), result.getString(5)))));
                } else{
                    customers.add(new OrdinaryCustomer(
                        result.getString(1),
                        result.getString(3),
                        result.getString(2),
                        this));
                }
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Admin> fetchAdmins() {
        String query = "SELECT * FROM Admins;";
        try {
            Statement statement = AcmeDB.createStatement();
            ResultSet result = statement.executeQuery(query);
            ArrayList<Admin> admins = new ArrayList<>();
            while(result.next()){
                admins.add(new Admin(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    this));
            }
            return admins;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Movie> fetchMovies() {
        String query = "SELECT * FROM Movies;";
        try {
            Statement statement = AcmeDB.createStatement();
            ResultSet result = statement.executeQuery(query);
            ArrayList<Movie> movies = new ArrayList<>();
            while(result.next()){
                movies.add(new Movie(
                    result.getString(1),
                    result.getInt(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)));
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Theatre> fetchTheatres() {
        String query = "SELECT * FROM Theatres;";
        try {
            Statement statement = AcmeDB.createStatement();
            ResultSet result = statement.executeQuery(query);
            ArrayList<Theatre> theatres = new ArrayList<>();
            while(result.next()){
                theatres.add(new Theatre(
                    result.getString(1),
                    result.getInt(2)));
                //System.out.println(result.getString(1));    
            }
            return theatres;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Showing> fetchShowings() {
        String query = "SELECT * FROM Showings;";
        try {
            Statement statement = AcmeDB.createStatement();
            ResultSet result = statement.executeQuery(query);
            ArrayList<Showing> showings = new ArrayList<>();
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while(result.next()){
                showings.add(new Showing(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4),
                    (result.getString(5)),
                    result.getString(6)
                    ));
                //System.out.println(result.getString(1));    
            }
            return showings;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<PaymentCard> fetchCards() {
        String query = "SELECT * FROM CardInfo;";
        try {
            Statement statement = AcmeDB.createStatement();
            ResultSet result = statement.executeQuery(query);
            ArrayList<PaymentCard> paymentCards = new ArrayList<>();

            while(result.next()){
                paymentCards.add(new PaymentCard(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
                    ));
                //System.out.println(result.getString(1));    
            }
            return paymentCards;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Ticket> fetchTickets() {
        String query = "SELECT * FROM Tickets;";
        try {
            Statement statement = AcmeDB.createStatement();
            ResultSet result = statement.executeQuery(query);
            ArrayList<Ticket> tickets = new ArrayList<>();

            while(result.next()){
                tickets.add(new Ticket(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getFloat(7),
                    result.getString(8)
                ));
                //System.out.println(result.getString(1));    
            }
            return tickets;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Receipt> fetchReceipts() {
        String query = "SELECT * FROM Receipts;";
        try {
            Statement statement = AcmeDB.createStatement();
            ResultSet result = statement.executeQuery(query);
            ArrayList<Receipt> receipts = new ArrayList<>();

            while(result.next()){
                receipts.add(new Receipt(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
                    ));
                //System.out.println(result.getString(1));    
            }
            return receipts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<SeatMap> fetchSeatMaps() {
        String query = "SELECT * FROM seatMaps;";
        try {
            Statement statement = AcmeDB.createStatement();
            ResultSet result = statement.executeQuery(query);
            ArrayList<SeatMap> seatMaps = new ArrayList<>();
            while(result.next()){
                seatMaps.add(new SeatMap(
                    result.getString(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getInt(4)
                    ));
                //System.out.println(result.getString(2));    
            }
            return seatMaps;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public ArrayList<Seat> fetchSeats() {
        String query = "SELECT * FROM Seats;";
        try {
            Statement statement = AcmeDB.createStatement();
            ResultSet result = statement.executeQuery(query);
            ArrayList<Seat> seats = new ArrayList<>();
            while(result.next()){
                seats.add(new Seat( 
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getBoolean(4)));
                //System.out.println(result.getString(3));    
            }
            //System.out.println("We have arrived at .fetchSeats()");
            return seats;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }




    public ResultSet fetchAvailableSeats(String movieTitle, String showtime) {
        String query = "SELECT s.SeatID, s.TheatreName, s.RoomNum FROM Seats s "
                     + "JOIN Tickets t ON s.SeatID = t.SeatID "
                     + "WHERE t.MovieName = ? AND t.DatePurchased = ? AND s.isAvailable = TRUE;";
        try {
            PreparedStatement preparedStatement = AcmeDB.prepareStatement(query);
            preparedStatement.setString(1, movieTitle);
            preparedStatement.setString(2, showtime);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean updateSeatAvailability(String seatId, boolean isAvailable) {
        String query = "UPDATE Seats SET isAvailable = ? WHERE SeatID = ?;";
        try {
            PreparedStatement preparedStatement = AcmeDB.prepareStatement(query);
            preparedStatement.setBoolean(1, isAvailable);
            preparedStatement.setString(2, seatId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addTicket(String ticketID, String movieTitle, String seatID, String showingTime, String showingID,
    String customerEmail, String theatreName, String receiptID){
        try {
            String addTicketStringQuery = "INSERT INTO Tickets VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement addTicketStatement = AcmeDB.prepareStatement(addTicketStringQuery);
            addTicketStatement.setString(1, ticketID);
            addTicketStatement.setString(2, movieTitle);
            addTicketStatement.setString(3, theatreName);
            addTicketStatement.setString(4, seatID);
            addTicketStatement.setString(5, showingTime);
            addTicketStatement.setString(6, showingID);
            addTicketStatement.setFloat(7, 12);
            addTicketStatement.setString(8, receiptID);

            int check = addTicketStatement.executeUpdate();
            System.out.println(check);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addReceipt(String receiptID, String custEmail, String cardNum, String ticketID){

        try{
            String addTicketStringQuery = "INSERT INTO Receipts VALUES (?, ?, ?, ?);";
            PreparedStatement addTicketStatement = AcmeDB.prepareStatement(addTicketStringQuery);
            addTicketStatement.setString(1, receiptID);
            addTicketStatement.setString(2, custEmail);
            addTicketStatement.setString(3, cardNum);
            addTicketStatement.setString(4, ticketID);

            int check = addTicketStatement.executeUpdate();
            System.out.println(check);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean removeTicket(String ticketIDString){

        try{
            String removeTicketQuery = "DELETE FROM Tickets WHERE TicketID = ?;";
            PreparedStatement removeTicketStatement = AcmeDB.prepareStatement(removeTicketQuery);
            removeTicketStatement.setString(1, ticketIDString);

            int check = removeTicketStatement.executeUpdate();
            System.out.println(check);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
