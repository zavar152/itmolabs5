package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.NoSuchElementException;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.exception.CommandRunningException;

public class RemoveAnyBySCCommand extends Command
{
	private static RemoveAnyBySCCommand command;
	
	private RemoveAnyBySCCommand()
	{
		super("remove_any_by_students_count", "students_count");
	}
	
	@Override
	public int execute(Environment env, Object[] args, InputStream inStream, OutputStream outStream) throws CommandException 
	{
		if(args.length != 1)
		{
			throw new CommandArgumentException("This command requires one argument!\n" + getUsage());
		}
		else
		{
			long sc;
			try
			{
				sc = Long.parseLong((String) args[0]);
			}
			catch(NumberFormatException e)
			{
				throw new CommandArgumentException("Students count shold be a long type!");
			}
			
			if(env.getCollection().isEmpty())
			{
				throw new CommandRunningException("Collection is empty!");
			}
			
			try
			{
				env.getCollection().remove(env.getCollection().stream().filter((p) -> p.getStudentsCount().equals(sc)).findFirst().orElseThrow(NoSuchElementException::new));  
				((PrintStream) outStream).println("Element deleted!");
			}
			catch(NoSuchElementException e)
			{
				((PrintStream) outStream).println("No such element!");
			}
		}
		return 0;
	}
	
	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new RemoveAnyBySCCommand();
		commandsMap.put(command.getName(), command);
	}

	@Override
	public String getHelp() 
	{
		return "This command removes the element from collection if its students count equals to argument!";
	}
}