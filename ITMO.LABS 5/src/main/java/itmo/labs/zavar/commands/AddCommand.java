package itmo.labs.zavar.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
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

public class AddCommand extends Command
{
	private static AddCommand command;
	
	public AddCommand() 
	{
		super("add", "element_name", "{ELEMENT}");
	}
	
	public static void register(HashMap<String, Command> commandsMap)
	{
		command = new AddCommand();
		commandsMap.put(command.getName(), command);
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
		if(args.length >= 2 || args.length < 1)
		{
			throw new CommandArgumentException("This command requires name of element only, than you will be able to enter parameters for this elements!\n" + getUsage());
		}
		else
		{
			Scanner in = new Scanner(inStream);
			int id = env.getCollection().size() + 1;
			String name = (String) args[0];
			Coordinates coordinates = null;
			Long studentsCount = null;
			int expelledStudents = 0;
			long transferredStudents = 0;
			FormOfEducation formOfEducation = null;
			Person groupAdmin = null;
			
			((PrintStream) outStream).println("Enter X coordinate:");
			Double x = InputParser.parseDouble(outStream, in, "X", -573.0d, Double.MAX_VALUE, false, false);
			((PrintStream) outStream).println("Enter Y coordinate:");
			Float y = InputParser.parseFloat(outStream, in, "Y", Float.MIN_VALUE, Float.MAX_VALUE, false, false);
			coordinates = new Coordinates(x, y);
			parCount++;
			
			((PrintStream) outStream).println("Enter students count:");
			studentsCount = InputParser.parseLong(outStream, in, "Students count", 0l, Long.MAX_VALUE, false, false);
			parCount++;
			
			((PrintStream) outStream).println("Enter expelled students count:");
			expelledStudents = InputParser.parseInteger(outStream, in, "Expelled students", 0, Integer.MAX_VALUE, false, true);
			parCount++;
			
			((PrintStream) outStream).println("Enter transferred students count:");
			transferredStudents = InputParser.parseLong(outStream, in, "Transferred students", 0l, Long.MAX_VALUE, false, true);
			parCount++;
			
			((PrintStream) outStream).println("Enter form of education, values - " + Arrays.toString(FormOfEducation.values()));
			formOfEducation = FormOfEducation.valueOf(InputParser.parseEnum(outStream, in, FormOfEducation.class, false));
			parCount++;
			
			((PrintStream) outStream).println("Does the group have an admin? [YES]");
			String answ = InputParser.parseString(outStream, in, "Answer", Integer.MIN_VALUE, Integer.MAX_VALUE, false, false);
			parCount++;
			
			if(answ.equals("YES"))
			{
				((PrintStream) outStream).println("Enter name:");
				String admName = InputParser.parseString(outStream, in, "Name", Integer.MIN_VALUE, Integer.MAX_VALUE, false, false);
				parCount++;
				
				((PrintStream) outStream).println("Enter passport ID:");
				String passportID = InputParser.parseString(outStream, in, "Passport ID", Integer.MIN_VALUE, Integer.MAX_VALUE, true, false);
				parCount++;

				((PrintStream) outStream).println("Enter eye color, values - " + Arrays.toString(Color.values()));
				Color eyeColor = Color.valueOf(InputParser.parseEnum(outStream, in, Color.class, false));
				parCount++;
				
				((PrintStream) outStream).println("Enter hair color, values - " + Arrays.toString(Color.values()));
				Color hairColor = Color.valueOf(InputParser.parseEnum(outStream, in, Color.class, false));
				parCount++;
				
				((PrintStream) outStream).println("Enter country, values - " + Arrays.toString(Country.values()));
				String an = InputParser.parseEnum(outStream, in, Country.class, true);
				Country nationality = null;
				if(an != null)
				{
					nationality = Country.valueOf(an);
				}
				parCount++;
				
				Location location;
				((PrintStream) outStream).println("Enter name location:");
				String nameStr = InputParser.parseString(outStream, in, "Location name", Integer.MIN_VALUE, 348, true, false);
				parCount++;
				
				((PrintStream) outStream).println("Enter X:");
				float x1 = InputParser.parseFloat(outStream, in, "X", Float.MIN_VALUE, Float.MAX_VALUE, false, true);
				parCount++;
				
				((PrintStream) outStream).println("Enter Y:");
				Float y1 = InputParser.parseFloat(outStream, in, "Y", Float.MIN_VALUE, Float.MAX_VALUE, false, false);
				parCount++;
				
				((PrintStream) outStream).println("Enter Z:");
				Long z = InputParser.parseLong(outStream, in, "Z", Long.MIN_VALUE, Long.MAX_VALUE, false, false);
				parCount++;
				
				location = new Location(x1, y1, z, nameStr);
				groupAdmin = new Person(admName, passportID, eyeColor, hairColor, nationality, location);
			}
			env.getCollection().push(new StudyGroup(id, name, coordinates, studentsCount, expelledStudents, transferredStudents, formOfEducation, groupAdmin));
			((PrintStream) outStream).println("Element added!");
		}
		return parCount;
	}

	@Override
	public String getHelp() 
	{
		return "This command adds one element to collection!";
	}
}
