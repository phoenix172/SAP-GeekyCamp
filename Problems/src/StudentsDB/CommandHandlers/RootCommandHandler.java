package StudentsDB.CommandHandlers;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import javax.activity.InvalidActivityException;

public class RootCommandHandler extends CommandHandler{

	public void AddHandler(String name, CommandHandler handler)
	{
		addAction(name, ()->invokeHandler(handler));
	}
	
	public void handleCommand(String command)
	{
		this.handleCommand(command.split(" "));
	}
	
	private void invokeHandler(CommandHandler handler)
	{
		String[] arguments = Arrays.asList(getArguments())
		.stream().skip(1).toArray(String[]::new);
		handler.handleCommand(arguments);
	}
}
