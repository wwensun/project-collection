package GeneticModel;

import java.util.ArrayList;

public class Chromosome {
	public Chromosome() {
            list = new ArrayList<Timeslot>();
	}
	
	public static Chromosome getInstance(){
        if(chromosome==null){
            chromosome=new Chromosome();           
        }
        return chromosome;
    }
	
	public void setList() {
            int count = 0;
            list.clear();
            for(Classroom room: School.getInstance().getRoomList()) {
                for(int day=1; day<=5; day++) {
                    for(int hour=8; hour<=20; hour++) {
                        Timeslot slot = new Timeslot(count,day,hour,room);
    //System.out.println(slot.getID() + "： " +  slot.getDay() + " " + slot.getStart() + " " + slot.getClassroom());
                        list.add(slot);
                        count++;
                    }
                }
            }
	}
	
	public ArrayList<Timeslot> getList() {
            return this.list;
	}
	
	private static Chromosome chromosome;
	private ArrayList<Timeslot> list;
	
	
}
