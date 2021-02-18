package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.StudyGroup;

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
			long tr = 0;
			try
			{
				tr = Long.parseLong((String) args[0]);
			}
			catch(NumberFormatException e)
			{
				throw new CommandArgumentException("transferredStudents shold be a long type!");
			}
			long count = 0;
			for(StudyGroup sg : env.getCollection())
			{
				if(sg.getTransferredStudents() == tr)
				{
					count++;
				}
			}
			((PrintStream) outStream).println("Count of elements: " + count);
		}
		return 0;
	}

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
