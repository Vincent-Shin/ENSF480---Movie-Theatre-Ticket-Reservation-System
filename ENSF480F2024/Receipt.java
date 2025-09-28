import java.util.ArrayList;
import java.util.Date;

public class Receipt {
    private String receiptID;
    private String customerEmail;
    private String cardNum;
    private String ticketID;

    public Receipt (String receiptID, String customerEmail, String cardNum, String ticketID){
        this.receiptID = receiptID;
        this.customerEmail = customerEmail;
        this.cardNum = cardNum;
        this.ticketID = ticketID;
    }

    public String getReceiptID(){
        return receiptID;
    }

    public String getCustomerEmail(){
        return customerEmail;
    }

    public String getCardNum(){
        return cardNum;
    }

    public String getTicketID(){
        return ticketID;
    }

    public String printReceipt(){

        StringBuilder receiptText = new StringBuilder(
       "Please keep this receipt for your records.\nThank you for joining us on this silver screen adventure." );

       receiptText.append("\nCustomer Email: " + customerEmail);
       receiptText.append("\nTicketID: " + ticketID);
       receiptText.append("\nPrice: 12$");
       receiptText.append("\nCardNum: XXXX XXXX XXXX "+
       cardNum.charAt(12) +
       cardNum.charAt(13) +
       cardNum.charAt(14) +
       cardNum.charAt(15));

        return receiptText.toString();
    }

    public String printRefund(Ticket ticket, int isRegisteredCustomer){
        StringBuilder receiptText = new StringBuilder(
            "Please keep this receipt to receive credit with AcmePlex.");
            receiptText.append("\nYour credit is valid until one year from the showing indicated here.\nWe hope to see you again soon!");
     
            receiptText.append("\nCustomer Email: " + customerEmail);
            receiptText.append("\nMovie: " + ticket.getMovieTitle());
            receiptText.append("\nCardNum: XXXX XXXX XXXX "+
            cardNum.charAt(12) +
            cardNum.charAt(13) +
            cardNum.charAt(14) +
            cardNum.charAt(15));
            receiptText.append("\nDate of Movie: "+ ticket.getShowingTime());

            if (isRegisteredCustomer>0){
                receiptText.append("\nYou have been refunded the full amount of your purchase:"+ 
                Double.toString(ticket.getPrice()));
            }else{
                receiptText.append("\nYou have been refunded your ticket price with a 15% admin fee: $"
                + (Double.toString(ticket.getPrice()*0.85)));
            }
     
             return receiptText.toString();
    }

}
