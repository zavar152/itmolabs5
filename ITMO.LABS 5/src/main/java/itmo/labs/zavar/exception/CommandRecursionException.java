package itmo.labs.zavar.exception;

public class CommandRecursionException extends CommandException
{
	public CommandRecursionException() 
	{
		super("Recursion detected! Execution will be aborted!");
	}
}
