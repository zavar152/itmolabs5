package itmo.labs.zavar.commands;

import java.util.HashMap;
import java.util.Stack;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.StudyGroup;

public class InfoCommand extends Command
{
	private static InfoCommand command;
	
	public InfoCommand() 
	{
		super("info");
	}

	@Override
	public void execute(HashMap<String, Command> map, Stack<StudyGroup> stack, Object[] args)
			throws CommandException 
	{
		if(args.length > 0)
		{
			throw new CommandArgumentException("This command doesn't require any arguments!\n" + getUsage());
		}
		else
		{
			System.out.println("Type: " + stack.getClass().getName());
			System.out.println("Creation date: ");
			System.out.println("Count of elements: " + stack.size());
		}
	}

	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new InfoCommand();
		commandsMap.put(command.getName(), command);
	}
	
	@Override
	public String getHelp() 
	{
		return "This command shows information about the collection!";
	}
}
