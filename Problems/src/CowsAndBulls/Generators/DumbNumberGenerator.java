package CowsAndBulls.Generators;

public class DumbNumberGenerator implements ISecretGenerator{
	
	@Override
	public String generate(int secretLength)
	{
		String number = "";
		for(int i=0;i<=9 && number.length()<secretLength;i++)
		{
			Character digitChar = (char)(i+48);
			if(!containsDigit(number, digitChar) && decideRandom())
			{
				number += digitChar;
			}
			
			if(i==9) i=0;
		}
		return number;
	}
	
	private Boolean containsDigit(String number, char digit)
	{
		return number.indexOf(digit) != -1; 
	}
	
	private Boolean decideRandom()
	{
		double decisionRandom = Math.random();
		if(decisionRandom >= 0 && decisionRandom<0.5)
			return true;
		else
			return false;
	}	
}
