package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Stack;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.History;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.StudyGroup;

public class HistoryCommand extends Command 
{
	private static HistoryCommand command;
	
	private HistoryCommand() 
	{
		super("history");
	}
	
	@Override
	public int execute(HashMap<String, Command> map, Stack<StudyGroup> stack, Object[] args, InputStream inStream,
			OutputStream outStream) throws CommandException 
	{
		String out = "";
		if(args.length > 2 )
		{
			throw new CommandArgumentException("This command requires one or zero arguments!\n" + getUsage());
		}
		else if(args.length == 0)
		{
			for(String com : History.getGlobalHistory())
			{
				out = out + com + "\n";
			}
			((PrintStream) outStream).println("-------");
			((PrintStream) outStream).println(out);
		}
		else 
		{
			if(History.getGlobalHistory().size() - Integer.parseInt((String) args[0]) < 0)
			{
				throw new CommandArgumentException("There are only " + History.getGlobalHistory().size() + " commands in history!");
			}
			else
			{
				ListIterator<String> iterator = History.getGlobalHistory().listIterator(History.getGlobalHistory().size() - Integer.parseInt((String) args[0]));
				while(iterator.hasNext())
				{
					out = out + iterator.next() + "\n";
				}
				((PrintStream) outStream).println("-------");
				((PrintStream) outStream).println(out);
			}
		}
		return 0;
	}
	
	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new HistoryCommand();
		commandsMap.put(command.getName(), command);
	}


	@Override
	public String getHelp() 
	{
		return "This command shows history of commands!";
	}
}
