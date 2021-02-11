package itmo.labs.zavar.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.StudyGroup;

public class ExecuteScriptCommand extends Command
{
	private static ExecuteScriptCommand command;

	public ExecuteScriptCommand() 
	{
		super("execute_script", "path_to_file");
	}

	@Override
	public void execute(HashMap<String, Command> map, Stack<StudyGroup> stack, Object[] args) throws CommandArgumentException 
	{
		if(args.length > 1 || args.length < 1)
		{
			throw new CommandArgumentException("This command requires one argument!\n" + getUsage());
		}
		else
		{
			String line;
			BufferedReader reader = null;
			try 
			{
				reader = new BufferedReader(new FileReader((String) args[0]));
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
	        try 
	        {
				while ((line = reader.readLine()) != null) 
				{
					if(line.startsWith("/"))
					{
						line = line.substring(1);
						String command[] = line.split(" ");
					
						if(map.containsKey(command[0]))
						{
							try 
							{
								map.get(command[0]).execute(map, stack, Arrays.copyOfRange(command, 1, command.length));
							} 
							catch(CommandException e) 
							{
								System.out.println(e.getMessage());
							}
						}
						else
						{
							System.out.println("Unknown command! Use /help.");
						}
					}
					else
					{
						System.out.println("It isn't a command! Use /help.");
					}
				}
			} 
	        catch (IOException e) 
	        {
				e.printStackTrace();
			}
		}
	}

	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new ExecuteScriptCommand();
		commandsMap.put(command.getName(), command);
	}
	
	@Override
	public String getHelp() 
	{
		return "This command runs a script from a file!";
	}

}
