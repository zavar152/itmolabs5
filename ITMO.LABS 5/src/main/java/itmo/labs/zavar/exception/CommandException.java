package itmo.labs.zavar.exception;

/**
 * Exception for any command's error.
 * 
 * @author Zavar
 * @version 1.0
 */
public class CommandException extends Exception
{
	public CommandException(String text) 
	{
		super(text);
	}
}
