package CowsAndBulls.Core;

import java.util.*;

import CowsAndBulls.Generators.ISecretGenerator;

public class Game {
	
	private Boolean isRunning = false;
	private String secret;
	private int guessLength = 4;
	private ISecretGenerator generator;
	
	public Game(ISecretGenerator generator)
	{
		this.generator = generator;
	}
	
	public void newGame()
	{
		isRunning = true;
		secret = generator.generate(guessLength);
	}
	
	public String getSecret()
	{
		return secret;
	}
	
	public Boolean isRunning()
	{
		return isRunning;
	}
	
	public void setGuessLength(int newLength)
	{
		guessLength = newLength;
	}
	
	public int getGuessLength()
	{
		return guessLength;
	}
	
	public TurnResult takeGuess(String guess)
	{
		if(!isRunning) return new NullTurnResult();
		TurnResult result = playTurn(guess);
		isRunning = !result.isWin();
		return result;
	}
	
	private TurnResult playTurn(String guess)
	{
		int cows = 0, bulls = 0;
		for(int i =0;i<secret.length();i++)
		{
			for(int j = 0;j<guess.length();j++)
			{
				if(secret.charAt(i) == guess.charAt(j))
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
		
		TurnResult result = new TurnResult(cows, bulls);
		return result;
	}
}
