package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.csv.CSVManager;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;

public class SaveCommand extends Command
{
	private static SaveCommand command;
	
	private SaveCommand()
	{
		super("save", "path_to_file");
	}
	
	@Override
	public int execute(Environment env, Object[] args, InputStream inStream, OutputStream outStream) throws CommandException 
	{
		if(args.length > 2 || args.length < 1)
		{
			throw new CommandArgumentException("This command require only path to file!\n" + getUsage());
		}
		else
		{
			if(CSVManager.write((String) args[0], env.getCollection(), outStream))
			{
				((PrintStream) outStream).println("Collection saved!");
			}
			else
			{
				((PrintStream) outStream).println("Collection didn't save!");
			}
		}
		return 0;
	}
	
	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new SaveCommand();
		commandsMap.put(command.getName(), command);
	}
	
	@Override
	public String getHelp() 
	{
		return "This command saves collection to file!";
	}
}