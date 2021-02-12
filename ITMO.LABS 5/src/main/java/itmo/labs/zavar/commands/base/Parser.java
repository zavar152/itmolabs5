package itmo.labs.zavar.commands.base;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Parser 
{
	public static Integer parseInteger(OutputStream err, Scanner in, String name, int greaterThan, int lessThan, boolean canBeNull, boolean primitive)
	{
		String intStr = "";
		Integer i = null;
		do
		{
			intStr = in.nextLine();
			if(canBeNull && intStr.isEmpty())
			{
				if(primitive)
				{
					return 0;
				}
				else
				{
					return null;
				}
			}
			else
			{
				try
				{
					i = Integer.parseInt(intStr);
					if(greaterThan == Integer.MIN_VALUE && lessThan == Integer.MAX_VALUE)
					{
						break;
					}
					else if(lessThan == Integer.MAX_VALUE)
					{
						if(i > greaterThan)
						{
							break;
						}
						else
						{
							((PrintStream) err).println(name + " value must be greater than " + greaterThan);
						}
					}
					else if(greaterThan == Integer.MIN_VALUE)
					{
						if(i < lessThan)
						{
							break;
						}
						else
						{
							((PrintStream) err).println(name + " value must be less than " + lessThan);
						}
					}
					else
					{
						if(i > greaterThan && i < lessThan)
						{
							break;
						}
						else
						{
							((PrintStream) err).println(name + " value must be greater than " + greaterThan + ", less than " + lessThan);
						}
					}
				}
				catch(NumberFormatException e)
				{
					((PrintStream) err).println(name + " value must be greater than " + (greaterThan == Integer.MIN_VALUE ? "-2147483649" : greaterThan) + ", less than " + (lessThan == Integer.MAX_VALUE ? "2147483648" : lessThan) + (canBeNull ? "" : " and can't be null!"));	
				}
			}
		}while(true);
		
		return i;
	}
	
	public static Long parseLong(OutputStream out, Scanner in, String name, int greaterThan, int lessThan, boolean canBeNull, boolean primitive)
	{
		String longStr = "";
		Long l = null;
		do
		{
			longStr = in.nextLine();
			if(canBeNull && longStr.isEmpty())
			{
				if(primitive)
				{
					return 0l;
				}
				else
				{
					return null;
				}
			}
			else
			{
				try
				{
					l = Long.parseLong(longStr);
					if(greaterThan == Long.MIN_VALUE && lessThan == Long.MAX_VALUE)
					{
						break;
					}
					else if(lessThan == Long.MAX_VALUE)
					{
						if(l > greaterThan)
						{
							break;
						}
						else
						{
							((PrintStream) out).println(name + " value must be greater than " + greaterThan);
						}
					}
					else if(greaterThan == Long.MIN_VALUE)
					{
						if(l < lessThan)
						{
							break;
						}
						else
						{
							((PrintStream) out).println(name + " value must be less than " + lessThan);
						}
					}
					else
					{
						if(l > greaterThan && l < lessThan)
						{
							break;
						}
						else
						{
							((PrintStream) out).println(name + " value must be greater than " + greaterThan + ", less than " + lessThan);
						}
					}
				}
				catch(NumberFormatException e)
				{
					((PrintStream) out).println(name + " value must be greater than " + (greaterThan == Long.MIN_VALUE ? "-9223372036854775809" : greaterThan) + ", less than " + (lessThan == Long.MAX_VALUE ? "9223372036854775808" : lessThan) + (canBeNull ? "" : " and can't be null!"));	
				}
			}
		}while(true);
		
		return l;
	}
	
	public static Float parseFloat(OutputStream out, Scanner in, String name, int greaterThan, int lessThan, boolean canBeNull, boolean primitive)
	{
		String floatStr = "";
		Float f = null;
		do
		{
			floatStr = in.nextLine();
			if(canBeNull && floatStr.isEmpty())
			{
				if(primitive)
				{
					return 0f;
				}
				else
				{
					return null;
				}
			}
			else
			{
				try
				{
					f = Float.parseFloat(floatStr);
					if(greaterThan == Float.MIN_VALUE && lessThan == Float.MAX_VALUE)
					{
						break;
					}
					else if(lessThan == Float.MAX_VALUE)
					{
						if(f > greaterThan)
						{
							break;
						}
						else
						{
							((PrintStream) out).println(name + " value must be greater than " + greaterThan);
						}
					}
					else if(greaterThan == Float.MIN_VALUE)
					{
						if(f < lessThan)
						{
							break;
						}
						else
						{
							((PrintStream) out).println(name + " value must be less than " + lessThan);
						}
					}
					else
					{
						if(f > greaterThan && f < lessThan)
						{
							break;
						}
						else
						{
							((PrintStream) out).println(name + " value must be greater than " + greaterThan + ", less than " + lessThan);
						}
					}
				}
				catch(NumberFormatException e)
				{
					((PrintStream) out).println(name + " value must be greater than " + (greaterThan == Float.MIN_VALUE ? "(1.4E-45)-1" : greaterThan) + ", less than " + (lessThan == Float.MAX_VALUE ? "(3.4028235E38)+1" : lessThan) + (canBeNull ? "" : " and can't be null!"));	
				}
			}
		}while(true);
		
		return f;
	}
	
	public static Double parseDouble(OutputStream out, Scanner in, String name, int greaterThan, int lessThan, boolean canBeNull, boolean primitive)
	{
		String floatStr = "";
		Double d = null;
		do
		{
			floatStr = in.nextLine();
			if(canBeNull && floatStr.isEmpty())
			{
				if(primitive)
				{
					return 0d;
				}
				else
				{
					return null;
				}
			}
			else
			{
				try
				{
					d = Double.parseDouble(floatStr);
					if(greaterThan == Double.MIN_VALUE && lessThan == Double.MAX_VALUE)
					{
						break;
					}
					else if(lessThan == Double.MAX_VALUE)
					{
						if(d > greaterThan)
						{
							break;
						}
						else
						{
							((PrintStream) out).println(name + " value must be greater than " + greaterThan);
						}
					}
					else if(greaterThan == Double.MIN_VALUE)
					{
						if(d < lessThan)
						{
							break;
						}
						else
						{
							((PrintStream) out).println(name + " value must be less than " + lessThan);
						}
					}
					else
					{
						if(d > greaterThan && d < lessThan)
						{
							break;
						}
						else
						{
							((PrintStream) out).println(name + " value must be greater than " + greaterThan + ", less than " + lessThan);
						}
					}
				}
				catch(NumberFormatException e)
				{
					((PrintStream) out).println(name + " value must be greater than " + (greaterThan == Double.MIN_VALUE ? "(4.9E-324)-1" : greaterThan) + ", less than " + (lessThan == Double.MAX_VALUE ? "(1.7976931348623157E308)+1" : lessThan) + (canBeNull ? "" : " and can't be null!"));	
				}
			}
		}while(true);
		
		return d;
	}
}
