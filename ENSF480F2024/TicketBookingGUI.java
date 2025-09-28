import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.time.*;

public class TicketBookingGUI extends JFrame implements ActionListener{

    private int user;
    private LoginManager login;
    private ArrayList<Movie> movies;
    private ArrayList<Theatre> theatres;
    private ArrayList<Showing> showings;
    private ArrayList<SeatMap> seatmaps;
    private ArrayList<Seat> seats;
    private ArrayList<Ticket> tickets;
    String selectedMovieTitle;
    String selectedTheatreName;
    String selectedShowingTime;
    String selectedShowingID;
    String selectedSeatMapID;
    String selectedSeatID;
    String userEmail;
    boolean earlyMovie;
    
    private JFrame tbBaseFrame = new JFrame();
    ImageIcon logo;
    ImageIcon seatmapIcon = new ImageIcon("Images/SeatMap.jpg");

    JLabel movieLabel = new JLabel();

    JPanel clearPanel = new JPanel();
    JScrollPane scrollPane = new JScrollPane();

    JComboBox<String> theatreBox;
    JButton selectTheatreButton = new JButton();
    JComboBox<String> showtimeBox;
    JButton selectShowingButton = new JButton();
    JComboBox<String> seatBox;
    JButton selectSeatButton = new JButton();

    JButton bookTicketButton = new JButton();;
    JTextField cardNumField = new JTextField();
    JTextField cardExpField = new JTextField();
    JTextField cardCVVField = new JTextField();
    JTextField cardholderNameField = new JTextField();
    JTextField customerEmailField = new JTextField();
    JButton submitButton = new JButton();

    

