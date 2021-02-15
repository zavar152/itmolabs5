package itmo.labs.zavar.commands;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.input.ReaderInputStream;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.exception.RecursionException;

public class ExecuteScriptCommand extends Command
{
	private static ExecuteScriptCommand command;

	public ExecuteScriptCommand() 
	{
		super("execute_script", "path_to_file");
	}

	@Override
	public int execute(Environment env, Object[] args, InputStream inStream, OutputStream outStream) throws CommandException
	{
		if(args.length > 1 || args.length < 1)
		{
			throw new CommandArgumentException("This command requires one argument!\n" + getUsage());
		}
		else
		{
			if(!new File((String) args[0]).exists())
			{
				throw new CommandException("File not found!");
			}
			
			if((env.getHistory().getTempHistory().size() > 1) && (env.getHistory().getTempHistory().contains(getName() + " " + Arrays.toString(args).replace("[", "").replace("]", ""))))
			{
				throw new RecursionException();
			}
			env.getHistory().addToTemp((getName() + " " + Arrays.toString(args).replace("[", "").replace("]", "")));
			
			List<String> lines = null;
			List<String> executed = new ArrayList<String>();
			try 
			{
				lines = Files.readAllLines(Paths.get((String) args[0]), StandardCharsets.UTF_8);
			} 
			catch (IOException e) 
			{
				throw new CommandException("IOException!");
			}
			
			for(int i = 0; i < lines.size(); i++)
			{
				String line = lines.get(i);
				String command[] = line.split(" ");
				executed.add(line);
				
				if(env.getCommandMap().containsKey(command[0]))
				{
					try 
					{
						if(env.getCommandMap().get(command[0]).isNeedInput())
						{
							List<String> subList = lines;
							subList.removeAll(executed);
					        String to = "";
					        for(String l : subList)
					        {
					        	to = to + l + "\n" ;
					        }
					        env.getHistory().addToGlobal(line);
							int r = env.getCommandMap().get(command[0]).execute(env, Arrays.copyOfRange(command, 1, command.length), new ReaderInputStream(new StringReader(to), StandardCharsets.UTF_8), outStream);
							i = i + r + 1;
						}
						else
						{
							env.getHistory().addToGlobal(line);
							env.getCommandMap().get(command[0]).execute(env, Arrays.copyOfRange(command, 1, command.length), inStream, outStream);
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
