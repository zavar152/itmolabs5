package itmo.labs.zavar;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import itmo.labs.zavar.commands.AddCommand;
import itmo.labs.zavar.commands.AddIfMaxCommand;
import itmo.labs.zavar.commands.AddIfMinCommand;
import itmo.labs.zavar.commands.AverageOfTSCommand;
import itmo.labs.zavar.commands.ClearCommand;
import itmo.labs.zavar.commands.CountGreaterThanTSCommand;
import itmo.labs.zavar.commands.ExecuteScriptCommand;
import itmo.labs.zavar.commands.ExitCommand;
import itmo.labs.zavar.commands.HelpCommand;
import itmo.labs.zavar.commands.HistoryCommand;
import itmo.labs.zavar.commands.InfoCommand;
import itmo.labs.zavar.commands.RemoveAnyBySCCommand;
import itmo.labs.zavar.commands.RemoveByIDCommand;
import itmo.labs.zavar.commands.SaveCommand;
import itmo.labs.zavar.commands.ShowCommand;
import itmo.labs.zavar.commands.ShuffleCommand;
import itmo.labs.zavar.commands.TestCommand;
import itmo.labs.zavar.commands.UpdateCommand;
import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.csv.CSVManager;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.StudyGroup;

public class Launcher 
{
	private static Stack<StudyGroup> stack = new Stack<StudyGroup>();
	private static HashMap<String, Command> commandsMap = new HashMap<String, Command>();
	
	public static void main(String[] args) throws IOException
	{	
		if(args.length < 1 || args.length > 1)
		{
			System.out.println("You should enter only a path to .csv file!");
			System.exit(0);
		}
		
		System.out.println("Reading file...");
		if(CSVManager.read(args[0], stack, System.out))
		{
			System.out.println("Collection loaded!");
		}
		System.out.println("Welcome to ITMO.LAB 5, enter a command or use help");
		
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
		SaveCommand.register(commandsMap);
		RemoveAnyBySCCommand.register(commandsMap);
		AverageOfTSCommand.register(commandsMap);
		CountGreaterThanTSCommand.register(commandsMap);
		AddIfMaxCommand.register(commandsMap);
		AddIfMinCommand.register(commandsMap);
		UpdateCommand.register(commandsMap);
		
		Scanner in = new Scanner(System.in);
		Environment env = new Environment(Files.readAttributes(new File(args[0]).toPath(), BasicFileAttributes.class), commandsMap, stack);
		
		
		while(true)
		{
			String input = in.nextLine();
			input = input.replaceAll(" +", " "); 
			String command[] = input.split(" ");
			
			if(env.getCommandMap().containsKey(command[0]))
			{
				try 
				{
					env.getHistory().addToGlobal(input);
					env.getCommandMap().get(command[0]).execute(env, Arrays.copyOfRange(command, 1, command.length), System.in, System.out);
					env.getHistory().clearTempHistory();
				} 
				catch(CommandException e) 
				{
					System.err.println(e.getMessage());
					env.getHistory().clearTempHistory();
				}
			}
			else
			{
				System.err.println("Unknown command! Use help.");
			}
		}
	}
}
