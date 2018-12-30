package GeneticModel;

public class Timeslot {
    public Timeslot(int id, int day, int start, Classroom room) {
        this.id = id;
        this.start = start;
        this.day = day;
        this.classroom = room;
    }

    public Classroom getClassroom() {
        return this.classroom;
    }

    public int getDay() {
        return this.day;
    }

    public int getStart() {
        return this.start;
    }
    public int getID() {
        return this.id;
    }

    private int id;
    private int day; //day of the week of the timeslot(1-5)
    private int start; //start time of the time slot
    private Classroom classroom;
	
}
