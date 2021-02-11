package itmo.labs.zavar.exception;

public class CommandArgumentException extends CommandException
{
	public CommandArgumentException(String text) 
	{
		super("Argument error: " + text);
	}
}
