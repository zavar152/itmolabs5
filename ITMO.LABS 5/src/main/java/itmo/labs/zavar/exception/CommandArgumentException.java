package itmo.labs.zavar.exception;

/**
 * Exception for command's arguments errors. 
 * 
 * @author Zavar
 * @version 1.0
 */
public class CommandArgumentException extends CommandException
{
	public CommandArgumentException(String text) 
	{
		super("Argument error: " + text);
	}
}
