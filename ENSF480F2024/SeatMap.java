import java.util.HashMap;
import java.util.Map;


public class SeatMap {
    private String seatMapID;
    private String theatreName;
    private int roomNum;
    private int numSeats;
    private Map<String, Seat> seats;

    public SeatMap(String theatreName, int roomNum) {
        this.theatreName = theatreName;
        this.roomNum = roomNum;
        this.seats = new HashMap<>();
    }

    public SeatMap(String seatMapID, String theatreName, int roomNum, int numSeats) {
        this.seatMapID = seatMapID;
        this.theatreName = theatreName;
        this.roomNum = roomNum;
        this.numSeats = numSeats;
        this.seats = new HashMap<>();
    }

    public String getSeatMapID(){
        return seatMapID;
    }

    public String getTheatreName(){
        return theatreName;
    }

    public int getRoomNum(){
        return roomNum;
    }

    public int getNumSeat(){
        return numSeats;
    }

    public void addSeat(Seat seat) {
        if (seats.containsKey(seat.getSeatId())) {
            System.out.println("Seat " + seat.getSeatId() + " already exists in the map.");
        } else {
            seats.put(seat.getSeatId(), seat);
        }
    }

    public Seat getSeat(String seatId) {
        return seats.get(seatId);
    }

    public boolean isSeatsAvailable(String seatId) {
        Seat seat = seats.get(seatId);
        return seat != null && seat.isAvailable();
    }
    
    // some printing and counting available seat code in seat map 
    //I don't know we need those code or not, but It seems like we need to count some 10% seat be served for RUs)
    public int countAvailableSeats() {
        int count = 0;
        for (Seat seat : seats.values()) {
            if (seat.isAvailable()) {
                count++;
            }
        }
        return count;
    }
    //get the available seat
    public Map<String, Seat> getAvailableSeats() {
        Map<String, Seat> availableSeats = new HashMap<>();
        for (Map.Entry<String, Seat> entry : seats.entrySet()) {
            if (entry.getValue().isAvailable()) {
                availableSeats.put(entry.getKey(), entry.getValue());
            }
        }
        return availableSeats;
    }
    public void printSeatMap() {
        System.out.println("Seat Map for Theatre: " + theatreName + ", Room: " + roomNum);
        for (Seat seat : seats.values()) {
            System.out.println(seat.getSeatId() + ": " + (seat.isAvailable() ? "Available" : "Reserved"));
        }
    }
    // Reserve a specific seat for the reservation
    public boolean reserveSeat(String seatId) {
        Seat seat = seats.get(seatId);
        if (seat != null && seat.isAvailable()) {
            seat.reserveSeat();
            return true;
        }
        System.out.println("Seat " + seatId + " is not available for reservation.");
        return false;
    }
    public int getSeatsForRUs() {
        int availableSeats = countAvailableSeats();
        return (int) (availableSeats * 0.10);
    }
}

