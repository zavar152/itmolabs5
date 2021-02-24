package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;

/**
 * Outputs information about the collection to the standard output stream.
 * Requires additional user input.
 * 
 * @author Zavar
 * @version 1.9
 * 
 */
public class InfoCommand extends Command
{

	private InfoCommand() 
	{
		super("info");
	}

	@Override
	public void execute(Environment env, Object[] args, InputStream inStream, OutputStream outStream) throws CommandException 
	{
		if(args.length > 0)
		{
			throw new CommandArgumentException("This command doesn't require any arguments!\n" + getUsage());
		}
		else
		{
			PrintStream pr = ((PrintStream) outStream);
			pr.println("Type: " + env.getCollection().getClass().getName());
			pr.println("Creation date: " + env.getCreationTime());
			pr.println("Count of elements: " + env.getCollection().size());
		}
	}

	/**
	 * Uses for commands registration.
	 * 
	 * @param commandsMap Commands' map.
	 */
	public static void register(HashMap<String, Command> commandsMap)
	{
		InfoCommand command = new InfoCommand();
		commandsMap.put(command.getName(), command);
	}
	
	@Override
	public String getHelp() 
	{
		return "This command shows information about the collection!";
	}
}
