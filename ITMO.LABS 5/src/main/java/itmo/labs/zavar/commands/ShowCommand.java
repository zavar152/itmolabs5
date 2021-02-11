package itmo.labs.zavar.commands;

import java.util.HashMap;
import java.util.Stack;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.StudyGroup;

public class ShowCommand extends Command
{
	private static ShowCommand command;
	
	private ShowCommand() 
	{
		super("show");
	}

	@Override
	public void execute(HashMap<String, Command> map, Stack<StudyGroup> stack, Object[] args) throws CommandException 
	{
		if(args.length > 0)
		{
			throw new CommandArgumentException("This command doesn't require any arguments!\n" + getUsage());
		}
		else
		{
			System.out.println(stack.toString());
		}
	}

	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new ShowCommand();
		commandsMap.put(command.getName(), command);
	}
	
	@Override
	public String getHelp() 
	{
		return "This command shows all elements of collection!";
	}
}
