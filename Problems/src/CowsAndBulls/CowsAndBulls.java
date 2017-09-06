package CowsAndBulls;

import java.util.Scanner;

public class CowsAndBulls {

	public static void main(String[] arguments)
	{
		Scanner scanner = new Scanner(System.in);
		ConsoleEngine engine = new ConsoleEngine(scanner);
		engine.run();
		scanner.close();
	}
}
