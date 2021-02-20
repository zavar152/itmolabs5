package itmo.labs.zavar.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Stack;

import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseEnum;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import itmo.labs.zavar.studygroup.FormOfEducation;
import itmo.labs.zavar.studygroup.StudyGroup;

public class CSVManager 
{
	private static final String[] nameMapping = new String[] {"id", "name", "coordinates", "creationDate", "studentsCount", "expelledStudents",
      		"transferredStudents", "formOfEducation", "groupAdmin"};
	private static CsvBeanWriter writer;
	private static ICsvBeanReader beanReader;
	
	public static boolean write(String path, Stack<StudyGroup> stack, OutputStream out)
	{
		try 
		{
			writer = new CsvBeanWriter(new FileWriter(path), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
			writer.writeHeader(nameMapping);
			for(StudyGroup sg : stack)
			{
				writer.write(sg, nameMapping, getProcessors0());
			}
			writer.close();
			return true;
		} 
		catch(FileNotFoundException e)
		{
			((PrintStream) out).println("Can't create file!");
			return false;
		}
		catch (IOException e) 
		{
			((PrintStream) out).println(e.getMessage());
			return false;
		} 
	} 
	
	public static boolean read(String path, Stack<StudyGroup> stack, OutputStream out)
	{ 
		try 
		{
			beanReader = new CsvBeanReader(new InputStreamReader(new FileInputStream(path)), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
			beanReader.getHeader(true);
			StudyGroup temp; 
			while ((temp = beanReader.read(StudyGroup.class, nameMapping, getProcessors1())) != null) 
			{
				long id = temp.getId();
				if(stack.stream().noneMatch(sg -> sg.getId() == id))
				{
					stack.push(temp);
				}
				else
				{
					((PrintStream) out).println("Error while parsing .csv file! Every ID must be unique (group name = "+ temp.getName() +")!");
				}
			}
			beanReader.close();
			return true;
		}
		catch(SuperCsvException | IllegalArgumentException e)
		{
			((PrintStream) out).print("Error while parsing .csv file! Check if your data is correct! >>> ");
			((PrintStream) out).println(e.getMessage());
			return false;
		}
		catch (IOException e) 
		{
			((PrintStream) out).print("Error while reading .csv file! >>> ");
			((PrintStream) out).println(e.getMessage());
			return false;
		}
	}
	
	private static CellProcessor[] getProcessors0() 
	{ 
		CellProcessor[] processors = new CellProcessor[] { 
				new NotNull(new ParseLong()),
				new NotNull(), 
				new NotNull(), 
				new NotNull(new FmtDate("yyyy-MM-dd")),
				new NotNull(new ParseLong()),
				new NotNull(new ParseInt()),
				new NotNull(new ParseLong()),
				new NotNull(new ParseEnum(FormOfEducation.class)),
				new Optional()
		};
		return processors;
	}
	
	private static CellProcessor[] getProcessors1() 
	{ 
		CellProcessor[] processors = new CellProcessor[] { 
				new NotNull(new ParseLong()),
				new NotNull(), 
				new NotNull(new ParseCoordinates()), 
				new NotNull(new ParseDate("yyyy-MM-dd")),
				new NotNull(new ParseLong()),
				new NotNull(new ParseInt()),
				new NotNull(new ParseLong()),
				new NotNull(new ParseEnum(FormOfEducation.class)),
				new Optional(new ParsePerson())
		};
		return processors;
	}
}
