package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.commands.base.Environment;
import itmo.labs.zavar.commands.base.InputParser;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.studygroup.Color;
import itmo.labs.zavar.studygroup.Coordinates;
import itmo.labs.zavar.studygroup.Country;
import itmo.labs.zavar.studygroup.FormOfEducation;
import itmo.labs.zavar.studygroup.Location;
import itmo.labs.zavar.studygroup.Person;
import itmo.labs.zavar.studygroup.StudyGroup;

public class AddIfMinCommand extends Command
{
	private static AddIfMinCommand command;
	
	public AddIfMinCommand() 
	{
		super("add_if_min", "{ELEMENT}");
	}
	
	@Override
	public boolean isNeedInput() 
	{
		return true;
	} 
	
	@Override
	public int execute(Environment env, Object[] args, InputStream inStream, OutputStream outStream) throws CommandException 
	{
		int parCount = 0;
		if(args.length > 0)
		{
			throw new CommandArgumentException("This command doesn't require any arguments!\n" + getUsage());
		}
		else
		{
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
			
			pr.println("Enter name:");
			String name = InputParser.parseString(outStream, in, "Name", Integer.MIN_VALUE, Integer.MAX_VALUE, false, false);
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
			parCount++;
			
			pr.println("Enter students count:");
			studentsCount = InputParser.parseLong(outStream, in, "Students count", 0l, Long.MAX_VALUE, false, false);
			parCount++;
			
			pr.println("Enter expelled students count:");
			expelledStudents = InputParser.parseInteger(outStream, in, "Expelled students", 0, Integer.MAX_VALUE, false, true);
			parCount++;
			
			pr.println("Enter transferred students count:");
			transferredStudents = InputParser.parseLong(outStream, in, "Transferred students", 0l, Long.MAX_VALUE, false, true);
			parCount++;
			
			pr.println("Enter form of education, values - " + Arrays.toString(FormOfEducation.values()));
			formOfEducation = FormOfEducation.valueOf(InputParser.parseEnum(outStream, in, FormOfEducation.class, false));
			parCount++;
			
			pr.println("Does the group have an admin? [YES]");
			String answ = InputParser.parseString(outStream, in, "Answer", Integer.MIN_VALUE, Integer.MAX_VALUE, false, false);
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
			}
			StudyGroup temp1 = new StudyGroup(id, name, coordinates, studentsCount, expelledStudents, transferredStudents, formOfEducation, groupAdmin);
			StudyGroup temp2 = Collections.min(env.getCollection());
			if(temp1.compareTo(temp2) == -1)
			{
				env.getCollection().push(temp1);
				pr.println("Element added!");
			}
			else
			{
				pr.println("Element is greater than min element in collection!");
			}
		}
		return parCount;
	}
	
	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new AddIfMinCommand();
		commandsMap.put(command.getName(), command);
	}

	@Override
	public String getHelp() 
	{
		return "This command added element if its creation date is less than min value in collection!";
	}
}
