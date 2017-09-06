package StudentsDB.CommandHandlers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import javax.activity.InvalidActivityException;

public class CommandHandler {
	
	private String[] arguments;
	private Map<String, SQLCheckedAction> actions;
	
	public CommandHandler()
	{
		this.actions = new HashMap<String, SQLCheckedAction>();
		addAction("help", this::helpAction);
	}
	
	protected void addAction(String name, SQLCheckedAction action)
	{
		actions.put(name, action);
	}
	
	protected void helpAction()
	{
		System.out.println("Available commands: ");
		actions.keySet().stream().forEach(System.out::println);
	}
	
	public void handleCommand(String[] arguments)
	{
		this.arguments = arguments;
		try
		{
			String action = this.arguments[0];
			actions.get(action).run();
		}
		catch (Exception ex)
		{
			//System.out.println("Error: invalid command");
			//System.out.print("Additional info:");
			ex.printStackTrace(System.out);
			helpAction();
		}
	}
		
	protected Optional<Integer> getIdArgument() {
		return getIntArgument("i");
	}
	
	protected Optional<Integer> getIntArgument(String arg) {
		Optional<String> argument = getArgument(arg);
		if (argument.isPresent()) {
			int id = Integer.parseInt(argument.get());
			return Optional.of(id);
		} else {
			return Optional.empty();
		}
	}
	
	protected Optional<String> getArgument(String name) {
		List<String> argList = Arrays.asList(arguments);
		int argIndex = argList.indexOf(getArgumentName(name));
		if (argIndex != -1)
			return Optional.of(argList.get(argIndex + 1));
		else
			return Optional.empty();
	}	

	protected String[] getArguments()
	{
		return arguments;
	}
	
	private String getArgumentName(String argument) {
		return String.format("-%s", argument);
	}
}
