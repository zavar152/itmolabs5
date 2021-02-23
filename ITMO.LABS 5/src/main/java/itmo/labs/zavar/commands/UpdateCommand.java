package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.commands.base.InputParser;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.exception.CommandRunningException;
import itmo.labs.zavar.studygroup.Color;
import itmo.labs.zavar.studygroup.Coordinates;
import itmo.labs.zavar.studygroup.Country;
import itmo.labs.zavar.studygroup.FormOfEducation;
import itmo.labs.zavar.studygroup.Location;
import itmo.labs.zavar.studygroup.Person;
import itmo.labs.zavar.studygroup.StudyGroup;

/**
 * Updates the value of a collection element whose id is equal to the specified one.
 * Requires ID.
 * 
 * @author Zavar
 * @version 1.5
 */
public class UpdateCommand extends Command 
{
	private static UpdateCommand command;
	
	private UpdateCommand() 
	{
		super("update", "id");
	}
	
	@Override
	public boolean isNeedInput() 
	{
		return true;
	} 
	
	@Override
	public String[] getInputOrder(int type) 
	{
		if(type == 9)
		{
			return new String[] {"mode", "name", "x", "y", "studentsCount", "expelledStudents", "transferredStudents", "formOfEducation", "answer"};
		}
		else if(type == 18)
		{
			return new String[] {"mode", "name", "x", "y", "studentsCount", "expelledStudents", "transferredStudents", "formOfEducation", "answer", "adminName",
					"adminPassportID", "adminEyeColor", "adminHairColor", "adminCountry", "adminLocation", "adminX", "adminY", "adminZ"};
		}
		else if(type == 10)
		{
			return new String[] {"mode", "adminName", "adminPassportID", "adminEyeColor", "adminHairColor", "adminCountry", "adminLocation", 
					"adminX", "adminY", "adminZ"};
		}
		else
		{
			return new String[] {"mode", "value"};
		}
	}
	
