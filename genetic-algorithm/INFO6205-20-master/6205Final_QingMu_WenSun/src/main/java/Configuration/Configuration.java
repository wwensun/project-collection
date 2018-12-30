package Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import GeneticModel.Chromosome;
import GeneticModel.Classroom;
import GeneticModel.CourseClass;
import GeneticModel.Generation;
import GeneticModel.Schedule;
import GeneticModel.School;
import GeneticModel.Timeslot;
import Log.Log;

public class Configuration {
	public Configuration() {
		
	}
	
	public static Generation configureFirstGeneration() {
		School school = School.getInstance();
		
		CourseClass c1 = new CourseClass(1,"Algorithm",50,2);
		CourseClass c2 = new CourseClass(2,"Database",80,3);
		CourseClass c3 = new CourseClass(3,"Object-Oriented Design",100,3);
		CourseClass c4 = new CourseClass(4,"Big Data",60,2);
                CourseClass c5 = new CourseClass(5,"Web Design",80,3);
                CourseClass c6 = new CourseClass(6,"Web Tools",60,3);
                CourseClass c7 = new CourseClass(7,"User Experience Design",90,2);
                CourseClass c8 = new CourseClass(8,"Application Engineering Development - session1",200,3);
                CourseClass c9 = new CourseClass(9,"Application Engineering Development - session2",200,3);
                CourseClass c10 = new CourseClass(10,"Cloud Computing - Session 1",90,3);
                CourseClass c11 = new CourseClass(11,"Cloud Computing - Session 2",90,3);
                CourseClass c12 = new CourseClass(12,"SmartPhone Application Development",50,2);
                CourseClass c13 = new CourseClass(13,"Linear Algebra - Session 1",150,3);
                CourseClass c14 = new CourseClass(14,"Linear Algebra - Session 2",150,3);
                CourseClass c15 = new CourseClass(15,"Advanced Mathematics - Session 1",150,3);
                CourseClass c16 = new CourseClass(16,"Advanced Mathematics - Session 2",150,3);
                CourseClass c17 = new CourseClass(17,"Data Warehousing and Business Intelligence",100,3);
                CourseClass c18 = new CourseClass(18,"Concurrent and Parallel Programming",60,3);
                CourseClass c19 = new CourseClass(19,"Business Analysis and Information Engineering",100,2);
                CourseClass c20 = new CourseClass(20,"Software Quality Control",50,3);
                CourseClass c21 = new CourseClass(21,"Advanced Application Engineering - Session 1",40,3);
                CourseClass c22 = new CourseClass(22,"Advanced Application Engineering - Session 2",40,3);
                CourseClass c23 = new CourseClass(23,"Agile Software Development",80,3);
                CourseClass c24 = new CourseClass(24,"Organizational Change and IT",30,3);
                CourseClass c25 = new CourseClass(25,"Advances in Data Science and Architecture",160,3);
		school.addCourseClass(c1);
		school.addCourseClass(c2);
		school.addCourseClass(c3);
		school.addCourseClass(c4);
                school.addCourseClass(c5);
                school.addCourseClass(c6);
                school.addCourseClass(c7);
                school.addCourseClass(c8);
                school.addCourseClass(c9);
                school.addCourseClass(c10);
                school.addCourseClass(c11);
                school.addCourseClass(c12);
                school.addCourseClass(c13);
                school.addCourseClass(c14);
                school.addCourseClass(c15);
                school.addCourseClass(c16);
                school.addCourseClass(c17);
                school.addCourseClass(c18);
                school.addCourseClass(c19);
                school.addCourseClass(c20);
		
		Classroom r1 = new Classroom(1,60);
		Classroom r2 = new Classroom(2,80);
                Classroom r3 = new Classroom(3,200);
                //Classroom r4 = new Classroom(4,120);
		school.addClassroom(r1);
		school.addClassroom(r2);
                school.addClassroom(r3);
                //school.addClassroom(r4);
		
		Chromosome chromosome = Chromosome.getInstance();
		chromosome.setList();
           
		
		Generation g0 = new Generation(100);
		//System.out.println("Generation: " + g0.getGenerationID());
		Log.info("Generation: " + g0.getGenerationID());
		for(int i=0; i<g0.getScheduleNumber(); i++) {
			HashMap<CourseClass,Timeslot> hash = new HashMap<CourseClass,Timeslot>();
			ArrayList<CourseClass> classList = school.getClassList();
			for(CourseClass course: classList) {
				int randSlot = (int) (Math.random()*chromosome.getList().size());
				hash.put(course, chromosome.getList().get(randSlot));
			}
			double mutationProbability = 0.3 + Math.random()*0.2;
			Schedule schedule = new Schedule(hash,2,mutationProbability);
			//System.out.println(i + ": " + schedule.getFitness());
                        
			for(Entry<CourseClass, Timeslot> e : hash.entrySet()) {
				String day = "";
				switch(e.getValue().getDay()) {
					case 1: day="Monday"; break;
					case 2: day="Tuesday";break;
					case 3: day="Wednesday";break;
					case 4: day="Thursday";break;
					case 5: day="Friday";break;
				}
				int time1 = e.getValue().getStart();
				int time2 = e.getValue().getStart() + e.getKey().getDuration();
			}
			
			g0.getGeneration().add(schedule);
		}

		//System.out.println("    Average Fitness: " + g0.getAverageFitness());
		Log.info("    Average Fitness: " + g0.getAverageFitness());
		//System.out.println("    Average Mutation Probability: " + g0.getAverageMutationProbability());
		Log.info("    Average Mutation Probability: " + g0.getAverageMutationProbability());
		return g0;
	}
}
