package itmo.labs.zavar.exception;

/**
 * Exception for command's arguments errors.
 * 
 * @author Zavar
 * @version 1.0
 */
public class CommandArgumentException extends CommandException {

	private static final long serialVersionUID = 1809502285590439897L;

	public CommandArgumentException(String text) {
		super("Argument error: " + text);
	}
}
