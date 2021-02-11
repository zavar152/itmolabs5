package itmo.labs.zavar.commands.base;

import java.util.Stack;

public final class History 
{
	private static Stack<String> globalHistory = new Stack<String>();
	private static Stack<String> tempHistory = new Stack<String>();
	
	public static void clearTempHistory()
	{
		tempHistory.clear();
	}
	
	public static Stack<String> getGlobalHistory()
	{
		return globalHistory;
	}
	
	public static Stack<String> getTempHistory()
	{
		return tempHistory;
	}
	
	public static void addToGlobal(String to)
	{
		globalHistory.push(to);
	}
	
	public static void addToTemp(String to)
	{
		tempHistory.push(to);
	}
}
