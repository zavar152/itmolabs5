package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.csv.CSVManager;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;

/**
 * Saves the collection to a file. Requires a path to file.
 * 
 * @author Zavar
 * @version 1.2
 */
public class SaveCommand extends Command {

	private SaveCommand() {
		super("save", "path_to_file");
	}

	@Override
	public void execute(Environment env, Object[] args, InputStream inStream, OutputStream outStream)
			throws CommandException {
		if (args.length > 2 || args.length < 1) {
			throw new CommandArgumentException("This command require only path to file!\n" + getUsage());
		} else {
			if (CSVManager.write((String) args[0], env.getCollection(), outStream)) {
				((PrintStream) outStream).println("Collection saved!");
			} else {
				((PrintStream) outStream).println("Collection didn't save!");
			}
		}
	}

	/**
	 * Uses for commands registration.
	 * 
	 * @param commandsMap Commands' map.
	 */
	public static void register(HashMap<String, Command> commandsMap) {
		SaveCommand command = new SaveCommand();
		commandsMap.put(command.getName(), command);
	}

	@Override
	public String getHelp() {
		return "This command saves collection to file!";
	}
}
