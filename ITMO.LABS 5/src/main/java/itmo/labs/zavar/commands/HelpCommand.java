package itmo.labs.zavar.commands;

import java.util.HashMap;
import java.util.Stack;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.StudyGroup;

public class HelpCommand extends Command
{
	private static HelpCommand command;
	
	private HelpCommand() 
	{
		super("help");
	}

	@Override
	public void execute(HashMap<String, Command> map, Stack<StudyGroup> stack, Object[] args) throws CommandException 
	{
		if(args.length > 0)
		{
			throw new CommandArgumentException("This command doesn't require any arguments!\n" + getUsage());
		}
		else
		{
			map.forEach((k, v) -> {
				System.out.println("<"+ map.get(k).getName() +">\n" + map.get(k).getHelp() + "\n" + map.get(k).getUsage() + "\n");
			});
		}
	}

	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new HelpCommand();
		commandsMap.put(command.getName(), command);
	}
	
	@Override
	public String getHelp() 
	{
		return "This command can give you information about other commands!";
	}
	
}
