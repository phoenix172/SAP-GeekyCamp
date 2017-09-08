package Algorithms.Genetic;

import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumbersLand {

	private static final int tournamentSize = 3;
	private static final int populationSize = 1000;
	private static final double breedProportion = .2;
	private static final double mutationProportion = .2;

	private double target;

	List<Specimen> population;

	public NumbersLand(double target) {
		this.target = target;
	}

	public void populate() {
		population = new ArrayList(Arrays.asList(new Specimen[populationSize])
				.stream().map(x->new Specimen(target)).collect(Collectors.toList()));
	}

	public Optional<Specimen> checkForSolution() {
		return population.stream().filter(x -> x.isSolution()).findFirst();
	}

	public void crossover() {
		int breedCount = (int) (populationSize * breedProportion);
		for (int i = 0; i < breedCount; i++) {
			Specimen[] parents = tournamentSelect(10, 2);
			population.add(parents[0].breed(parents[1]));
			population.add(parents[1].breed(parents[0]));
		}
	}

	public void mutation() {
		int mutationsCount = (int) (populationSize * mutationProportion);
		Specimen[] mutees = tournamentSelect(mutationsCount * 3, mutationsCount);
		for (Specimen mutee : mutees) {
			mutee.mutate();
		}
	}

	public void survivorSelection() {
		population = new ArrayList(Arrays.asList(tournamentSelect(population.size(), populationSize)));
	}

	private Specimen getRandomSpecimen() {
		return population.get(NumbersHelper.generateRandomBetween(0, population.size() - 1));
	}

	private Specimen[] tournamentSelect(int tournamentSize, int winnersCount) {
		return Arrays.stream(new int[tournamentSize]).mapToObj(x -> getRandomSpecimen())
				.sorted((a, b) -> ((Double) a.fitness()).compareTo(b.fitness())).limit(winnersCount)
				.toArray(Specimen[]::new);
	}
	
	public Collection<Specimen> population() {
		return Collections.unmodifiableCollection(population);
	}
}