	@Override
	public int execute(Environment env, Object[] args, InputStream inStream, OutputStream outStream) throws CommandException 
	{
		int parCount = 0;
		if(args.length != 1)
		{
			throw new CommandArgumentException("This command requires one argument!\n" + getUsage());
		}
		else
		{
			if(env.getCollection().isEmpty())
			{
				throw new CommandRunningException("Collection is empty!");
			}
			PrintStream pr = new PrintStream(outStream);
			StudyGroup sg;
			try
			{
				sg = env.getCollection().stream().filter(p -> p.getId() == Long.parseLong((String) args[0])).findFirst().orElseThrow(NoSuchElementException::new);
			}
			catch(NoSuchElementException e)
			{
				throw new CommandArgumentException("No such id in the collection!");
			}
			Scanner in = new Scanner(inStream);
			
			int f = -1;
			
			pr.println("Select a field to update:\n");
			
			for(Fields field : Fields.values())
			{
				pr.println(field.getId() + ": " + field.toString());
			}
			try
			{
				f = InputParser.parseInteger(outStream, in, "Field", -2, 8, false, true);
				parCount++;
				switch (f)  
				{	
				case 1:
					parCount = parCount + updateAll(sg, in, inStream, outStream);
					pr.println("Element updated");
					break;
				case 2:
					pr.println("Enter name:");
					String name = InputParser.parseString(outStream, in, "Name", Integer.MIN_VALUE, Integer.MAX_VALUE, false, false);
					sg.setName(name);
					parCount++;
					pr.println("Name updated");
					break;
				case 3:
					pr.println("Enter X coordinate:");
					Double x = InputParser.parseDouble(outStream, in, "X", -573.0d, Double.MAX_VALUE, false, false);
					pr.println("Enter Y coordinate:");
					Float y = InputParser.parseFloat(outStream, in, "Y", Float.MIN_VALUE, Float.MAX_VALUE, false, false);
					Coordinates coordinates = new Coordinates(x, y);
					sg.setCoordinates(coordinates);
					parCount++;
					pr.println("Coordinates updated");
					break;
				case 4:
					pr.println("Enter students count:");
					Long studentsCount = InputParser.parseLong(outStream, in, "Students count", 0l, Long.MAX_VALUE, false, false);
					sg.setStudentsCount(studentsCount);
					parCount++;
					pr.println("Students count updated");
					break;
				case 5:
					pr.println("Enter expelled students count:");
					int expelledStudents = InputParser.parseInteger(outStream, in, "Expelled students", 0, Integer.MAX_VALUE, false, true);
					sg.setExpelledStudents(expelledStudents);
					parCount++;
					pr.println("Expelled students updated");
					break;
				case 6:
					pr.println("Enter transferred students count:");
					long transferredStudents = InputParser.parseLong(outStream, in, "Transferred students", 0l, Long.MAX_VALUE, false, true);
					sg.setTransferredStudents(transferredStudents);
					parCount++;
					pr.println("Transferred students updated");
					break;
				case 7:
					pr.println("Enter form of education, values - " + Arrays.toString(FormOfEducation.values()));
					FormOfEducation formOfEducation = FormOfEducation.valueOf(InputParser.parseEnum(outStream, in, FormOfEducation.class, false));
					sg.setFormOfEducation(formOfEducation);
					parCount++;
					pr.println("Form of education updated");
					break;
				case 8:
					pr.println("Enter name:");
					String admName = InputParser.parseString(outStream, in, "Name", Integer.MIN_VALUE, Integer.MAX_VALUE, false, false);
					parCount++;
						
					pr.println("Enter passport ID:");
					String passportID = InputParser.parseString(outStream, in, "Passport ID", Integer.MIN_VALUE, Integer.MAX_VALUE, true, false);
					parCount++;
		
					pr.println("Enter eye color, values - " + Arrays.toString(Color.values()));
					Color eyeColor = Color.valueOf(InputParser.parseEnum(outStream, in, Color.class, false));
					parCount++;
						
					pr.println("Enter hair color, values - " + Arrays.toString(Color.values()));
					Color hairColor = Color.valueOf(InputParser.parseEnum(outStream, in, Color.class, false));
					parCount++;
						
					pr.println("Enter country, values - " + Arrays.toString(Country.values()));
					String an = InputParser.parseEnum(outStream, in, Country.class, true);
					Country nationality = null;
					if(an != null)
					{
						nationality = Country.valueOf(an);
					}
					parCount++;
						
					Location location;
					pr.println("Enter name location:");
					String nameStr = InputParser.parseString(outStream, in, "Location name", Integer.MIN_VALUE, 348, true, false);
					parCount++;
						
					pr.println("Enter X:");
					float x1 = InputParser.parseFloat(outStream, in, "X", Float.MIN_VALUE, Float.MAX_VALUE, false, true);
					parCount++;
						
					pr.println("Enter Y:");
					Float y1 = InputParser.parseFloat(outStream, in, "Y", Float.MIN_VALUE, Float.MAX_VALUE, false, false);
					parCount++;
						
					pr.println("Enter Z:");
					Long z = InputParser.parseLong(outStream, in, "Z", Long.MIN_VALUE, Long.MAX_VALUE, false, false);
					parCount++;
					
					location = new Location(x1, y1, z, nameStr);
					Person groupAdmin = new Person(admName, passportID, eyeColor, hairColor, nationality, location);
					sg.setGroupAdmin(groupAdmin);
					pr.println("Group's admin updated");
					break;
				}
			}
			catch(Exception e)
			{
				throw new CommandRunningException("Parsing error!");
			}
		pr.println("Updating is completed!");
		}
	return parCount;
	}
	
