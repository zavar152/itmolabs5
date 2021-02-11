package itmo.labs.zavar.exception;

public class IllegalParameterException extends CommandException
{
	public IllegalParameterException(String text) 
	{
		super("Illegal parameters: " + text);
	}
}
