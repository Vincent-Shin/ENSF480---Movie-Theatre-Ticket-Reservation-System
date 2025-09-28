import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginPage extends JFrame implements ActionListener{
    private LoginManager login;
    private ArrayList<Movie> movies;
    private ArrayList<Theatre> theatres;
    private ArrayList<Showing> showings;
    private ArrayList<SeatMap> seatmaps;
    private ArrayList<Seat> seats;
    private ArrayList<Ticket> tickets;
    
    private JFrame baseFrame = new JFrame();
    ImageIcon logo;
    JButton loginButton;
    JButton nonRegisteredButton;
    JTextArea newUserMessage;
    JTextField logUsernameTF;
    JTextField logPasswordTF;

    public LoginPage (LoginManager login, ArrayList<Movie> movies,
        ArrayList<Theatre> theatres, ArrayList<Showing> showings, ArrayList<SeatMap> seatmaps,
        ArrayList<Seat> seats, ArrayList<Ticket> tickets){
        
        this.login = login;
        this.movies = movies;
        this.theatres = theatres;
        this.showings = showings;
        this.seatmaps = seatmaps;
        this.seats = seats;
        this.tickets = tickets;

        baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        baseFrame.setTitle("AcmePlex Movie Bookings");
        baseFrame.setSize(500, 500);
        baseFrame.setLayout(new FlowLayout(FlowLayout.LEFT));

        
        logo = new ImageIcon("Images/APLogo.jpg");
        baseFrame.setIconImage(logo.getImage());
        baseFrame.getContentPane().setBackground(new Color(70, 104, 156));

        loginButton = new JButton ("Login");
        loginButton.addActionListener(this);
        nonRegisteredButton = new JButton ("Proceed without logging in");
        nonRegisteredButton.addActionListener(this);
        logUsernameTF = new JTextField();
        logUsernameTF.setText("Username");
        logPasswordTF = new JTextField();
        logPasswordTF.setText("Password");

        logUsernameTF.setPreferredSize(new Dimension (250, 40));
        logPasswordTF.setPreferredSize(new Dimension (250, 40));


        baseFrame.add(logUsernameTF);
        baseFrame.add (logPasswordTF);
        baseFrame.add(loginButton);
        baseFrame.add(nonRegisteredButton);


        baseFrame.setVisible(true);


    }

    public void actionPerformed (ActionEvent e){
        if (e.getSource() == loginButton){
            if (login != null){
                int user = login.checkCredentials(logUsernameTF.getText(), logPasswordTF.getText());
                String userEmail = AcmePlexMovieBookingApp.getUserEmail(logUsernameTF.getText());
                System.out.println(logUsernameTF.getText());
                System.out.println(logPasswordTF.getText());
                System.out.println(user);
            if (user==1){
                //user==1 if the login belongs to a customer
                baseFrame.dispose();
                GUI mainPage = new GUI(user, login, movies, theatres, showings, seatmaps, seats, tickets, userEmail);
            }else if (user==2){
                //user==2 if the login belongs to an admin

            }else{
                JOptionPane.showMessageDialog(
                    null, "Your login information is incorrect.",
                     "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        }else if (e.getSource() == nonRegisteredButton){
            baseFrame.dispose();
            GUI casualMainPage = new GUI(0, login, movies, theatres, showings, seatmaps, seats, tickets, "nonuser");
        }
    }
}
