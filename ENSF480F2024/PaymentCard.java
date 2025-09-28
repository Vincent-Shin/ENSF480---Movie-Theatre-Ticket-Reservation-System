public class PaymentCard {
    private String cardNum;
    private String cardCVV;
    private String cardExpDate;
    private String cardholderName;

    public PaymentCard (String cardNum, String cardholderName){
        this.cardNum = cardNum;
        this.cardholderName = cardholderName;
    }

    public PaymentCard (String cardNum, String cardCVV, String cardExpDate, String cardholderName){
        this.cardNum = cardNum;
        this.cardCVV = cardCVV;
        this.cardExpDate = cardExpDate;
        this.cardholderName = cardholderName;
    }

    public String getCardNum(){
        return cardNum;
    }

    public String getCardExpDate(){
        return cardExpDate;
    }
    
    public String getCardCVV(){
        return cardCVV;
    }
    public String getcardName(){
        return cardholderName;
    }
}
