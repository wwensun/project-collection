/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneticAlgorithm;

import GeneticModel.Chromosome;
import GeneticModel.Classroom;
import GeneticModel.CourseClass;
import GeneticModel.School;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author jenni
 */
public class ChromosomeTest {
	
    @Test
    public void testChromosome0() {
        School school = School.getInstance();
        
        Classroom r1 = new Classroom(1,30);
        Classroom r2 = new Classroom(2,50);
        school.addClassroom(r1);
        school.addClassroom(r2);
        
        Chromosome chro = Chromosome.getInstance();
        chro.setList();
        school.getRoomList().clear();
        assertEquals(26*5, chro.getList().size());
        assertEquals(4, chro.getList().get(40).getDay());
        assertEquals(8, chro.getList().get(52).getStart());
    }
    
    @Test
    public void testChromosome1() {
        School school = School.getInstance();
        
        Classroom r1 = new Classroom(1,30);
        school.addClassroom(r1);
        
        Chromosome chro = Chromosome.getInstance();
        chro.setList();
        school.getRoomList().clear();
        assertEquals(13*5, chro.getList().size());
        assertEquals(3, chro.getList().get(27).getDay());
        assertEquals(20, chro.getList().get(64).getStart());
    }
    
}
