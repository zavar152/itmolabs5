package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Stack;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.StudyGroup;

public class ClearCommand extends Command
{
	private static ClearCommand command;
	
	private ClearCommand() 
	{
		super("clear");
	}

	@Override
	public int execute(HashMap<String, Command> map, Stack<StudyGroup> stack, Object[] args, InputStream inStream, OutputStream outStream)
			throws CommandException 
	{
		if(args.length > 0)
		{
			throw new CommandArgumentException("This command doesn't require any arguments!\n" + getUsage());
		}
		else
		{
			stack.clear();
			((PrintStream) outStream).println("Collection cleared");
		}
		return 0;
	}

	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new ClearCommand();
		commandsMap.put(command.getName(), command);
	}
	
	@Override
	public String getHelp() 
	{
		return "This command clears the collection!";
	}
}