	/**
	 * Uses for commands registration.
	 * 
	 * @param commandsMap Commands' map.
	 */
	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new UpdateCommand();
		commandsMap.put(command.getName(), command);
	}
	
	@Override
	public String getHelp() 
	{
		return "This command update the element from collection!";
	}
	
	/**
	 * Updates all elements.
	 * 
	 * @param sg {@link StudyGroup}
	 * @param in {@link Scanner}
	 * @param inStream Any input stream.
	 * @param outStream Any output stream.
	 * @return Number of input parameters.
	 */
	private int updateAll(StudyGroup sg, Scanner in, InputStream inStream, OutputStream outStream)
	{
		PrintStream pr = new PrintStream(outStream);
		int parCount = 0;
		pr.println("Enter name:");
		String name = InputParser.parseString(outStream, in, "Name", Integer.MIN_VALUE, Integer.MAX_VALUE, false, false);
		sg.setName(name);
		parCount++;
		
		Coordinates coordinates = null;
		Long studentsCount = null;
		int expelledStudents = 0;
		long transferredStudents = 0;
		FormOfEducation formOfEducation = null;
		Person groupAdmin = null;
		
		pr.println("Enter X coordinate:");
		Double x = InputParser.parseDouble(outStream, in, "X", -573.0d, Double.MAX_VALUE, false, false);
		pr.println("Enter Y coordinate:");
		Float y = InputParser.parseFloat(outStream, in, "Y", Float.MIN_VALUE, Float.MAX_VALUE, false, false);
		coordinates = new Coordinates(x, y);
		sg.setCoordinates(coordinates);
		parCount++;
		
		pr.println("Enter students count:");
		studentsCount = InputParser.parseLong(outStream, in, "Students count", 0l, Long.MAX_VALUE, false, false);
		sg.setStudentsCount(studentsCount);
		parCount++;
		
		pr.println("Enter expelled students count:");
		expelledStudents = InputParser.parseInteger(outStream, in, "Expelled students", 0, Integer.MAX_VALUE, false, true);
		sg.setExpelledStudents(expelledStudents);
		parCount++;
		
		pr.println("Enter transferred students count:");
		transferredStudents = InputParser.parseLong(outStream, in, "Transferred students", 0l, Long.MAX_VALUE, false, true);
		sg.setTransferredStudents(transferredStudents);
		parCount++;
		
		pr.println("Enter form of education, values - " + Arrays.toString(FormOfEducation.values()));
		formOfEducation = FormOfEducation.valueOf(InputParser.parseEnum(outStream, in, FormOfEducation.class, false));
		sg.setFormOfEducation(formOfEducation);
		parCount++;
		
		pr.println("Do you want to update group's admin? Enter [YES] if you want");
		String answ = InputParser.parseString(outStream, in, "Answer", Integer.MIN_VALUE, Integer.MAX_VALUE, false, true);
		parCount++;
		
		if(answ.equals("YES"))
		{
			pr.println("Enter name:");
			String admName = InputParser.parseString(outStream, in, "Name", Integer.MIN_VALUE, Integer.MAX_VALUE, false, false);
			parCount++;
			
			pr.println("Enter passport ID:");
			String passportID = InputParser.parseString(outStream, in, "Passport ID", Integer.MIN_VALUE, Integer.MAX_VALUE, true, false);
			parCount++;

			pr.println("Enter eye color, values - " + Arrays.toString(Color.values()));
			Color eyeColor = Color.valueOf(InputParser.parseEnum(outStream, in, Color.class, false));
			parCount++;
			
			pr.println("Enter hair color, values - " + Arrays.toString(Color.values()));
			Color hairColor = Color.valueOf(InputParser.parseEnum(outStream, in, Color.class, false));
			parCount++;
			
			pr.println("Enter country, values - " + Arrays.toString(Country.values()));
			String an = InputParser.parseEnum(outStream, in, Country.class, true);
			Country nationality = null;
			if(an != null)
			{
				nationality = Country.valueOf(an);
			}
			parCount++;
			
			Location location;
			pr.println("Enter name location:");
			String nameStr = InputParser.parseString(outStream, in, "Location name", Integer.MIN_VALUE, 348, true, false);
			parCount++;
			
			pr.println("Enter X:");
			float x1 = InputParser.parseFloat(outStream, in, "X", Float.MIN_VALUE, Float.MAX_VALUE, false, true);
			parCount++;
			
			pr.println("Enter Y:");
			Float y1 = InputParser.parseFloat(outStream, in, "Y", Float.MIN_VALUE, Float.MAX_VALUE, false, false);
			parCount++;
			
			pr.println("Enter Z:");
			Long z = InputParser.parseLong(outStream, in, "Z", Long.MIN_VALUE, Long.MAX_VALUE, false, false);
			parCount++;
			
			location = new Location(x1, y1, z, nameStr);
			groupAdmin = new Person(admName, passportID, eyeColor, hairColor, nationality, location);
			sg.setGroupAdmin(groupAdmin);
		}
		
		return parCount;
	}
	
	/**
	 * Contains fields for choosing.
	 * 
	 * @author Zavar
	 *
	 */
	private enum Fields
	{
		ALL(1, "All fields"),
		NAME(2, "Name"),
		COORDINATES(3, "Coordinates"),
		STUDENTSCOUNT(4 ,"Students count"),
		EXPELLEDSTUDENTS(5, "Expelled students"),
		TRANSFERREDSTUDENTS(6, "Transferred students"),
		FORMOFEDUCATION(7, "Form of education"),
		GROUPADMIN(8, "Group admin");
		
		private String name;
		private int id;
		
		private Fields(int id, String name)
		{
			this.name = name;
			this.id = id;
		}
		
		public int getId() 
		{
			return id;
		}
		
		@Override
		public String toString() 
		{
			return name;
		}
	}
}
