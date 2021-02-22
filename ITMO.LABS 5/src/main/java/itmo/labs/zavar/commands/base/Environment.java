package itmo.labs.zavar.commands.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Stack;

import itmo.labs.zavar.csv.CSVManager;
import itmo.labs.zavar.studygroup.StudyGroup;

/**
 * This class contains main information: commands' map, collection, command's history, init time.
 * 
 * @author Zavar
 * @version 1.4
 */

public class Environment 
{
	private HashMap<String, Command> map;
	private Stack<StudyGroup> stack;
	private History history;
	private String time;
	
	/**
	 * Creates new environment for commands. Collection's creation date will be equals to file's creation date.
	 * If it won't be able to get file's attributes, collection's creation date will be set to the current. 
	 * 
	 * @param file File with collection.
	 * @param map Commands' map.
	 * @param stack Main collection.
	 */
	public Environment(File file, HashMap<String, Command> map, Stack<StudyGroup> stack)
	{
		System.out.println("Reading file...");
		if(CSVManager.read(file.toPath().toString(), stack, System.out))
		{
			System.out.println("Collection loaded!");
		}
		
		this.map = map;
		this.stack = stack;
		history = new History();
		try 
		{
			BasicFileAttributes attr = Files.readAttributes((file).toPath(), BasicFileAttributes.class);
			time = new SimpleDateFormat("yyyy-MM-dd").format(attr.creationTime().toMillis());
		} 
		catch (IOException e) 
		{
			time = new SimpleDateFormat("yyyy-MM-dd").format(Date.valueOf(LocalDate.now()));
		}
	}

	/**
	 * Returns commands' map.
	 * 
	 * @return {@link HashMap}
	 */
	public HashMap<String, Command> getCommandsMap() 
	{
		return map;
	}

	/**
	 * Returns main collection.
	 * 
	 * @return {@link Stack}
	 */
	public Stack<StudyGroup> getCollection() 
	{
		return stack;
	}

	/**
	 * Returns history.
	 * 
	 * @return {@link History}
	 */
	public History getHistory() 
	{
		return history;
	}
	
	/**
	 * Returns creation time.
	 * 
	 * @return {@link String}
	 */
	public String getCreationTime()
	{
		return time;
	}
	
	/**
	 * Class uses to contain global history of commands and to contain temp history of "execute_script" command to prevent recursion.
	 * 
	 * @author Zavar
	 * @version 1.0
	 */
	public class History 
	{
		private Stack<String> globalHistory = new Stack<String>();
		private Stack<String> tempHistory = new Stack<String>();
		
		/**
		 * Clears temp history.
		 */
		public void clearTempHistory()
		{
			tempHistory.clear();
		}
		
		/**
		 * Returns global command history. 
		 * @return {@link Stack}
		 */
		public Stack<String> getGlobalHistory()
		{
			return globalHistory;
		}
		
		/**
		 * Returns temp command history. 
		 * @return {@link Stack}
		 */
		public Stack<String> getTempHistory()
		{
			return tempHistory;
		}
		
		/**
		 * Adds command to global history.
		 * @param to Command to add.
		 */
		public void addToGlobal(String to)
		{
			globalHistory.push(to);
		}
		
		/**
		 * Adds command to temp history.
		 * @param to Command to add.
		 */
		public void addToTemp(String to)
		{
			tempHistory.push(to);
		}
	}
}
