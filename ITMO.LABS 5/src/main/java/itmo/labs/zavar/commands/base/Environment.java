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

import itmo.labs.zavar.studygroup.StudyGroup;

public class Environment 
{
	private HashMap<String, Command> map;
	private Stack<StudyGroup> stack;
	private History history;
	private String time;
	
	public Environment(File file, HashMap<String, Command> map, Stack<StudyGroup> stack)
	{
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

	public HashMap<String, Command> getCommandsMap() 
	{
		return map;
	}

	public Stack<StudyGroup> getCollection() 
	{
		return stack;
	}

	public History getHistory() 
	{
		return history;
	}
	
	public String getFileCreationTime()
	{
		return time;
	}
	
	public class History 
	{
		private Stack<String> globalHistory = new Stack<String>();
		private Stack<String> tempHistory = new Stack<String>();
		
		public void clearTempHistory()
		{
			tempHistory.clear();
		}
		
		public Stack<String> getGlobalHistory()
		{
			return globalHistory;
		}
		
		public Stack<String> getTempHistory()
		{
			return tempHistory;
		}
		
		public void addToGlobal(String to)
		{
			globalHistory.push(to);
		}
		
		public void addToTemp(String to)
		{
			tempHistory.push(to);
		}
	}
}
