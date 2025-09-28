import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.Rectangle;

public class GUI extends JFrame implements ActionListener{
    int user;
    private LoginManager login;
    private ArrayList<Movie> movies;
    private ArrayList<Movie> publicMovies;
    private ArrayList<Movie> earlyMovies;
    private ArrayList<Theatre> theatres;
    private ArrayList<Showing> showings;
    private ArrayList<SeatMap> seatmaps;
    private ArrayList<Seat> seats;
    private ArrayList<Ticket> tickets;
    private String userEmail;
    
    JFrame guiBaseFrame = new JFrame();
    ImageIcon logo;
    JTabbedPane backgroundPane = new JTabbedPane();

    ArrayList<ImageIcon> moviePosterIcons = new ArrayList<>();
    ArrayList<JButton> selectMovieButtons = new ArrayList<>();
    ArrayList<JLabel> movieLabels = new ArrayList<>();
    JPanel contentPanel = new JPanel();
    JScrollPane scrollLayer;

    JTextField searchBarField;
    JButton searchButton;
    JPanel searchPanel;

    JTextField cancelField = new JTextField();
    JButton cancelButton = new JButton();
    JPanel cancelPanel = new JPanel();

    ArrayList<ImageIcon> moviePosterIconsE = new ArrayList<>();
    ArrayList<JButton> selectMovieButtonsE = new ArrayList<>();
    ArrayList<JLabel> movieLabelsE = new ArrayList<>();
    JPanel contentPanelE = new JPanel();
    JScrollPane scrollLayerE;


    public GUI (int user, LoginManager login, ArrayList<Movie> movies,
    ArrayList<Theatre> theatres, ArrayList<Showing> showings, ArrayList<SeatMap> seatmaps,
    ArrayList<Seat> seats, ArrayList<Ticket> tickets, String userEmail){
        this.user = user;
        this.login = login;
        this.movies = movies;
        this.publicMovies = new ArrayList<>();
        for (Movie movie:AcmePlexMovieBookingApp.getPublicMovies()){
            publicMovies.add(movie);
        }
        
        this.earlyMovies = new ArrayList<>();
        for (Movie movie:AcmePlexMovieBookingApp.getEarlyReleaseMovies()){
            earlyMovies.add(movie);
        }
        this.theatres = theatres;
        this.showings = showings;
        this.seatmaps = seatmaps;
        this.seats = seats;
        this.tickets = tickets;
        this.userEmail = userEmail;

        guiBaseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiBaseFrame.setTitle("AcmePlex Movie Bookings");
        guiBaseFrame.setSize(1000, 750);
        guiBaseFrame.getContentPane().setBackground(new Color(70, 104, 156));
        
        logo = new ImageIcon("Images/APLogo.jpg");
        guiBaseFrame.setIconImage(logo.getImage());
        guiBaseFrame.getContentPane().setBackground(new Color(70, 104, 156));
        guiBaseFrame.setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());

