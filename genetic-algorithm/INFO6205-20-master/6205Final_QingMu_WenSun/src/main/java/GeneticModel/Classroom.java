package GeneticModel;

public class Classroom 
{
	public Classroom(int roomID, int seats) {
            this.roomID = roomID;
            this.seats = seats;
	}
	
	public int getRoomID() {
            return this.roomID;
	}
	
	public int getSeats() {
            return this.seats;
	}
	
    private int roomID;
    private int seats;
}
