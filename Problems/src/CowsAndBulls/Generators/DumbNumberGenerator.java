package CowsAndBulls.Generators;

public class DumbNumberGenerator implements ISecretGenerator{
	
	@Override
	public String generate(int secretLength)
	{
		String number = "";
		for(int i=0;i<=9 && number.length()<secretLength;i++)
		{
			Character digitChar = (char)(i+'0');
			if(!containsDigit(number, digitChar) && decideRandom())
			{
				number += digitChar;
			}
			
			if(i==9) i=0;
		}
		return number;
	}
	
	private boolean containsDigit(String number, char digit)
	{
		return number.indexOf(digit) != -1; 
	}
	
	private boolean decideRandom()
	{
		double decisionRandom = Math.random();
		return decisionRandom >= 0 && decisionRandom<0.5;
	}	
}