        searchBarField = new JTextField();
        searchBarField.setText("Search Movies by Title");
        searchBarField.setSize(200, 40);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);

        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setSize(400, 50);
        searchPanel.add(searchBarField);
        searchPanel.add(searchButton);

        backgroundPane.addTab("Search", searchPanel);

        contentPanel.setLayout(new FlowLayout());
        this.populateMoviePanels(contentPanel);
        //System.out.println("populatemoviepanels has been called");
        contentPanel.setVisible(true);
        scrollLayer = new JScrollPane();
        scrollLayer.setViewportView(contentPanel);
        scrollLayer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        backgroundPane.addTab("Browse Movies", scrollLayer);

        cancelField.setText("Search by TicketID");
        cancelField.setSize(200, 40);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        cancelPanel.setLayout(new FlowLayout());
        cancelPanel.setSize(400, 50);
        cancelPanel.add(cancelField);
        cancelPanel.add(cancelButton);

        backgroundPane.addTab("Cancel Ticket", cancelPanel);

        if (user==1){
            contentPanelE.setLayout(new FlowLayout());
            this.populateEMoviePanels(contentPanelE);
            //System.out.println("populatemoviepanels has been called");
            contentPanelE.setVisible(true);
            scrollLayerE = new JScrollPane();
            scrollLayerE.setViewportView(contentPanelE);
            scrollLayerE.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
            backgroundPane.addTab("Browse Early Release Movies", scrollLayerE);
        }

        guiBaseFrame.add(backgroundPane);
        guiBaseFrame.setVisible(true);
        guiBaseFrame.revalidate();


    }

    public void actionPerformed (ActionEvent e){

        if (e.getSource() == searchButton){
            System.out.println("Search Button pressed");
            int movieIndex = AcmePlexMovieBookingApp.searchMovies(searchBarField.getText(), publicMovies);
            System.out.println(movieIndex);
            if (movieIndex>=0){
                guiBaseFrame.dispose();
                TicketBookingGUI selectedMovie = new TicketBookingGUI(
                    user, login, movies, theatres, showings, seatmaps, seats, tickets, searchBarField.getText(), userEmail, false);
            }
            else{
                System.out.println("Movie not found");
                JOptionPane.showMessageDialog(
                    null, "Your movie title was not found in our theatres.",
                     "Search Title Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == cancelButton){
            int ticketIndex = AcmePlexMovieBookingApp.searchTickets(cancelField.getText(), tickets);
            Ticket ticketToCancel = tickets.get(ticketIndex);
            int success = AcmePlexMovieBookingApp.cancelTicket(ticketToCancel, user);

            if (success == 1){
                JOptionPane.showMessageDialog(
                    null, "Your ticket was cancelled successfully.",
                     "Ticket Cancellation", JOptionPane.INFORMATION_MESSAGE);
            }

            else{
                JOptionPane.showMessageDialog(
                    null, "There was an error cancelling your ticket.\nPlease contact admin for support.",
                     "Ticket Cancellation Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            for (JButton button: selectMovieButtons){
                if (e.getSource()==button){
                    int movieIndex = AcmePlexMovieBookingApp.searchMovies(button.getText(), movies);
                    guiBaseFrame.dispose();
                    System.out.println("Ticket Booking Page Reached");
                    TicketBookingGUI selectedMovie = new TicketBookingGUI(
                        user, login, movies, theatres, showings, seatmaps, seats, tickets, button.getText(), userEmail, false);
                }
            }
        }
        for (JButton button: selectMovieButtonsE){
            if (e.getSource()==button){
                int movieIndex = AcmePlexMovieBookingApp.searchMovies(button.getText(), movies);
                guiBaseFrame.dispose();
                System.out.println("Advance Ticket Booking Page Reached");
                TicketBookingGUI selectedMovie = new TicketBookingGUI(
                    user, login, movies, theatres, showings, seatmaps, seats, tickets, button.getText(), userEmail, true);
            }
        }
    }

    public void populateMoviePanels (JPanel wrapperPanel){
        int i = 0;
        if (publicMovies != null){
            
            for (Movie element: publicMovies){

                ImageIcon tempIcon = new ImageIcon("Images/MoviePosters/"+ 
                (Integer.toString(element.getDuration())) + ".jpg");
                moviePosterIcons.add(tempIcon);

                //System.out.println(tempIcon==null);

                JButton tempButton = new JButton();
                tempButton.setText( element.getTitle());

                selectMovieButtons.add(tempButton);
                selectMovieButtons.get(i).addActionListener(this);

                //System.out.println(tempButton==null);
                JLabel tempLabel = new JLabel (tempIcon);
                tempLabel.setText("Book a Ticket for: " + element.getTitle() + 
                "\n Price : 12$");
                tempLabel.setBackground(new Color (70, 104, 156));
                movieLabels.add(tempLabel);

                System.out.println(element.getTitle());

                wrapperPanel.add(movieLabels.get(i));
                wrapperPanel.add(selectMovieButtons.get(i));
                System.out.println(tempButton.getText());
                i++;
            }
        }

    }

    public void populateEMoviePanels (JPanel wrapperPanel){
        int i = 0;
        if (earlyMovies != null){
            
            for (Movie element: earlyMovies){

                ImageIcon tempIcon = new ImageIcon("Images/MoviePosters/"+ 
                (Integer.toString(element.getDuration())) + ".jpg");
                moviePosterIconsE.add(tempIcon);

                //System.out.println(tempIcon==null);

                JButton tempButton = new JButton();
                tempButton.setText( element.getTitle());

                selectMovieButtonsE.add(tempButton);
                selectMovieButtonsE.get(i).addActionListener(this);

                //System.out.println(tempButton==null);
                JLabel tempLabel = new JLabel (tempIcon);
                tempLabel.setText("Book a Ticket for: " + element.getTitle() + 
                "\n Price : 12$");
                tempLabel.setBackground(new Color (70, 104, 156));
                movieLabelsE.add(tempLabel);

                System.out.println(element.getTitle());

                wrapperPanel.add(movieLabelsE.get(i));
                wrapperPanel.add(selectMovieButtonsE.get(i));
                System.out.println(tempButton.getText());
                i++;
            }
        }

    }

}