package itmo.labs.zavar.exception;

public class CommandRunningException extends CommandException
{
	public CommandRunningException(String text) 
	{
		super("Running error: " + text);
	}
}
