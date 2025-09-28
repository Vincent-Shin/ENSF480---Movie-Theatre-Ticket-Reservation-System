import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.xml.crypto.Data;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class AcmePlexMovieBookingApp {
    public static DatabaseConnector dbConnection;
    public static ArrayList<Customer> users;
    public static ArrayList<PaymentCard> paymentCards;
    public static ArrayList<Admin> admins;
    public static ArrayList<Movie> movies;
    public static ArrayList<Theatre> theatres;
    public static ArrayList<Showing> showings;
    public static ArrayList<SeatMap> seatmaps;
    public static ArrayList<Seat> seats;
    public static ArrayList<Receipt> receipts;
    public static ArrayList<Ticket> tickets;

    public static int ticketCounter;
    public static int receiptCounter;



    public static void main (String[] args){
        // Must run this file with two command line arguments: db username and db password
        dbConnection = new DatabaseConnector(args);

        users = dbConnection.fetchCustomers();
        paymentCards = dbConnection.fetchCards();
        admins = dbConnection.fetchAdmins();

        movies = dbConnection.fetchMovies();
        theatres = dbConnection.fetchTheatres();
        showings = dbConnection.fetchShowings();
        seatmaps = dbConnection.fetchSeatMaps();
        seats = dbConnection.fetchSeats();

        receipts = dbConnection.fetchReceipts();
        tickets = dbConnection.fetchTickets();

        if (tickets.size()==0){
            ticketCounter = tickets.size();
        }else {
            ticketCounter = Integer.parseInt(tickets.get(tickets.size()-1).getTicketID());
        }

        if (receipts.size()==0){
            receiptCounter = receipts.size();
        }else{
            receiptCounter = Integer.parseInt(receipts.get(receipts.size()-1).getReceiptID());
        }


        LoginManager login = new LoginManager(users, admins);
        LoginPage appStart = new LoginPage(login, movies, theatres, showings, seatmaps, seats, tickets);
        //GUI mainPage = new GUI(0, login, movies, theatres, showings, seatmaps, seats);
        
    }

    public static int makeBooking(String movieTitle, String seatID, String showingTime, String showingID,
        String customerEmail, String theatreName, String cardNum){

            tickets.add(new Ticket(
                Integer.toString(++ticketCounter), movieTitle, theatreName, seatID,
                showingTime, showingID, 12, Integer.toString(++receiptCounter)));

                for(Seat seat:seats){
                    if (seat.getSeatId().compareTo(seatID)==0){
                        seat.updateAvailability(false);  
                    }
                }
            receipts.add(new Receipt(Integer.toString(receiptCounter),
             customerEmail, cardNum, tickets.get(tickets.size()-1).getTicketID()));

            DatabaseConnector.updateSeatAvailability(seatID, false);
            DatabaseConnector.addTicket(Integer.toString(ticketCounter), movieTitle, seatID, showingTime,
            showingID, customerEmail, theatreName, Integer.toString(receiptCounter));
            DatabaseConnector.addReceipt((receipts.get(receipts.size()-1).getReceiptID()),
             customerEmail, cardNum, (tickets.get(tickets.size()-1).getTicketID()));
            
            createTicket(tickets.get(tickets.size()-1));
            createReceipt(receipts.get(tickets.size()-1));
            return 0;
    }

    public static int createTicket(Ticket myTicket){
        // Adapted from Geeks for Geeks Java program to write to a file
        String fileContent = myTicket.printTicket();
        FileOutputStream outputStream = null;
        StringBuilder ticketName = new StringBuilder("ticket");
        ticketName.append(myTicket.getMovieTitle().charAt(0));
        ticketName.append(myTicket.getSeatID());
        ticketName.append(".txt");

        try {
            outputStream = new FileOutputStream(ticketName.toString());
            byte[] strToBytes = fileContent.getBytes();
            outputStream.write(strToBytes);
            System.out.print(
                "Ticket created successfully.");
        }
        catch (IOException e) {
            System.out.print(e.getMessage());
        }

        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                    return 1;
                }
                catch (IOException e) {
                    System.out.print(e.getMessage());
                }
            }
        }
        return 0;
    }

    public static int createReceipt(Receipt myReceipt){
        // Adapted from Geeks for Geeks Java program to write to a file
        String fileContent = myReceipt.printReceipt();
        FileOutputStream outputStream = null;
        StringBuilder receiptName = new StringBuilder("receipt");
        receiptName.append(myReceipt.getReceiptID());
        receiptName.append(myReceipt.getTicketID());
        receiptName.append(".txt");

        try {
            outputStream = new FileOutputStream(receiptName.toString());
            byte[] strToBytes = fileContent.getBytes();
            outputStream.write(strToBytes);
            System.out.print(
                "Receipt created successfully.");
        }
        catch (IOException e) {
            System.out.print(e.getMessage());
        }

        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                    return 1;
                }
                catch (IOException e) {
                    System.out.print(e.getMessage());
                }
            }
        }
        return 0;
    }

    public static int createReceipt(String receiptText){
        // Adapted from Geeks for Geeks Java program to write to a file
        FileOutputStream outputStream = null;
        StringBuilder receiptName = new StringBuilder("refund.txt");

        try {
            outputStream = new FileOutputStream(receiptName.toString());
            byte[] strToBytes = receiptText.getBytes();
            outputStream.write(strToBytes);
            System.out.print(
                "Refund receipt created successfully.");
        }
        catch (IOException e) {
            System.out.print(e.getMessage());
        }

        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                    return 1;
                }
                catch (IOException e) {
                    System.out.print(e.getMessage());
                }
            }
        }
        return 0;
    }




    public static int searchMovies (String searchString, ArrayList<Movie> movies){
        int index = 0;
        for (Movie element:movies){
            if (searchString.compareTo(element.getTitle())==0){
                return index;
            }
            index++;
        }
        return -1;
    }

    public static int searchTickets (String searchString, ArrayList<Ticket> tickets){
        int index = 0;
        for (Ticket element:tickets){
            if (searchString.compareTo(element.getTicketID())==0){
                return index;
            }
            index++;
        }
        return -1;
    }

    public static int searchReceipts (String searchString, ArrayList<Receipt> receipts){
        int index = 0;
        for (Receipt element:receipts){
            if (searchString.compareTo(element.getReceiptID())==0){
                return index;
            }
            index++;
        }
        return -1;
    }

    public static int cancelTicket(Ticket myTicket, int isRegisteredCustomer){

        LocalDateTime todaysDate = LocalDateTime.now();
        System.out.println(todaysDate.format(DateTimeFormatter.ISO_DATE_TIME));
        
        LocalDateTime showDateTime = LocalDateTime.parse(
            myTicket.getShowingTime(), DateTimeFormatter.ISO_DATE_TIME); 

        LocalDateTime latestToCancelDateTime = showDateTime.minusHours(72);
        System.out.println(latestToCancelDateTime.format(DateTimeFormatter.ISO_DATE_TIME));

        System.out.println(todaysDate.compareTo(latestToCancelDateTime));
        int receiptIndex = searchReceipts(myTicket.getReceiptID(), receipts);
        String receiptText;

        if (todaysDate.compareTo(latestToCancelDateTime)<0){
            if ( isRegisteredCustomer > 0){
                receiptText = receipts.get(receiptIndex).printRefund(myTicket, isRegisteredCustomer);
            }else {
                receiptText = receipts.get(receiptIndex).printRefund(myTicket, isRegisteredCustomer);
            }
            createReceipt(receiptText);
            
            DatabaseConnector.updateSeatAvailability(myTicket.getSeatID(), true);
            int j  = 0;
            for (Seat element:seats){
                if (element.getSeatId().compareTo(myTicket.getSeatID())==0){
                    break;
                }
                j++;
            }

            seats.remove(j);

            DatabaseConnector.removeTicket(myTicket.getTicketID());
            int i = 0;
            for (Ticket element:tickets){
                if (element.getTicketID().compareTo(myTicket.getTicketID())==0){
                    break;
                }
                i++;
            }
            tickets.remove(i);

            System.out.println(receiptCounter);

            receipts.add(new Receipt(Integer.toString(++receiptCounter),
             receipts.get(receiptIndex).getCustomerEmail(),
             receipts.get(receiptIndex).getCardNum(), null));
            
             System.out.println(receiptCounter);

            DatabaseConnector.addReceipt(Integer.toString(receiptCounter),
            receipts.get(receiptIndex).getCustomerEmail(),
            receipts.get(receiptIndex).getCardNum(), null);
            
            return 1;
        }else{
            return 0;
        }
    }

    public static String getUserEmail(String searchUsername){
        String userEmail = "";

        for (Customer customer:users){
            if ((customer.getUsername().compareTo(searchUsername))==0){
                userEmail = customer.getEmail();
            }
        }
        return userEmail;
    }

    public static ArrayList<Movie> getEarlyReleaseMovies (){
        ArrayList<Movie> earlyReleaseMovies = new ArrayList<>();

        LocalDate todaysDate = LocalDate.now();  
        for (Movie movie:movies){
            LocalDate releaseDate = LocalDate.parse(
                movie.getRelease(), DateTimeFormatter.ISO_DATE);

            if (todaysDate.compareTo(releaseDate)<0){
                earlyReleaseMovies.add(movie);
            }
        }

        return earlyReleaseMovies;
    }

    public static ArrayList<Movie> getPublicMovies (){
        ArrayList<Movie> publicMovies = new ArrayList<>();

        LocalDate todaysDate = LocalDate.now();  
        for (Movie movie:movies){
            LocalDate releaseDate = LocalDate.parse(
                movie.getRelease(), DateTimeFormatter.ISO_DATE);

            if (todaysDate.compareTo(releaseDate)>=0){
                publicMovies.add(movie);
            }
        }

        return publicMovies;
    }

    public static int searchShowings(String showingID){
        int i = 0;
        for (Showing element:showings){
            if (showingID.compareTo(element.getShowingID())==0){
                return i;
            }
            i++;
        }
        return -1;
    }

    public static boolean checkRatioReservedSeats(String reservedShowingID){
        int tenPercentSeats = seatmaps.get(searchShowings(reservedShowingID)).getNumSeat()/10;
        System.out.println(Integer.toString(tenPercentSeats));

        int reservedSeats = 0;
        for (Seat seat:seats){
            if ((seat.getShowingID().compareTo(reservedShowingID))==0){
                if (!seat.isAvailable()){
                    reservedSeats++;
                }
            }
        }

        System.out.println(Integer.toString(reservedSeats));
        if (tenPercentSeats>reservedSeats){
            return true;
        }else{
            return false;
        }
    }

    public static ImageIcon resize(ImageIcon icon, int extent) {
        return new ImageIcon(icon.getImage().getScaledInstance(extent, extent, Image.SCALE_SMOOTH));
    }
}
