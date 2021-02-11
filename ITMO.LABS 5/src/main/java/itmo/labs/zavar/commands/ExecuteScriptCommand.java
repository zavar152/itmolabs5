package itmo.labs.zavar.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.input.ReaderInputStream;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.History;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.exception.RecursionException;
import itmo.labs.zavar.studygroup.StudyGroup;

public class ExecuteScriptCommand extends Command
{
	private static ExecuteScriptCommand command;

	public ExecuteScriptCommand() 
	{
		super("execute_script", "path_to_file");
	}

	@Override
	public int execute(HashMap<String, Command> map, Stack<StudyGroup> stack, Object[] args, InputStream inStream, OutputStream outStream) throws CommandException
	{
		if(args.length > 1 || args.length < 1)
		{
			throw new CommandArgumentException("This command requires one argument!\n" + getUsage());
		}
		else
		{
			History.addToTemp((getName() + " " + Arrays.toString(args).replace("[", "").replace("]", "")));
			if((History.getTempHistory().size() > 1) && (History.getTempHistory().firstElement().equals(getName() + " " + Arrays.toString(args).replace("[", "").replace("]", ""))))
			{
				throw new RecursionException();
			}
			
			List<String> lines = null;
			List<String> executed = new ArrayList<String>();
			try 
			{
				lines = Files.readAllLines(Paths.get((String) args[0]), StandardCharsets.UTF_8);
			} 
			catch (IOException e) 
			{
				((PrintStream) outStream).println(e.getMessage());
			}
			
			for(int i = 0; i < lines.size(); i++)
			{
				String line = lines.get(i);
				String command[] = line.split(" ");
				executed.add(line);
				
				if(map.containsKey(command[0]))
				{
					try 
					{
						if(map.get(command[0]).isNeedInput())
						{
							List<String> subList = ListUtils.subtract(lines, executed);
					        String to = "";
					        for(String l : subList)
					        {
					        	to = to + l + "\n" ;
					        }
					        History.addToGlobal(line);
							int r = map.get(command[0]).execute(map, stack, Arrays.copyOfRange(command, 1, command.length), new ReaderInputStream(new StringReader(to), StandardCharsets.UTF_8), outStream);
							i = i + r + 1;
						}
						else
						{
							History.addToGlobal(line);
							map.get(command[0]).execute(map, stack, Arrays.copyOfRange(command, 1, command.length), inStream, outStream);
						}
					} 
					catch(CommandException e) 
					{
						System.err.println(e.getMessage());
					}
				}
				else
				{
					System.err.println("Unknown command #" + (i + 1) + " ! Please, check your script!");
				}
			}
		}
		return 0;
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
