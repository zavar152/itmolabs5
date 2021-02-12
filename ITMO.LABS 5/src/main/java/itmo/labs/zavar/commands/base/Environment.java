package itmo.labs.zavar.commands.base;

import java.util.HashMap;
import java.util.Stack;

import itmo.labs.zavar.studygroup.StudyGroup;

public class Environment 
{
	private HashMap<String, Command> map;
	private Stack<StudyGroup> stack;
	private History history;
	
	public Environment(HashMap<String, Command> map, Stack<StudyGroup> stack)
	{
		this.map = map;
		this.stack = stack;
		history = new History();
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
