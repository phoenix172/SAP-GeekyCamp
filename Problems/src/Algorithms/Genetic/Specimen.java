package Algorithms.Genetic;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Specimen {

	// {0}/{1} + {2}*{3} - {4}
	// random min = {5}
	// random max = {6}

	private static final int genesCount = 7;

	private int[] genes;
	private double fitness;
	private double target;

	public Specimen(double target) {
		this(target, generateGenes());
	}

	public Specimen(double target, int[] genes) {
		this.target = target;
		this.genes = genes;
		calculateFitness();
	}

	private double calculate() {
		return (double) genes[0] / genes[1] + genes[2] * genes[3] - genes[4];
	}

	private void calculateFitness() {
		 fitness = 1 / (Math.abs(target - calculate()) + 1);
	}

	public double fitness() {
		return this.fitness;
	}

	public boolean isSolution() {
		return this.fitness == 1;
	}

	public Specimen breed(Specimen mate) {
		int[] newGenes = new int[genesCount];
		for (int i = 0; i < genesCount / 2 - 2; i++)
			newGenes[i] = mate.genes[i];
		for (int i = genesCount / 2 - 2; i < genesCount-2; i++)
			newGenes[i] = genes[i];
		newGenes[5] = mate.genes[5];
		newGenes[6] = this.genes[6];
		return new Specimen(target, newGenes);
	}

	public void mutate() {
		int direction = fitness < 1 ? -1 : 1;
		mutateRandomMetaGeneInDirection(direction);
		mutateRandomGeneInDirection(direction);
		calculateFitness();
		int p = 1;
	}
	
	private void mutateRandomGeneInDirection(int direction)
	{
		int geneIndex = NumbersHelper.generateRandomBetween(0, 4);
		mutateGeneInDirection(geneIndex, direction);
	}
	
	private void mutateRandomMetaGeneInDirection(int direction)
	{
		int geneIndex = NumbersHelper.generateRandomBetween(5, 6);
		mutateGeneInDirection(geneIndex, direction);
		if(genes[5]>genes[6])
		{
			int temp = genes[5];
			genes[5] = genes[6];
			genes[6] = temp;
		}
	}
	
	private void mutateGeneInDirection(int geneIndex, int direction)
	{
		if(geneIndex == 1 || geneIndex == 4)direction *= -1;
		if(direction == -1)
			genes[geneIndex] = NumbersHelper.generateRandomBetween(1, genes[5]);
		else
			genes[geneIndex] = NumbersHelper.generateRandomBetween(genes[6], 100);
	}

	private static int[] generateMetaGenes()
	{
		int a= NumbersHelper.generateRandomBetween(1, 100);
		int b = NumbersHelper.generateRandomBetween(1, 100);
		if(Math.abs(a-b)<2)return generateMetaGenes();
		return new int[] {Math.min(a, b), Math.max(a, b)};
	}
	
	private int generateGene()
	{
		return generateGene(new int[] {genes[5], genes[6]});
	}
	
	private static int generateGene(int[] metaGenes) {
		int result = NumbersHelper.generateRandomBetween(metaGenes[0], metaGenes[1]);
		return result;
	}

	private static int[] generateGenes() {
		int[] metaGenes = generateMetaGenes();
		int[] newGenes = IntStream.range(0, genesCount)
				.map(x->Specimen.generateGene(metaGenes)).toArray();
		newGenes[5] = metaGenes[0];
		newGenes[6] = metaGenes[1];
		return newGenes;
	}

	@Override
	public String toString() {
		return String.format("%d/%d + %d*%d - %d = %4.3f, meta-min: %d, meta-max: %d, fitness: %4.9f, isSolution: %b",
				genes[0],genes[1],genes[2],genes[3],genes[4], calculate() ,genes[5], genes[6], fitness, isSolution());
	}
}
