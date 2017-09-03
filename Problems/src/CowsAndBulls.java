
import java.util.*;


public class CowsAndBulls {

	String generatedNumber;
	
	public class CowBullResult
	{
		private int cows, bulls;

		public CowBullResult(int cows, int bulls)
		{
			this.cows = cows;
			this.bulls = bulls;
		}
		
		public int getCows() 
		{
			return cows;
		}
		
		public int GetBulls()
		{
			return bulls;
		}
		
		public Boolean isWin()
		{
			return bulls == 4;
		}
		
		@Override
		public String toString()
		{
			StringBuilder result = new StringBuilder();
			
			if(bulls != 0)
			{
				String bullsString = String.format("%d %s", bulls, bulls==1?"бик":"бика");
				result.append(bullsString);
				if(bulls == 4)
				{
					result.append(", печелиш, как се сети?");
				}
			}
			
			if(cows != 0 || result.length() == 0)
			{
				if(bulls != 0)
				{
					result.append(" и ");
				}
				String cowsString = String.format("%d %s", cows, cows==1?"крава":"крави");
				result.append(cowsString);
			}				
			
			return result.toString();
		}
	}
	
	public static void main(String[] arguments)
	{
		Scanner scanner = new Scanner(System.in);
		
		CowsAndBulls game = new CowsAndBulls();
		while(true)
		{
			String guess = scanner.nextLine();		
			CowBullResult result = game.takeGuess(guess);
			System.out.println(result.toString());
			if(result.isWin())break;
		}
		scanner.close();
	}
	
	public CowsAndBulls()
	{
		generatedNumber = generateNumber();
		//System.out.println(generatedNumber);
	}
	
	public CowBullResult takeGuess(String guess)
	{
		return getCowsAndBulls(guess);
	}
	
	private CowBullResult getCowsAndBulls(String guess) 
	{
		int cows = 0, bulls = 0;
		for(int i =0;i<generatedNumber.length();i++)
		{
			for(int j = 0;j<guess.length();j++)
			{
				if(generatedNumber.charAt(i) == guess.charAt(j))
				{
					if(i == j)
					{
						bulls++;
					}
					else
					{
						cows++;
					}
				}
			}
		}
		CowBullResult result = new CowBullResult(cows, bulls);
		return result;
	}
	
	private String generateNumber()
	{
		String numbers = "";
		for(int i=0;i<=9 && numbers.length()<4;i++)
		{
			Character digit = new Character((char)(i+48));
			
			if(numbers.indexOf(digit) == -1 && decide())
			{
				numbers += digit;
			}
			
			if(i==9)
			{
				i=0;
			}
		}
		return numbers;
	}
	
	public Boolean decide()
	{
		double decisionRandom = Math.random();
		if(decisionRandom >= 0 && decisionRandom<0.5)
			return true;
		else
			return false;
	}	
}
