package itmo.labs.zavar.commands;

import java.util.HashMap;
import java.util.Stack;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.StudyGroup;

public class RemoveByIDCommand extends Command 
{
	private static RemoveByIDCommand command;
	
	private RemoveByIDCommand() 
	{
		super("remove_by_id", "id");
	}

	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new RemoveByIDCommand();
		commandsMap.put(command.getName(), command);
	}
	
	@Override
	public void execute(HashMap<String, Command> map, Stack<StudyGroup> stack, Object[] args) throws CommandException 
	{
		if(args.length != 1)
		{
			throw new CommandArgumentException("This command requires id of element only!\n" + getUsage());
		}
		else
		{
			int id;
			try
			{
				id = Integer.parseInt((String) args[0]);
			}
			catch(NumberFormatException e)
			{
				throw new CommandArgumentException("ID must be a number!\n" + getUsage());
			}
			if(stack.size() > 0)
			{
				stack.remove(stack.stream().filter(e -> id == e.getId()).findAny().get());
				System.out.println("Element deleted!");
			}
			else
			{
				System.out.println("Collection is empty!");
			}
		}
	}

	@Override
	public String getHelp() 
	{
		return "This command removes one element from collection by ID!";
	}

}
