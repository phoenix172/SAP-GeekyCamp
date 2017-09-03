package CowsAndBulls.Generators;

public class SmartNumberGenerator implements ISecretGenerator{

	@Override
	public String generate(int secretLength) {
		StringBuilder number = new StringBuilder();
		for(int i =0;i<secretLength;i++)
		{
			char digit;
			do
			{
				digit = (char)generateDigit();
			}
			while(number.indexOf(digit+"")==-1);			
		}
		return number.toString();
	}
	
	public int generateDigit()
	{
		double result = Math.random();
		return (int)result*10;
	}

}
