package GeneticModel;

public class CourseClass {
	public CourseClass(int classID, String className, int studentNumber, int duration) {
            this.classID = classID;
            this.className = className;
            this.studentNumber = studentNumber;
            this.duration = duration;
	}
	
	public int getStudentNumber() {
            return this.studentNumber;
	}
	
	public int getDuration() {
            return this.duration;
	}
	
	public int getCourseID() {
            return this.classID;
	}
	
	@Override
	public String toString() {
            return this.className;
	}
	
	private int classID;
	private String className;
	private int studentNumber;
	private int duration;
}
