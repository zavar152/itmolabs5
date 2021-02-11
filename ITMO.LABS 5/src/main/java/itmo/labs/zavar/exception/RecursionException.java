package itmo.labs.zavar.exception;

public class RecursionException extends CommandException
{
	public RecursionException() 
	{
		super("Recursion detected! Execution will be aborted!");
	}
}
