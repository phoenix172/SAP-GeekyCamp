package Algorithms.Genetic;

public class NumbersHelper {
	public static int generateRandomBetween(int fromInclusive, int toInclusive)
	{
		int max = (toInclusive+1)-fromInclusive;
		if(max == 0)
			max = 1;
		int number = (int)(Math.random() * getMultiplier(max)) % max;
		return fromInclusive+number;
	}
	
	private static int getMultiplier(int max)
	{
		int result = 10;
		while(max > 0)
		{
			max /= 10;
			result *= 10;
		}
		return result;
	}
}
