package itmo.labs.zavar.exception;

/**
 * Exception for any command's error.
 * 
 * @author Zavar
 * @version 1.0
 */
public class CommandException extends Exception {

	private static final long serialVersionUID = 2190189016910990672L;

	public CommandException(String text) {
		super(text);
	}
}