    public TicketBookingGUI (int user, LoginManager login, ArrayList<Movie> movies,
    ArrayList<Theatre> theatres, ArrayList<Showing> showings, ArrayList<SeatMap> seatmaps,
    ArrayList<Seat> seats, ArrayList<Ticket> tickets, String selectedMovieTitle, String userEmail, boolean earlyMovie){

        this.user = user;
        this.login = login;
        this.movies = movies;
        this.theatres = theatres;
        this.showings = showings;
        this.seatmaps = seatmaps;
        this.seats = seats;
        this.tickets = tickets;
        this.selectedMovieTitle = selectedMovieTitle;
        this.userEmail = userEmail;
        this.earlyMovie = earlyMovie;

        tbBaseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tbBaseFrame.setTitle("AcmePlex Movie Bookings");
        tbBaseFrame.setSize(1000, 750);
        tbBaseFrame.setLayout(new FlowLayout());
        tbBaseFrame.getContentPane().setBackground(new Color(70, 104, 156));
        
        logo = new ImageIcon("Images/APLogo.jpg");
        tbBaseFrame.setIconImage(logo.getImage());
        tbBaseFrame.getContentPane().setBackground(new Color(70, 104, 156));

        movieLabel.setText(selectedMovieTitle);
        movieLabel.setFont(new Font("Consolas", Font.PLAIN, 24));
        clearPanel.setLayout(new FlowLayout());
        clearPanel.add(movieLabel);
        clearPanel.setPreferredSize(new Dimension(800, 500));

        JPanel imagePanel = new JPanel();
        seatmapIcon = AcmePlexMovieBookingApp.resize(seatmapIcon, 400);
        imagePanel.add(new JLabel(seatmapIcon));
        imagePanel.setVisible(true);
        
        clearPanel.add(imagePanel);

        ArrayList<String> theatreList = new ArrayList<>();
        for (Showing show:showings){
            if (((show.getMovieTitle().compareTo(selectedMovieTitle))==0)&&(!theatreList.contains(show.getTheatreName()))){
                theatreList.add(show.getTheatreName());
            }
        }

        String[] mytheatreNames = theatreList.toArray(new String[0]);
        theatreBox = new JComboBox<>(mytheatreNames);
        theatreBox.addActionListener(this);
        clearPanel.add (theatreBox);

        scrollPane.setViewportView(clearPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVisible(true);
        tbBaseFrame.getContentPane().add(scrollPane);
        tbBaseFrame.setVisible(true);


    }

    public void actionPerformed (ActionEvent e){
        if (e.getSource() == theatreBox){
            System.out.println(theatreBox.getSelectedItem());
            selectedTheatreName = theatreBox.getSelectedItem().toString();
            ArrayList<String> showtimesList = new ArrayList<>();
            for (Showing show:showings){
                if (((show.getTheatreName().compareTo
                (selectedTheatreName))==0)&&((show.getMovieTitle().compareTo(selectedMovieTitle))==0)){
                    showtimesList.add(show.getShowingTime());
                }
            }
    
            String[] myshowings = showtimesList.toArray(new String[0]);
            showtimeBox = new JComboBox<>(myshowings);
            showtimeBox.addActionListener(this);
            clearPanel.add(showtimeBox);
            //scrollPane.remove(clearPanel);
            //scrollPane.add(clearPanel);
            tbBaseFrame.revalidate();

        }else if (e.getSource() == showtimeBox){
            System.out.println(showtimeBox.getSelectedItem());
            selectedShowingTime = showtimeBox.getSelectedItem().toString();

            for (Showing show:showings){
                if (((show.getShowingTime().compareTo(selectedShowingTime))==0)&&
                (show.getTheatreName().compareTo(selectedTheatreName)==0)){
                    selectedSeatMapID = show.getSeatMapID();
                    selectedShowingID = show.getShowingID();
                    System.out.println(selectedSeatMapID);
                }
            }
            ArrayList<String> seatList = new ArrayList<>();
            for (Seat seat:seats){
                if ((seat.isAvailable())&&
                (selectedSeatMapID.compareTo(seat.getSeatMapID())==0)&&
                (selectedShowingID.compareTo(seat.getShowingID())==0)){
                    seatList.add(seat.getSeatId());
                }
            
            }
    
            String[] myseatList = seatList.toArray(new String[0]);
            seatBox = new JComboBox<>(myseatList);
            seatBox.addActionListener(this);
            clearPanel.add(seatBox);
            bookTicketButton.setText("Book this ticket");
            bookTicketButton.addActionListener(this);
            clearPanel.add(bookTicketButton);
            //scrollPane.remove(clearPanel);
            //scrollPane.add(clearPanel);
            tbBaseFrame.revalidate();


        }else if (e.getSource() == bookTicketButton){

            if (earlyMovie){
                System.out.println("check reserved seats" +
                AcmePlexMovieBookingApp.checkRatioReservedSeats(selectedShowingID));
                if (AcmePlexMovieBookingApp.checkRatioReservedSeats(selectedShowingID)){

                    selectedSeatID = seatBox.getSelectedItem().toString();
                    cardNumField.setText("Enter 16-digit Card Number with no spaces");
                    cardExpField.setText("Enter Card Expiry Date in format MMYY");
                    cardCVVField.setText("Enter 3-digit Card CVV Number");
                    cardholderNameField.setText("Enter Cardholder Name");
                    customerEmailField.setText("Enter email to associate with this ticket");
                    submitButton.setText("Submit");
                    submitButton.addActionListener(this);

                    cardNumField.setPreferredSize(new Dimension (300, 40));
                    cardExpField.setPreferredSize(new Dimension (300, 40));
                    cardCVVField.setPreferredSize(new Dimension (300, 40));
                    cardholderNameField.setPreferredSize(new Dimension (300, 40));
                    customerEmailField.setPreferredSize(new Dimension (300, 40));

                    tbBaseFrame.add(cardNumField);
                    tbBaseFrame.add(cardExpField);
                    tbBaseFrame.add(cardCVVField);
                    tbBaseFrame.add(cardholderNameField);
                    tbBaseFrame.add(customerEmailField);
                    tbBaseFrame.add(submitButton);

                    tbBaseFrame.validate();         
                }
                else {
                    JOptionPane.showMessageDialog(
                        null, "This showing has reached its quota of reserved seating prior to public release.",
                        "Max Reserved Seats Reached", JOptionPane.ERROR_MESSAGE);
                        tbBaseFrame.dispose();
                        GUI mainPage = new GUI(user, login, movies, theatres, showings, seatmaps, seats, tickets, userEmail);

                }
            }else{
                selectedSeatID = seatBox.getSelectedItem().toString();
                cardNumField.setText("Enter 16-digit Card Number with no spaces");
                cardExpField.setText("Enter Card Expiry Date in format MMYY");
                cardCVVField.setText("Enter 3-digit Card CVV Number");
                cardholderNameField.setText("Enter Cardholder Name");
                customerEmailField.setText("Enter email to associate with this ticket");
                submitButton.setText("Submit");
                submitButton.addActionListener(this);

                cardNumField.setPreferredSize(new Dimension (300, 40));
                cardExpField.setPreferredSize(new Dimension (300, 40));
                cardCVVField.setPreferredSize(new Dimension (300, 40));
                cardholderNameField.setPreferredSize(new Dimension (300, 40));
                customerEmailField.setPreferredSize(new Dimension (300, 40));

                tbBaseFrame.add(cardNumField);
                tbBaseFrame.add(cardExpField);
                tbBaseFrame.add(cardCVVField);
                tbBaseFrame.add(cardholderNameField);
                tbBaseFrame.add(customerEmailField);
                tbBaseFrame.add(submitButton);

                tbBaseFrame.validate();   
            }

        }else if (e.getSource() == submitButton){
            if ((cardNumField.getText().length() == 16)&&
            (cardCVVField.getText().length() == 3)&&
            (cardExpField.getText().length()  ==4)){
                System.out.println(selectedMovieTitle);
                System.out.println(selectedSeatID);
                System.out.println(selectedShowingTime);
                System.out.println(selectedShowingID);
                System.out.println(customerEmailField.getText());
                System.out.println(selectedTheatreName); 

                AcmePlexMovieBookingApp.makeBooking(selectedMovieTitle, selectedSeatID, selectedShowingTime,
                    selectedShowingID, customerEmailField.getText(), selectedTheatreName, cardNumField.getText());
                JOptionPane.showMessageDialog(
                        null, "Your ticket was booked successfully!",
                         "Ticket Booking Success", JOptionPane.INFORMATION_MESSAGE);
                tbBaseFrame.dispose();
                GUI mainPage = new GUI(user, login, movies, theatres, showings, seatmaps, seats, tickets, userEmail);
            }
            else{
                JOptionPane.showMessageDialog(
                    null, "Please review your card information entry.\nThe format is incorrect.",
                     "Card Info Error", JOptionPane.ERROR_MESSAGE);
                
            }

        }
    }

    



}