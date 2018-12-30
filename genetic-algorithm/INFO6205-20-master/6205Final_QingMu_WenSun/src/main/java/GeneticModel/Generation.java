package GeneticModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

public class Generation {
    public Generation(int number) {
        generation = new ArrayList<Schedule> ();
        this.scheduleNumber = number;
        generationID = count;
        count++;
    }

    public Generation crossover() {
        Generation next = new Generation(scheduleNumber);
        //System.out.println("Crossover: " + next.getGenerationID());
        ArrayList<Schedule> parents = this.findTopTenPercent();
        //20 kids to be born per pair
        while(next.getGeneration().size()<next.getScheduleNumber())
        {
            int f = (int)(Math.random()*parents.size());
            int m = (int)(Math.random()*parents.size());
            while(f==m)
            {
                m=(int)(Math.random()*parents.size());
            }
            next.getGeneration().add(crossover(parents,f,m));
            next.getGeneration().add(crossover(parents,m,f));
        }
        return next;
    }

    private Schedule crossover(ArrayList<Schedule> parents,int f,int m)
    {
        School school = School.getInstance();
        Schedule father = parents.get(f);
        //printS(father);
        Schedule mother = parents.get(m);
        //printS(mother);

        int b1 = (int)(Math.random()*father.getHash().size());
        int b2 = (int)(Math.random()*father.getHash().size());
        while(b2==b1)
            b2 = (int)(Math.random()*father.getHash().size());

        if(b2<b1)
        {
            b1=b1+b2;
            b2=b1-b2;
            b1=b1-b2;
        }

        HashMap<CourseClass,Timeslot> kidHash = new HashMap<CourseClass,Timeslot>();
        int i = 0;
        for(;i<b1;i++)
            kidHash.put(school.getClassList().get(i), father.getHash().get(school.getClassList().get(i)));
        for(;i<b2;i++)
            kidHash.put(school.getClassList().get(i), mother.getHash().get(school.getClassList().get(i)));
        for(;i<father.getHash().size();i++)
            kidHash.put(school.getClassList().get(i), father.getHash().get(school.getClassList().get(i)));

        mutate(kidHash,(father.getMutationProbability()+mother.getMutationProbability())/2);
        Schedule kid = new Schedule(kidHash,father.getCrossoverPoints(),(father.getMutationProbability()+mother.getMutationProbability())/2);
        //printS(kid);
        return kid;
    }

    private void printS(Schedule s)
    {
        HashMap<CourseClass, Timeslot> hash = s.getHash();
         for(Entry<CourseClass, Timeslot> e : hash.entrySet()) {
            String day = "";
            switch(e.getValue().getDay()) {
                case 1: day="Monday";break;
                case 2: day="Tuesday";break;
                case 3: day="Wednesday";break;
                case 4: day="Thursday";break;
                case 5: day="Friday";break;
            }
            int time1 = e.getValue().getStart();
            int time2 = e.getValue().getStart() + e.getKey().getDuration();

            System.out.println("Course Class: " + e.getKey().toString());
            System.out.println("    Time: " + day + "  " + time1 + ":00 - " + time2 + ":00");
            System.out.println("    Location: " + "Room " + e.getValue().getClassroom().getRoomID());
            System.out.println("    Student Count: " + e.getKey().getStudentNumber());
            System.out.println("    Room Capacity: " + e.getValue().getClassroom().getSeats());
        }
    }

    private void mutate(HashMap<CourseClass,Timeslot> kid,double prob)
    {
        Chromosome chro = Chromosome.getInstance();
        if(Math.random()<prob)
        {
            int mut = (int)(Math.random()*(kid.size()));
            int val = (int)(Math.random()*(chro.getList().size()));
            int i=0;
            for(Entry<CourseClass,Timeslot> vo : kid.entrySet()){ 
                if(i==mut)
                {
                    kid.put(vo.getKey(),chro.getList().get(val));
                    break;
                }   
                else
                    i++;
            }
        }
    }

    public ArrayList<Schedule> findTopTenPercent() {
        ArrayList<Schedule> topten  = new ArrayList<Schedule>();
        int num = scheduleNumber/10;
        //TODO
        Collections.sort(generation,new FitnessComparator());
        for(int i= 0;i<num;i++)
            topten.add(generation.get(i));
        return topten;
    }

    public double getAverageFitness() {
        double total = 0;
        double count = scheduleNumber;
        for(Schedule s: generation) {
            total += s.getFitness();
        }
        return total/count;
    }
    
    public double getAverageMutationProbability( ) {
    	double total = 0;
        double count = scheduleNumber;
        for(Schedule s: generation) {
            total += s.getMutationProbability();
        }
        return total/count;
    }
    
    public ArrayList<Schedule> getGeneration() {
        return generation;
    }

    public int getScheduleNumber() {
        return scheduleNumber;
    }
    
    public int getGenerationID() {
    	return this.generationID;
    }

	private ArrayList<Schedule> generation;
	private int generationID;
        private static int count=0;
	private int scheduleNumber; // number of schedules in the generation.
}

class FitnessComparator implements Comparator<Schedule>{
    public int compare(Schedule s1, Schedule s2) {
        if(s1.getFitness()<s2.getFitness())
            return 1;
        else if(s1.getFitness()==s2.getFitness())
            return 0;
        else
            return -1;
    }           
}