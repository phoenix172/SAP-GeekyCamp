package CowsAndBulls.Core;

public class TurnResult
{
	private int cows, bulls;

	public TurnResult(int cows, int bulls)
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