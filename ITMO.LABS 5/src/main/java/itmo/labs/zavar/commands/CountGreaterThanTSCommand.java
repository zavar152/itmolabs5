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

/**
 * Outputs the number of elements whose transferredStudents field value is greater than the specified value.
 * Requires transferred students.
 * 
 * @author Zavar
 * @version 1.2
 */
public class CountGreaterThanTSCommand extends Command 
{
	private static CountGreaterThanTSCommand command;
	
	private CountGreaterThanTSCommand()
	{
		super("count_greater_than_transferred_students", "transferredStudents");
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
			long tr;
			try
			{
				tr = Long.parseLong((String) args[0]); 
			}
			catch(NumberFormatException e)
			{
				throw new CommandArgumentException("transferredStudents shold be a long type!");
			}
			
			if(env.getCollection().isEmpty())
			{
				throw new CommandRunningException("Collection is empty!");
			}
			
			long count = env.getCollection().stream().filter((p) -> p.getTransferredStudents() > tr).count();
			((PrintStream) outStream).println("Count of elements: " + count);
		}
		return 0;
	}

	/**
	 * Uses for commands registration.
	 * 
	 * @param commandsMap Commands' map.
	 */
	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new CountGreaterThanTSCommand();
		commandsMap.put(command.getName(), command);
	}
	
	@Override
	public String getHelp() 
	{
		return "This command counts a number of elements which transferred students count is greater than argument!";
	}
	
	
}
