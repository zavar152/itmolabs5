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

public class HistoryCommand extends Command 
{
	private static HistoryCommand command;
	
	private HistoryCommand() 
	{
		super("history");
	}
	
	@Override
	public int execute(Environment env, Object[] args, InputStream inStream, OutputStream outStream) throws CommandException 
	{
		String out = "";
		if(args.length > 2 || args.length < 0 )
		{
			throw new CommandArgumentException("This command requires one or zero arguments!\n" + getUsage());
		}
		else if(args.length == 0)
		{
			for(String com : env.getHistory().getGlobalHistory())
			{
				out = out + com + "\n";
			}
			((PrintStream) outStream).println("-------");
			((PrintStream) outStream).println(out);
		}
		else 
		{
			if(env.getHistory().getGlobalHistory().size() - Integer.parseInt((String) args[0]) < 0)
			{
				throw new CommandArgumentException("There are only " + env.getHistory().getGlobalHistory().size() + " commands in history!");
			}
			else
			{
				ListIterator<String> iterator = env.getHistory().getGlobalHistory().listIterator(env.getHistory().getGlobalHistory().size() - Integer.parseInt((String) args[0]));
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
