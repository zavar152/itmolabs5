package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.ListIterator;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.exception.CommandRunningException;

/**
 * Ouputs history of commands.
 * 
 * @author Zavar
 *
 */
public class HistoryCommand extends Command {

	private HistoryCommand() {
		super("history", "count");
	}

	@Override
	public void execute(Environment env, Object[] args, InputStream inStream, OutputStream outStream)
			throws CommandException {
		if (args.length > 2 || args.length < 0) {
			throw new CommandArgumentException("This command requires one or zero arguments!\n" + getUsage());
		} else if (args.length == 0) {
			((PrintStream) outStream).println("-------");
			env.getHistory().getGlobalHistory().stream().forEachOrdered(((PrintStream) outStream)::println);
		} else {
			if (Integer.parseInt((String) args[0]) <= 0) {
				throw new CommandArgumentException("Argument should be greater than 0!");
			}
			if (env.getHistory().getGlobalHistory().size() - Integer.parseInt((String) args[0]) < 0) {
				throw new CommandRunningException(
						"There are only " + env.getHistory().getGlobalHistory().size() + " commands in history!");
			} else {
				ListIterator<String> iterator = env.getHistory().getGlobalHistory()
						.listIterator(env.getHistory().getGlobalHistory().size() - Integer.parseInt((String) args[0]));
				((PrintStream) outStream).println("-------");
				iterator.forEachRemaining(((PrintStream) outStream)::println);
			}
		}
	}

	/**
	 * Uses for commands registration.
	 * 
	 * @param commandsMap Commands' map.
	 */
	public static void register(HashMap<String, Command> commandsMap) {
		HistoryCommand command = new HistoryCommand();
		commandsMap.put(command.getName(), command);
	}

	@Override
	public String getHelp() {
		return "This command shows history of commands!";
	}
}
