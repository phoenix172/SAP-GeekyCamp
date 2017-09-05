package StudentsDB.CommandHandlers;

import java.util.List;
import java.util.Scanner;

import StudentsDB.Entities.Student;

public abstract class EntityCommandHandler<T> extends CommandHandler {

	protected final Scanner scanner;
	
	public EntityCommandHandler(Scanner scanner) {
		this.scanner = scanner;
	}
	
	@Override
	protected void finalize()
	{
		this.scanner.close();
	}
	
	protected String promptString(String name, String current)
	{
		if(current == null)
			System.out.printf("%s: ", name);
		else
			System.out.printf("%s(%s): ", name, current);
		
		String line = scanner.nextLine();
		if(line.isEmpty())
			return current;
		else
			return line;
	}
	
	protected Integer promptInt(String name, Integer current)
	{
		if(current == 0)
			System.out.printf("%s: ", name);
		else
			System.out.printf("%s(%d): ", name, current);
		
		String line = scanner.nextLine();
		if(line.isEmpty())
			return current;
		else
			return Integer.parseInt(line);
	}
	
	protected void Print(List<T> entities) {
		entities.forEach(System.out::println);
	}
	
	protected abstract T scanEntity(T template);
}
