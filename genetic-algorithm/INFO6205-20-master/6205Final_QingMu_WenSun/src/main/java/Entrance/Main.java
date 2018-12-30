package Entrance;

import GeneticModel.Generation;

import Configuration.Configuration;
import GeneticModel.School;
import Log.Log;

public class Main {
	
    public static void main( String[] args )
    {
        Generation g0 = Configuration.configureFirstGeneration();
        Generation previousGeneration = g0;
        
        while(School.findflag == 0) {
            Generation nextGeneration = previousGeneration.crossover();
            //System.out.println("Generation: " + nextGeneration.getGenerationID());
            //System.out.println("    Average Fitness: " + nextGeneration.getAverage());
            Log.info("Generation: " + nextGeneration.getGenerationID());
            Log.info("    Average Fitness: " + nextGeneration.getAverageFitness());
            Log.info("    Average Mutation Probability: " + nextGeneration.getAverageMutationProbability());
            previousGeneration = nextGeneration;
        }
        
    }
	
}
