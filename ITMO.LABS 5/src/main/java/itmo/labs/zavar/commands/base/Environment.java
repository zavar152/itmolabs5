package itmo.labs.zavar.commands.base;

import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Stack;

import itmo.labs.zavar.studygroup.StudyGroup;

public class Environment 
{
	private HashMap<String, Command> map;
	private Stack<StudyGroup> stack;
	private History history;
	private BasicFileAttributes fileAttr;
	
	public Environment(BasicFileAttributes fileAttr, HashMap<String, Command> map, Stack<StudyGroup> stack)
	{
		this.map = map;
		this.stack = stack;
		history = new History();
		this.fileAttr = fileAttr;
	}

	public HashMap<String, Command> getCommandMap() 
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
	
	public BasicFileAttributes getFileAttr() 
	{
		return fileAttr;
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
