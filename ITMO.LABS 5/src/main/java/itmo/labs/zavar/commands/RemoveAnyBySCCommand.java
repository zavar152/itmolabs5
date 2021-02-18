package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.ListIterator;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.StudyGroup;

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
			ListIterator<StudyGroup> iterator = env.getCollection().listIterator();
			long sc = 0;
			try
			{
				sc = Long.parseLong((String) args[0]);
			}
			catch(NumberFormatException e)
			{
				throw new CommandArgumentException("students_count shold be a long type!");
			}
			while(iterator.hasNext())
			{
				if(iterator.next().getStudentsCount() == sc)
				{
					iterator.remove();
					((PrintStream) outStream).println("Element deleted!");
					break;
				}
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
