package GeneticModel;

import java.util.ArrayList;

public class ClassroomList {
	public ClassroomList() {
		roomList = new ArrayList<Classroom>();
	}
	
	public void add(Classroom room) {
		roomList.add(room);
	}
	
	public int count() {
		return roomList.size();
	}
	
	private ArrayList<Classroom> roomList;
}
