package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;

public class ExitCommand extends Command 
{
	private static ExitCommand command;
	
	private ExitCommand() 
	{
		super("exit");
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
			((PrintStream) outStream).println("Program is closing...");
			System.exit(0);
		}
		return 0;
	}

	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new ExitCommand();
		commandsMap.put(command.getName(), command);
	}
	
	@Override
	public String getHelp() 
	{
		return "This command stops the application!";
	}
}
