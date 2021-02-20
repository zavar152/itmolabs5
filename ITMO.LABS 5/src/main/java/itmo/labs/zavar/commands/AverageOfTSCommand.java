package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.exception.CommandRunningException;

public class AverageOfTSCommand extends Command
{	
	private static AverageOfTSCommand command;
	
	private AverageOfTSCommand()
	{
		super("average_of_transferred_students");
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
			if(env.getCollection().isEmpty())
			{
				throw new CommandRunningException("Collection is empty!");
			}
			double a = env.getCollection().stream().mapToLong((l) -> l.getTransferredStudents()).average().orElse(0);
			((PrintStream) outStream).println("The average value of transferred students is " + a);
		}
		return 0;
	}

	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new AverageOfTSCommand();
		commandsMap.put(command.getName(), command);
	}
	
	@Override
	public String getHelp() 
	{
		return "This command counts the average value of transferred students!";
	}
	
	
}
