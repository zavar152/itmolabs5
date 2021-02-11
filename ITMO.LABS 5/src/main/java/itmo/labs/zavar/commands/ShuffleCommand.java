package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.StudyGroup;

public class ShuffleCommand extends Command
{
	private static ShuffleCommand command;

	private ShuffleCommand() 
	{
		super("shuffle");
	}

	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new ShuffleCommand();
		commandsMap.put(command.getName(), command);
	}
	
	@Override
	public int execute(HashMap<String, Command> map, Stack<StudyGroup> stack, Object[] args, InputStream inStream, OutputStream outStream) throws CommandException 
	{
		if(args.length > 0)
		{
			throw new CommandArgumentException("This command doesn't require any arguments!\n" + getUsage());
		}
		else
		{
			Collections.shuffle(stack);
			((PrintStream) outStream).println("Collection mixed up!");
		}
		
		return 0;
	}

	@Override
	public String getHelp() 
	{
		return "This command shuffles the elements in collection!";
	}

}
