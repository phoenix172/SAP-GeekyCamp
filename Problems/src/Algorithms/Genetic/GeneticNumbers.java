package Algorithms.Genetic;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;


public class GeneticNumbers {
	private static NumbersLand numbersLand;
	private static int currentGeneration = 0;
	
	public static void main(String[] arguments) {
		Scanner scanner = new Scanner(System.in);
		numbersLand = createWorld(scanner);
		printGeneration();
		
		Optional<Specimen> solution;
		while(!(solution = numbersLand.checkForSolution()).isPresent()) {
			printGeneration();
			numbersLand.crossover();
			numbersLand.mutation();
			numbersLand.survivorSelection();
			currentGeneration++;
			scanner.nextLine();
		}
		
		scanner.close();
	}

	private static NumbersLand createWorld(Scanner scanner) {
		System.out.print("Enter target: ");
		double target = scanner.nextDouble();
		
		NumbersLand world = new NumbersLand(target);
		world.populate();
		return world;
	}
	
	private static void printGeneration()
	{
		System.out.println("--------------------");
		System.out.printf("Generation: %d\n", currentGeneration);
		numbersLand.population().forEach(System.out::println);
		System.out.println("--------------------");
	}
}
