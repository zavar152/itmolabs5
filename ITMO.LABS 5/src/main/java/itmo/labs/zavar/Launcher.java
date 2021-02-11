package itmo.labs.zavar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import itmo.labs.zavar.commands.AddCommand;
import itmo.labs.zavar.commands.ClearCommand;
import itmo.labs.zavar.commands.ExecuteScriptCommand;
import itmo.labs.zavar.commands.ExitCommand;
import itmo.labs.zavar.commands.HelpCommand;
import itmo.labs.zavar.commands.HistoryCommand;
import itmo.labs.zavar.commands.InfoCommand;
import itmo.labs.zavar.commands.RemoveByIDCommand;
import itmo.labs.zavar.commands.ShowCommand;
import itmo.labs.zavar.commands.ShuffleCommand;
import itmo.labs.zavar.commands.TestCommand;
import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.History;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.StudyGroup;

public class Launcher 
{
	private static Stack<StudyGroup> stack = new Stack<StudyGroup>();
	private static HashMap<String, Command> commandsMap = new HashMap<String, Command>();

	public static void main(String[] args)
	{	
		System.setErr(System.out);
		TestCommand.register(commandsMap);
		HelpCommand.register(commandsMap);
		ExitCommand.register(commandsMap);
		ShowCommand.register(commandsMap);
		ExecuteScriptCommand.register(commandsMap);
		ClearCommand.register(commandsMap);
		InfoCommand.register(commandsMap);
		AddCommand.register(commandsMap);
		RemoveByIDCommand.register(commandsMap);
		ShuffleCommand.register(commandsMap);
		HistoryCommand.register(commandsMap);
		
		Scanner in = new Scanner(System.in);
		
		while(true)
		{
			String input = in.nextLine();
			String command[] = input.split(" ");
				
			if(commandsMap.containsKey(command[0]))
			{
				try 
				{
					History.addToGlobal(input);
					commandsMap.get(command[0]).execute(commandsMap, stack, Arrays.copyOfRange(command, 1, command.length), System.in, System.out);
					History.clearTempHistory();
				} 
				catch(CommandException e) 
				{
					System.err.println(e.getMessage());
					History.clearTempHistory();
				}
			}
			else
			{
				System.err.println("Unknown command! Use help.");
			}
		}
	}
}
