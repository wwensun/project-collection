package GeneticAlgorithm;

import GeneticModel.Chromosome;
import GeneticModel.Classroom;
import GeneticModel.CourseClass;
import GeneticModel.Schedule;
import GeneticModel.School;
import GeneticModel.Timeslot;

import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FitnessTest{
	
    @Test
    public void testFitness0() {
        School school = School.getInstance();
        
        CourseClass c1 = new CourseClass(1,"Algorithm",50,2);
        school.addCourseClass(c1);
        
        Classroom r1 = new Classroom(1,40);
        school.addClassroom(r1);
        Chromosome chro = Chromosome.getInstance();
        chro.setList();

        HashMap<CourseClass,Timeslot> hash = new HashMap<CourseClass,Timeslot>();
        hash.put(c1, chro.getList().get(12));
        
        Schedule schedule = new Schedule(hash,2,0);
        school.getRoomList().remove(r1);
        school.getClassList().remove(c1);
        assertEquals(0.333333333, schedule.getFitness(),1.0E-7);
    }
	
    @Test
    public void testFitness1() {
        School school = School.getInstance();
        
        CourseClass c1 = new CourseClass(1,"Algorithm",50,2);
        CourseClass c2 = new CourseClass(2,"Database",40,3);
        school.addCourseClass(c1);
        school.addCourseClass(c2);
        
        Classroom r1 = new Classroom(1,40);
        school.addClassroom(r1);
        Chromosome chro = Chromosome.getInstance();
        chro.setList();

        HashMap<CourseClass,Timeslot> hash = new HashMap<CourseClass,Timeslot>();
        hash.put(c1, chro.getList().get(2));
        hash.put(c2, chro.getList().get(15));
        
        Schedule schedule = new Schedule(hash,2,0);
        school.getRoomList().remove(r1);
        school.getClassList().remove(c1);
        school.getClassList().remove(c2);
        assertEquals(0.833333333, schedule.getFitness(),1.0E-7);
    }
	
    @Test
    public void testFitness2() {
        School school = School.getInstance();
        
        CourseClass c1 = new CourseClass(1,"Algorithm",50,2);
        CourseClass c2 = new CourseClass(2,"Database",80,3);
        CourseClass c3 = new CourseClass(3,"Object-Oriented Design",100,3);
        CourseClass c4 = new CourseClass(4,"Big Data",60,4);
        school.addCourseClass(c1);
        school.addCourseClass(c2);
        school.addCourseClass(c3);
        school.addCourseClass(c4);
        
        Classroom r1 = new Classroom(3,80);
        school.addClassroom(r1);
        Chromosome chro = Chromosome.getInstance();
        chro.setList();

        HashMap<CourseClass,Timeslot> hash = new HashMap<CourseClass,Timeslot>();
        hash.put(c1, chro.getList().get(0));
        hash.put(c2, chro.getList().get(15));
        hash.put(c3, chro.getList().get(16));
        hash.put(c4, chro.getList().get(50));
        
        Schedule schedule = new Schedule(hash,2,0);
        school.getRoomList().remove(r1);
        school.getClassList().remove(c1);
        school.getClassList().remove(c2);
        school.getClassList().remove(c3);
        school.getClassList().remove(c4);
        assertEquals(0.666666666, schedule.getFitness(),1.0E-7);
    }
    
    @Test
    public void testFitness3() {
        School school = School.getInstance();
        
        CourseClass c1 = new CourseClass(1,"Algorithm",50,4);
        CourseClass c2 = new CourseClass(2,"Database",80,3);
        school.addCourseClass(c1);
        school.addCourseClass(c2);
        
        Classroom r1 = new Classroom(3,30);
        school.addClassroom(r1);
        Chromosome chro = Chromosome.getInstance();
        chro.setList();

        HashMap<CourseClass,Timeslot> hash = new HashMap<CourseClass,Timeslot>();
        hash.put(c1, chro.getList().get(24));
        hash.put(c2, chro.getList().get(25));
        
        Schedule schedule = new Schedule(hash,2,0);
        school.getRoomList().remove(r1);
        school.getClassList().remove(c1);
        school.getClassList().remove(c2);
        assertEquals(0.0, schedule.getFitness(),1.0E-7);
    }
    
}
