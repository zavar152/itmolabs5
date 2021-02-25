package itmo.labs.zavar.exception;

/**
 * Exception for "execute_script" recursion error.
 * 
 * @author Zavar
 * @version 1.0
 */
public class CommandRecursionException extends CommandException {

	private static final long serialVersionUID = -5370275508785426360L;

	public CommandRecursionException() {
		super("Recursion detected! Execution will be aborted!");
	}
}
