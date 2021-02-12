package itmo.labs.zavar.commands.base;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import itmo.labs.zavar.exception.CommandException;

public abstract class Command
{
	private String name;
	private String[] args;
	
	public Command(String name, String ... args)
	{
		this.name = name;
		this.args = args;
	}
	
	public abstract int execute(Environment env, Object[] args, InputStream inStream, OutputStream outStream) throws CommandException;
	
	public abstract String getHelp();
	
	public final String getUsage()
	{
		return "Usage: " + name + " " + getArgsAsString();
	}
	
	public final String[] getArgs() 
	{
		return args;
	}
	
	public final String getArgsAsString() 
	{
		if(args.length > 0)
		{
			return Arrays.toString(args).replace(", ", "] [");
		}
		else
		{
			return "";
		}
	}
	
	public final String getName() 
	{
		return name;
	}
	
	public boolean isNeedInput()
	{
		return false;
	}
}
