package itmo.labs.zavar;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import itmo.labs.zavar.commands.AddCommand;
import itmo.labs.zavar.commands.AddIfMaxCommand;
import itmo.labs.zavar.commands.AddIfMinCommand;
import itmo.labs.zavar.commands.AverageOfTSCommand;
import itmo.labs.zavar.commands.ClearCommand;
import itmo.labs.zavar.commands.CountGreaterThanTSCommand;
import itmo.labs.zavar.commands.ExecuteScriptCommand;
import itmo.labs.zavar.commands.ExitCommand;
import itmo.labs.zavar.commands.HelpCommand;
import itmo.labs.zavar.commands.HistoryCommand;
import itmo.labs.zavar.commands.InfoCommand;
import itmo.labs.zavar.commands.RemoveAnyBySCCommand;
import itmo.labs.zavar.commands.RemoveByIDCommand;
import itmo.labs.zavar.commands.SaveCommand;
import itmo.labs.zavar.commands.ShowCommand;
import itmo.labs.zavar.commands.ShuffleCommand;
import itmo.labs.zavar.commands.UpdateCommand;
import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.csv.CSVManager;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.StudyGroup;

/**
 * Main class of application.
 * 
 * @author Zavar
 * @version 1.7
 */
public class CMD {
	private static Stack<StudyGroup> stack = new Stack<StudyGroup>();
	private static HashMap<String, Command> commandsMap = new HashMap<String, Command>();

	/**
	 * Registering all commands and creating environment. Contains interpreter of
	 * commands.
	 * 
	 * @param args path to csv file.
	 */
	public static void main(String[] args) {
		System.setErr(System.out);

		if (args.length != 1) {
			args = new String[] { "" };
			System.out.println("You should enter a path to .csv file! Collection will be empty!");
		}

		File file = new File(args[0]);

		try {
			System.out.println("Reading file...");
			if (CSVManager.read(file.toPath().toString(), stack, System.out)) {
				System.out.println("Collection loaded!");
			}
		} catch (Exception e) {
			System.out.println("Unexcepted error during initialization. Program will be closed...");
			System.exit(-1);
		}

		HelpCommand.register(commandsMap);
		ExitCommand.register(commandsMap);
		ShowCommand.register(commandsMap);
		ExecuteScriptCommand.register(commandsMap);
		ClearCommand.register(commandsMap);
		InfoCommand.register(commandsMap);
		AddCommand.register(commandsMap);
		RemoveByIDCommand.register(commandsMap);
		ShuffleCommand.register(commandsMap);
		HistoryCommand.register(commandsMap);
		SaveCommand.register(commandsMap);
		RemoveAnyBySCCommand.register(commandsMap);
		AverageOfTSCommand.register(commandsMap);
		CountGreaterThanTSCommand.register(commandsMap);
		AddIfMaxCommand.register(commandsMap);
		AddIfMinCommand.register(commandsMap);
		UpdateCommand.register(commandsMap);

		Scanner in = new Scanner(System.in);
		Environment env = new Environment(file, commandsMap, stack);

		System.out.println("Welcome to ITMO.LAB 5, enter a command or use help");

		while (true) {
			try {
				String input = in.nextLine();
				input = input.replaceAll(" +", " ").trim();
				String command[] = input.split(" ");

				if (env.getCommandsMap().containsKey(command[0])) {
					try {
						env.getHistory().addToGlobal(input);
						env.getCommandsMap().get(command[0]).execute(env,
								Arrays.copyOfRange(command, 1, command.length), System.in, System.out);
						env.getHistory().clearTempHistory();
					} catch (CommandException e) {
						System.err.println(e.getMessage());
						env.getHistory().clearTempHistory();
					}
				} else {
					System.err.println("Unknown command! Use help.");
				}
			} catch (Exception e) {
				if (!in.hasNextLine()) {
					System.out.println("Inputing is closed! Program is closing...");
					System.exit(0);
				} else {
					System.out.println("Unexcepted error!");
				}
			}
		}
	}
}
