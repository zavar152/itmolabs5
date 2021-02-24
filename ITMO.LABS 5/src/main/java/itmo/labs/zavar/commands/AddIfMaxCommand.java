package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
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
 * Adds a new element to the collection if its value exceeds the value of the largest element in this collection.
 * Requires {ELEMENT}.
 * 
 * @author Zavar
 * @version 1.1
 */
public class AddIfMaxCommand extends Command
{

	private AddIfMaxCommand() 
	{
		super("add_if_max", "{ELEMENT}");
	}
	
	@Override
	public boolean isNeedInput() 
	{
		return true;
	} 
	
	@Override
	public String[] getInputOrder(int type) 
	{
		if(type == 8)
		{
			return new String[] {"name", "x", "y", "studentsCount", "expelledStudents", "transferredStudents", "formOfEducation", "answer"};
		}
		else
		{
			return new String[] {"name", "x", "y", "studentsCount", "expelledStudents", "transferredStudents", "formOfEducation", "answer", "adminName",
					"adminPassportID", "adminEyeColor", "adminHairColor", "adminCountry", "adminLocation", "adminX", "adminY", "adminZ"};
		}
	}
	
	@Override
	public void execute(Environment env, Object[] args, InputStream inStream, OutputStream outStream) throws CommandException 
	{
		if(args.length > 0)
		{
			throw new CommandArgumentException("This command doesn't require any arguments!\n" + getUsage());
		}
		else
		{
			if(env.getCollection().isEmpty())
			{
				throw new CommandRunningException("Collection is empty!");
			}
			PrintStream pr = new PrintStream(outStream);
			Scanner in = new Scanner(inStream);
			long id;
			
			long maxId;
			try
			{
				maxId = env.getCollection().stream().max(Comparator.comparingLong(StudyGroup::getId)).orElseThrow(NoSuchElementException::new).getId();
				id = maxId + 1;
			}
			catch(NoSuchElementException e)
			{
				id = 1;
			}
			catch(Exception e)
			{
				throw new CommandRunningException("Unexcepted error! " + e.getMessage());
			}
			
			try
			{
				pr.println("Enter name:");
				String name = InputParser.parseString(outStream, in, "Name", Integer.MIN_VALUE, Integer.MAX_VALUE, false, false);
				
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
				
				pr.println("Enter students count:");
				studentsCount = InputParser.parseLong(outStream, in, "Students count", 0l, Long.MAX_VALUE, false, false);
				
				pr.println("Enter expelled students count:");
				expelledStudents = InputParser.parseInteger(outStream, in, "Expelled students", 0, Integer.MAX_VALUE, false, true);
				
				pr.println("Enter transferred students count:");
				transferredStudents = InputParser.parseLong(outStream, in, "Transferred students", 0l, Long.MAX_VALUE, false, true);
				
				pr.println("Enter form of education, values - " + Arrays.toString(FormOfEducation.values()));
				formOfEducation = FormOfEducation.valueOf(InputParser.parseEnum(outStream, in, FormOfEducation.class, false));
				
				pr.println("Does the group have an admin? [YES]"); 
				String answ = InputParser.parseString(outStream, in, "Answer", Integer.MIN_VALUE, Integer.MAX_VALUE, false, true);
				
				if(answ.equals("YES"))
				{
					pr.println("Enter name:");
					String admName = InputParser.parseString(outStream, in, "Name", Integer.MIN_VALUE, Integer.MAX_VALUE, false, false);
					
					pr.println("Enter passport ID:");
					String passportID = InputParser.parseString(outStream, in, "Passport ID", Integer.MIN_VALUE, Integer.MAX_VALUE, true, false);
	
					pr.println("Enter eye color, values - " + Arrays.toString(Color.values()));
					Color eyeColor = Color.valueOf(InputParser.parseEnum(outStream, in, Color.class, false));
					
					pr.println("Enter hair color, values - " + Arrays.toString(Color.values()));
					Color hairColor = Color.valueOf(InputParser.parseEnum(outStream, in, Color.class, false));
					
					pr.println("Enter country, values - " + Arrays.toString(Country.values()));
					String an = InputParser.parseEnum(outStream, in, Country.class, true);
					Country nationality = null;
					if(an != null)
					{
						nationality = Country.valueOf(an);
					}
					
					Location location;
					pr.println("Enter name location:");
					String nameStr = InputParser.parseString(outStream, in, "Location name", Integer.MIN_VALUE, 348, true, false);
					
					pr.println("Enter X:");
					float x1 = InputParser.parseFloat(outStream, in, "X", Float.MIN_VALUE, Float.MAX_VALUE, false, true);
					
					pr.println("Enter Y:");
					Float y1 = InputParser.parseFloat(outStream, in, "Y", Float.MIN_VALUE, Float.MAX_VALUE, false, false);
					
					pr.println("Enter Z:");
					Long z = InputParser.parseLong(outStream, in, "Z", Long.MIN_VALUE, Long.MAX_VALUE, false, false);
					
					location = new Location(x1, y1, z, nameStr);
					groupAdmin = new Person(admName, passportID, eyeColor, hairColor, nationality, location);
				}
				StudyGroup temp1 = new StudyGroup(id, name, coordinates, studentsCount, expelledStudents, transferredStudents, formOfEducation, groupAdmin);
				StudyGroup temp2 = Collections.max(env.getCollection());
				if(temp1.compareTo(temp2) > 0)
				{
					env.getCollection().push(temp1);
					pr.println("Element added!");
				}
				else
				{
					pr.println("Element less than max element in collection!");
				}
			}
			catch(InputMismatchException e)
			{
				throw new CommandRunningException("Input closed!");
			}
			catch(Exception e)
			{
				throw new CommandRunningException("Parsing error!");
			}
		}
	}
	
	/**
	 * Uses for commands registration.
	 * 
	 * @param commandsMap Commands' map.
	 */
	public static void register(HashMap<String, Command> commandsMap)
	{
		AddIfMaxCommand command = new AddIfMaxCommand();
		commandsMap.put(command.getName(), command);
	}

	@Override
	public String getHelp() 
	{
		return "This command added element if its creation date is greater than max value in collection!";
	}
}
