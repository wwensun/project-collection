package GeneticModel;

import java.util.ArrayList;

public class School {
    public static School getInstance(){
        if(school==null){
            school=new School(); 
            findflag = 0;
        }
        return school;
    }

    public void addCourseClass(CourseClass c) {
        classList.add(c);
    } 

    public void addClassroom(Classroom r) {
        roomList.add(r);
    }

    public ArrayList<CourseClass> getClassList() {
        return this.classList;
    }

    public ArrayList<Classroom> getRoomList() {
        return this.roomList;
    }

    public int getRoomCount() {
        return this.roomList.size();
    }

    private static School school;
    private ArrayList<CourseClass> classList;
    private ArrayList<Classroom> roomList;
    public static int findflag;

    private School() {
        classList = new ArrayList<CourseClass>();
        roomList = new ArrayList<Classroom>();
    }
	
}
