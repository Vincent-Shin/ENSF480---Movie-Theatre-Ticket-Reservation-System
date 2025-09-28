public class Seat {
    private String seatMapID;
    private String showingID;
    private String seatId;
    private String theatreName;
    private int roomNum;
    private boolean isAvailable;
   
    public Seat(String seatId, String theatreName, int roomNum, String showtime){
        this.seatId = seatId;
        this.theatreName = theatreName;
        this.roomNum = roomNum;
        this.isAvailable = true;

    }

    public Seat(String seatMapID, String showingID, String seatID, boolean isAvailable){
        this.seatMapID = seatMapID;
        this.showingID = showingID;
        this.seatId = seatID;
        this.isAvailable = isAvailable;
    }
    
    public String getSeatId(){
        return seatId;
    }
    public String getTheatreName(){
        return theatreName;
    }
    public int getRoomNum(){
        return roomNum;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public String getSeatMapID(){
        return seatMapID;
    }

    public String getShowingID(){
        return showingID;
    }

    public void updateAvailability(boolean availability){
        isAvailable = availability;
    }

    public void reserveSeat() {
        if(isAvailable){
            isAvailable = false;
            System.out.println("Seat" +seatId + "reserved successfully");
        } else {
            System.out.println("Seat" +seatId + "is already reserved ");
        }
    }

    public void releaseSeat() {
        if (!isAvailable) {
            this.isAvailable = true;
            System.out.println("Seat " + seatId + " has been released.");
        } else {
            System.out.println("Seat " + seatId + " is already available.");
        }
    }

    public void cancelReservation() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Reservation for seat " + seatId + " canceled successfully.");
        } else {
            System.out.println("Seat " + seatId + " is not reserved.");
        }
    }

}
