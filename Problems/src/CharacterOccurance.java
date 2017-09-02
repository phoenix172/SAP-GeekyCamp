import java.util.*;

public class CharacterOccurance {
	public static void main(String[] arguments)
	{
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();
		
		char prev = 0;
		int maxCount = 0;
		int currentCount = 1;
		for(char c : input.toCharArray())
		{
			if(c == prev)
			{
				currentCount++;
			}
			else
			{
				maxCount = Math.max(currentCount, maxCount);
				currentCount = 1;
			}
			prev = c;
		}
		maxCount = Math.max(currentCount, maxCount);
		System.out.println(maxCount);
	}
}
