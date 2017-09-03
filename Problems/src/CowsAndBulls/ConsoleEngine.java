package CowsAndBulls;

import java.util.Scanner;

import CowsAndBulls.Core.Game;
import CowsAndBulls.Core.TurnResult;
import CowsAndBulls.Generators.ISecretGenerator;
import CowsAndBulls.Generators.SecretGeneratorFactory;

public class ConsoleEngine {
	private final String DEFAULT_GENERATOR = "smart-number";
	
	private SecretGeneratorFactory generatorFactory;
	private Scanner scanner;
	private Game game;
	
	public ConsoleEngine(Scanner scanner)
	{
		this.scanner = scanner;
		generatorFactory = new SecretGeneratorFactory();
	}
	
	public void Run()
	{
		initializeGame();		
		do
		{
			game.newGame();
			doDebug();			
			playGame();
		}
		while(shouldStartNewGame());
	}

	private void doDebug() {
		System.out.println(game.getSecret());
	}

	private void initializeGame() {
		ISecretGenerator generator = chooseGenerator(DEFAULT_GENERATOR);
		game = new Game(generator);
		configureGuessLength();
	}

	private void configureGuessLength() {
		int defaultGuessLength = game.getGuessLength();
		int guessLength = chooseGuessLength(defaultGuessLength);
		game.setGuessLength(guessLength);
	}
	
	private void playGame() {
		System.out.printf("Начало на играта. Дължина на тайната: %d\n", game.getGuessLength());
		while(game.isRunning())
		{
			String guess = scanner.nextLine();		
			TurnResult result = game.takeGuess(guess);
			System.out.println(result.toString());
			if(result.isWin()) break;
		}
	}
	
	private Boolean shouldStartNewGame()
	{
		System.out.println("Нова игра? (no)");
		String input = scanner.nextLine();
		return input.toLowerCase().equals("yes");
	}
	
	private int chooseGuessLength(int defaultGuessLength)
	{
		System.out.printf("Въведете дължина на тайната(%d): \n", defaultGuessLength);
		
		String input = getValueOrDefault(scanner.nextLine(),
				((Integer)defaultGuessLength).toString());
		
		int guessLength;
		try
		{
			guessLength = Integer.parseInt(input);
		}
		catch(NumberFormatException ex)
		{
			System.out.println("Невалиден избор");
			guessLength = chooseGuessLength(defaultGuessLength);
		}
		
		return guessLength;
	}
	
	private ISecretGenerator chooseGenerator(String defaultGenerator)
	{
		System.out.printf("Изберете генератор(%s). Възможни опции: \n", defaultGenerator);		
		printGenerators();
		String generatorName = getValueOrDefault(scanner.nextLine(), defaultGenerator);
		ISecretGenerator result = generatorFactory.Get(generatorName);
		if(result == null)
		{
			System.out.println("Невалиден избор");
			result = chooseGenerator(defaultGenerator);
		}
		return result;
	}

	private void printGenerators() {
		String[] generatorNames =  generatorFactory.GetAllNames();
		for(String name : generatorNames)
		{
			System.out.printf("\t%s\n", name);
		}
	}

	private String getValueOrDefault(String value, String defaultValue) {
		if(value.isEmpty())
			return defaultValue;
		else
			return value;
	}
}
