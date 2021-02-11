package itmo.labs.zavar.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import itmo.labs.zavar.commands.base.Command;
import itmo.labs.zavar.exception.CommandArgumentException;
import itmo.labs.zavar.exception.CommandException;
import itmo.labs.zavar.exception.IllegalParameterException;
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
	public void execute(HashMap<String, Command> map, Stack<StudyGroup> stack, Object[] args)
			throws CommandException 
	{
		if(args.length >= 2 || args.length < 1)
		{
			throw new CommandArgumentException("This command requires name of element only, than you will be able to enter parameters for this elements!\n" + getUsage());
		}
		else
		{
			Scanner in = new Scanner(System.in);
			int id = stack.size() + 1;
			String name = (String) args[0];
			Coordinates coordinates = null;
			Long studentsCount = null;
			int expelledStudents = 0;
			long transferredStudents = 0;
			FormOfEducation formOfEducation = null;
			Person groupAdmin = null;
			
			while(coordinates == null)
			{
				System.out.print("Enter coordinates:\nX > ");
				String xStr = in.nextLine();
				Double x;
				try
				{
					x = Double.parseDouble(xStr);
				}
				catch(NumberFormatException e)
				{
					x = null;
				}
				System.out.print("Y > ");
				String yStr = in.nextLine();
				Float y;
				try
				{
					y = Float.parseFloat(yStr);
				}
				catch(NumberFormatException e)
				{
					y = null;
				}
				try
				{
					coordinates = new Coordinates(x, y);
				}
				catch(IllegalParameterException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			while(studentsCount == null)
			{
				System.out.print("Enter students count:\n> ");
				String cStr = in.nextLine();
				Long c = null;
				try
				{
					c = Long.parseLong(cStr);
				}
				catch(NumberFormatException e)
				{
					System.out.println("Illegal parameters: Count should be a number and can't be null");
				}
				
				if(c != null && c <= 0L)
				{
					c = null;
					System.out.println("Illegal parameters: Count should be greater than 0");
				}
				else
				{
					studentsCount = c;
				}
			}
			
			while(expelledStudents == 0)
			{
				boolean exc = false;
				System.out.print("Enter expelled students count:\n> ");
				String cStr = in.nextLine();
				int c;
				try
				{
					exc = false;
					c = Integer.parseInt(cStr);
				}
				catch(NumberFormatException e)
				{
					exc = true;
					c = 0;
					System.out.println("Illegal parameters: Count should be a number");
				}
				
				if(c <= 0 && !exc)
				{
					System.out.println("Illegal parameters: Count should be greater than 0");
				}
				else
				{
					expelledStudents = c;
				}
			}
			
			while(transferredStudents == 0)
			{
				boolean exc = false;
				System.out.print("Enter transferred students count:\n> ");
				String cStr = in.nextLine();
				long c;
				try
				{
					exc = false;
					c = Long.parseLong(cStr);
				}
				catch(NumberFormatException e)
				{
					exc = true;
					c = 0;
					System.out.println("Illegal parameters: Count should be a number");
				}
				
				if(c <= 0 && !exc)
				{
					System.out.println("Illegal parameters: Count should be greater than 0");
				}
				else
				{
					transferredStudents = c;
				}
			}
			
			while(formOfEducation == null)
			{
				System.out.print("Enter form of education, values - " + Arrays.toString(FormOfEducation.values()) + ":\n> ");
				String formStr = in.nextLine();
				try
				{
					formOfEducation = FormOfEducation.valueOf(formStr);
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("Illegal parameters: You can use only these values: " + Arrays.toString(FormOfEducation.values()));
				}
			}
			
			System.out.print("Does the group have an admin? [YES, NO]\n> ");
			String ansStr = in.nextLine();
			if(ansStr.equals("YES"))
			{
				String admName = null;
				while(admName == null || admName.isEmpty())
				{
					System.out.print("Enter name:\n> ");
					admName = in.nextLine();
					if(admName == null || admName.isEmpty())
					{
						System.out.println("Illegal parameters: Name can't be null or empty");
					}
				}
				System.out.print("Enter passport ID:\n> ");
				String passportID = in.nextLine();
				if(passportID.isEmpty())
				{
					passportID = null;
				}
				
				Color eyeColor = null;
				while(eyeColor == null)
				{
					System.out.print("Enter eye color, values - " + Arrays.toString(Color.values()) + ":\n> ");
					String colStr = in.nextLine();
					try
					{
						eyeColor = Color.valueOf(colStr);
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("Illegal parameters: You can use only these values: " + Arrays.toString(Color.values()));
					}
				}
				
				Color hairColor = null;
				while(hairColor == null)
				{
					System.out.print("Enter hair color, values - " + Arrays.toString(Color.values()) + ":\n> ");
					String colStr = in.nextLine();
					try
					{
						hairColor = Color.valueOf(colStr);
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("Illegal parameters: You can use only these values: " + Arrays.toString(Color.values()));
					}
				}
				
				Country nationality = null;
				String conStr = "";
				boolean en = false;
				while(!en)
				{
					System.out.print("Enter country, values - " + Arrays.toString(Country.values()) + ":\n> ");
					conStr = in.nextLine();
					try
					{
						if(!conStr.isEmpty())
						{
							nationality = Country.valueOf(conStr);
							en = true;
						}
						else
						{
							en = true;
						}
					}
					catch(IllegalArgumentException e)
					{
						en = false;
						System.out.println("Illegal parameters: You can use only these values: " + Arrays.toString(Country.values()));
					}
				}
				
				Location location;
				System.out.print("Enter name location:\n> ");
				String nameStr = null;
				while(nameStr == null)
				{
					nameStr = in.nextLine();
					if(nameStr.length() > 348)
					{
						nameStr = null;
						System.out.println("Name length can't be greater than 348");
					}
				}
				System.out.print("Enter X:\n> ");
				float x = in.nextFloat();
				System.out.print("Enter Y:\n> ");
				Float y = null;
				while(y == null)
				{
					try
					{
						y = in.nextFloat();
					}
					catch(NumberFormatException e)
					{
						System.out.println("Illegal parameters: Y should be a number");
					}
				}
				System.out.print("Enter Z:\n> ");
				Long z = null;
				while(z == null)
				{
					try
					{
						z = in.nextLong();
					}
					catch(NumberFormatException e)
					{
						System.out.println("Illegal parameters: Z should be a number");
					}
				}
				location = new Location(x, y, z, nameStr);
				groupAdmin = new Person(admName, passportID, eyeColor, hairColor, nationality, location);
			}
			stack.push(new StudyGroup(id, name, coordinates, studentsCount, expelledStudents, transferredStudents, formOfEducation, groupAdmin));
			System.out.println("Element added!");
		}
	}

	@Override
	public String getHelp() 
	{
		return "This command adds one element to collection!";
	}
}
