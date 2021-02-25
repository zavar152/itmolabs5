package itmo.labs.zavar.exception;

/**
 * Exception for command's running process errors.
 * 
 * @author Zavar
 * @version 1.0
 */
public class CommandRunningException extends CommandException {

	private static final long serialVersionUID = 1622488370017111488L;

	public CommandRunningException(String text) {
		super("Running error: " + text);
	}
}
