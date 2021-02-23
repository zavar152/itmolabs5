package itmo.labs.zavar.commands.base;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Class provides static methods to parse values from {@link Scanner} with additional conditions and protection from incorrect input.
 * 
 * @author Zavar
 * @version 1.2
 */
public class InputParser 
{
	/**
	 * Parse {@link Integer} from {@link Scanner} with additional conditions and protection from incorrect input.
	 * 
	 * @param err Stream for output and errors.
	 * @param in {@link Scanner}
	 * @param name Name of input parameter.
	 * @param greaterThan The lower limit of the number. Type in {@link Integer#MIN_VALUE} if there is no any limit.
	 * @param lessThan The upper limit of the number. Type in {@link Integer#MAX_VALUE} if there is no any limit.
	 * @param canBeNull If the value can be <tt>null</tt>.
	 * @param primitive If the value is primitive type.
	 * @return {@link Integer} from input.
	 */
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
							((PrintStream) err).println("Enter again:");
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
							((PrintStream) err).println("Enter again:");
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
							((PrintStream) err).println("Enter again:");
						}
					}
				}
				catch(NumberFormatException e)
				{
					((PrintStream) err).println(name + " value must be greater than " + (greaterThan == Integer.MIN_VALUE ? "-2147483649" : greaterThan) + ", less than " + (lessThan == Integer.MAX_VALUE ? "2147483648" : lessThan) + (canBeNull ? "" : " and can't be null!"));	
					((PrintStream) err).println("Enter again:");
				}
			}
		}while(true);
		
		return i; 
	}
	
	/**
	 * Parse {@link Long} from {@link Scanner} with additional conditions and protection from incorrect input.
	 * 
	 * @param err Stream for output and errors.
	 * @param in {@link Scanner}
	 * @param name Name of input parameter.
	 * @param greaterThan The lower limit of the number. Type in {@link Long#MIN_VALUE} if there is no any limit.
	 * @param lessThan The upper limit of the number. Type in {@link Long#MAX_VALUE} if there is no any limit.
	 * @param canBeNull If the value can be <tt>null</tt>.
	 * @param primitive If the value is primitive type.
	 * @return {@link Long} from input.
	 */
	public static Long parseLong(OutputStream err, Scanner in, String name, long greaterThan, long lessThan, boolean canBeNull, boolean primitive)
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
							((PrintStream) err).println(name + " value must be greater than " + greaterThan);
							((PrintStream) err).println("Enter again:");
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
							((PrintStream) err).println(name + " value must be less than " + lessThan);
							((PrintStream) err).println("Enter again:");
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
							((PrintStream) err).println(name + " value must be greater than " + greaterThan + ", less than " + lessThan);
							((PrintStream) err).println("Enter again:");
						}
					}
				}
				catch(NumberFormatException e)
				{
					((PrintStream) err).println(name + " value must be greater than " + (greaterThan == Long.MIN_VALUE ? "-9223372036854775809" : greaterThan) + ", less than " + (lessThan == Long.MAX_VALUE ? "9223372036854775808" : lessThan) + (canBeNull ? "" : " and can't be null!"));	
					((PrintStream) err).println("Enter again:");
				}
			}
		}while(true);
		
		return l;
	}
	
	/**
	 * Parse {@link Float} from {@link Scanner} with additional conditions and protection from incorrect input.
	 * 
	 * @param err Stream for output and errors.
	 * @param in {@link Scanner}
	 * @param name Name of input parameter.
	 * @param greaterThan The lower limit of the number. Type in {@link Float#MIN_VALUE} if there is no any limit.
	 * @param lessThan The upper limit of the number. Type in {@link Float#MAX_VALUE} if there is no any limit.
	 * @param canBeNull If the value can be <tt>null</tt>.
	 * @param primitive If the value is primitive type.
	 * @return {@link Float} from input.
	 */
	public static Float parseFloat(OutputStream err, Scanner in, String name, float greaterThan, float lessThan, boolean canBeNull, boolean primitive)
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
							((PrintStream) err).println(name + " value must be greater than " + greaterThan);
							((PrintStream) err).println("Enter again:");
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
							((PrintStream) err).println(name + " value must be less than " + lessThan);
							((PrintStream) err).println("Enter again:");
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
							((PrintStream) err).println(name + " value must be greater than " + greaterThan + ", less than " + lessThan);
							((PrintStream) err).println("Enter again:");
						}
					}
				}
				catch(NumberFormatException e)
				{
					((PrintStream) err).println(name + " value must be greater than " + (greaterThan == Float.MIN_VALUE ? "(1.4E-45)-1" : greaterThan) + ", less than " + (lessThan == Float.MAX_VALUE ? "(3.4028235E38)+1" : lessThan) + (canBeNull ? "" : " and can't be null!"));	
					((PrintStream) err).println("Enter again:");
				}
			}
		}while(true);
		
		return f;
	}
	
	/**
	 * Parse {@link Double} from {@link Scanner} with additional conditions and protection from incorrect input.
	 * 
	 * @param err Stream for output and errors.
	 * @param in {@link Scanner}
	 * @param name Name of input parameter.
	 * @param greaterThan The lower limit of the number. Type in {@link Double#MIN_VALUE} if there is no any limit.
	 * @param lessThan The upper limit of the number. Type in {@link Double#MAX_VALUE} if there is no any limit.
	 * @param canBeNull If the value can be <tt>null</tt>.
	 * @param primitive If the value is primitive type.
	 * @return {@link Double} from input.
	 */
	public static Double parseDouble(OutputStream err, Scanner in, String name, double greaterThan, double lessThan, boolean canBeNull, boolean primitive)
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
							((PrintStream) err).println(name + " value must be greater than " + greaterThan);
							((PrintStream) err).println("Enter again:");
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
							((PrintStream) err).println(name + " value must be less than " + lessThan);
							((PrintStream) err).println("Enter again:");
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
							((PrintStream) err).println(name + " value must be greater than " + greaterThan + ", less than " + lessThan);
							((PrintStream) err).println("Enter again:");
						}
					}
				}
				catch(NumberFormatException e)
				{
					((PrintStream) err).println(name + " value must be greater than " + (greaterThan == Double.MIN_VALUE ? "(4.9E-324)-1" : greaterThan) + ", less than " + (lessThan == Double.MAX_VALUE ? "(1.7976931348623157E308)+1" : lessThan) + (canBeNull ? "" : " and can't be null!"));	
					((PrintStream) err).println("Enter again:");
				}
			}
		}while(true);
		
		return d;
	}
	
	/**
	 * Parse {@link Enum} from {@link Scanner} with additional conditions and protection from incorrect input.
	 * 
	 * @param err Stream for output and errors.
	 * @param in {@link Scanner}
	 * @param enu {@link Enum} class.
	 * @param canBeNull If the value can be <tt>null</tt>.
	 * @return {@link String} from input.
	 */
	public static String parseEnum(OutputStream err, Scanner in, Class<? extends Enum> enu, boolean canBeNull)
	{
		String par = "";
		do
		{
			par = in.nextLine();
			if(canBeNull && par.isEmpty())
			{
				return null;
			}
			else
			{
				try
				{
					par = Enum.valueOf(enu, par).toString();
					break;
				}
				catch(IllegalArgumentException e)
				{
					((PrintStream) err).println("Incorrect value!");
					((PrintStream) err).println("Enter again:");
				}
			}
			
		}while(true);
		
		return par;
	}
	
	/**
	 * Parse {@link String} from {@link Scanner} with additional conditions and protection from incorrect input.
	 * 
	 * @param err Stream for output and errors.
	 * @param in {@link Scanner}
	 * @param name Name of input parameter.
	 * @param greaterThan The lower limit of the string length. Type in {@link Integer#MIN_VALUE} if there is no any limit.
	 * @param lessThan The upper limit of the string length. Type in {@link Integer#MAX_VALUE} if there is no any limit.
	 * @param canBeNull If the value can be <tt>null</tt>.
	 * @param canBeEmpty If the string can be empty.
	 * @return {@link String} from input.
	 */
	public static String parseString(OutputStream err, Scanner in, String name, int greaterThan, int lessThan, boolean canBeNull, boolean canBeEmpty)
	{
		String ret;
		do
		{
			try
			{
				ret = in.nextLine();
				
				if(canBeNull && ret.isEmpty())
				{
					return null;
				}
				
				if(canBeEmpty && ret.isEmpty())
				{
					return "";
				}
				
				if((!canBeNull || !canBeEmpty) && ret.isEmpty())
				{
					((PrintStream) err).println(name +  " can't be null or empty");
					((PrintStream) err).println("Enter again:");
				}
				else
				{
					if(greaterThan == Integer.MIN_VALUE && lessThan == Integer.MAX_VALUE)
					{
						break;
					}
					else if(lessThan == Integer.MAX_VALUE)
					{
						if(ret.length() > greaterThan)
						{
							break;
						}
						else
						{
							((PrintStream) err).println(name + " length must be greater than " + greaterThan);
							((PrintStream) err).println("Enter again:");
						}
					}
					else if(greaterThan == Integer.MIN_VALUE)
					{
						if(ret.length() < lessThan)
						{
							break;
						}
						else
						{
							((PrintStream) err).println(name + " length must be less than " + lessThan);
							((PrintStream) err).println("Enter again:");
						}
					}
					else
					{
						if(ret.length() > greaterThan && ret.length() < lessThan)
						{
							break;
						}
						else
						{
							((PrintStream) err).println(name + " length must be greater than " + greaterThan + ", less than " + lessThan);
							((PrintStream) err).println("Enter again:");
						}
					}
				}
			}
			catch(NoSuchElementException e)
			{
				e.printStackTrace();
				((PrintStream) err).println("It isn't a string");
				((PrintStream) err).println("Enter again:");
			}
			
		}while(true);
			
		return ret;
	}
}
