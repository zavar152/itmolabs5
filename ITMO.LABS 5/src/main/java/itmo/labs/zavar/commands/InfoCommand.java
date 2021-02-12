package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;

public class InfoCommand extends Command
{
	private static InfoCommand command;
	
	public InfoCommand() 
	{
		super("info");
	}

	@Override
	public int execute(Environment env, Object[] args, InputStream inStream, OutputStream outStream) throws CommandException 
	{
		if(args.length > 0)
		{
			throw new CommandArgumentException("This command doesn't require any arguments!\n" + getUsage());
		}
		else
		{
			((PrintStream) outStream).println("Type: " + env.getCollection().getClass().getName());
			((PrintStream) outStream).println("Creation date: ");
			((PrintStream) outStream).println("Count of elements: " + env.getCollection().size());
		}
		return 0;
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
