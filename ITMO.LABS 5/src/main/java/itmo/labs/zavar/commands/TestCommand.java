package itmo.labs.zavar.commands;

import java.util.HashMap;
import java.util.Stack;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.studygroup.StudyGroup;

public class TestCommand extends Command
{
	private static TestCommand command;
	
	private TestCommand()
	{
		super("test");
	}
	
	@Override
	public void execute(HashMap<String, Command> map, Stack<StudyGroup> stack, Object[] args) 
	{
		System.out.println("test command");	
	}
	
	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new TestCommand();
		commandsMap.put(command.getName(), command);
	}

	@Override
	public String getHelp() 
	{
		return "This is a test command without args!";
	}
}
