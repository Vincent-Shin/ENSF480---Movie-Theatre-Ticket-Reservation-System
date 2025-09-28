import java.util.ArrayList;

public class Order {
    private PaymentCard card;
    private Receipt receipt;
    private String email;
    private ArrayList<Ticket> tickets;

    public Order (PaymentCard card, Receipt receipt, String email, ArrayList<Ticket> tickets){
        this.card = card;
        this.receipt = receipt;
        this.email = email;
        this.tickets = new ArrayList<>();
        for(Ticket element:tickets){
            this.tickets.add(element);
        }
    }

    public PaymentCard getPaymentCard(){
        return card;
    }
    public Receipt getReceipt(){
        return receipt;
    }
    public String getEmail(){
        return email;
    }
    public ArrayList<Ticket> getTickets(){
        return tickets;
    }
}
