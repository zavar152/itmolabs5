package itmo.labs.zavar.csv;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import itmo.labs.zavar.studygroup.Color;
import itmo.labs.zavar.studygroup.Country;
import itmo.labs.zavar.studygroup.Location;
import itmo.labs.zavar.studygroup.Person;

/**
 * Class to parse person object for {@link CellProcessor}.
 * 
 * @author Zavar
 * @version 1.1
 */
public class ParsePerson extends CellProcessorAdaptor implements StringCellProcessor 
{
	public ParsePerson() 
	{
		super();
	}
	
	public ParsePerson(final CellProcessor next) 
	{
		super(next);
	}

	@Override
	public Object execute(Object value, CsvContext context) 
	{
		validateInputNotNull(value, context);
		
		final Person person;
		
		if(value instanceof String)
		{
			try
			{
				String[] str = ((String) value).split(",");
				if(str[0].contains("name=") && str[1].contains("passportID=") && str[2].contains("eyeColor=") && str[3].contains("hairColor=") && str[4].contains("nationality=") && str[5].contains("locationX=") && str[6].contains("locationY=") && str[7].contains("locationZ=") && str[8].contains("locationName="))
				{
					person = new Person(str[0].split("=")[1].trim(), str[1].split("=")[1].trim(), Color.valueOf(str[2].split("=")[1].trim()), Color.valueOf(str[3].split("=")[1].trim()), Country.valueOf(str[4].split("=")[1].trim()), new Location(Float.parseFloat(str[5].split("=")[1].trim()), Float.parseFloat(str[6].split("=")[1].trim()), Long.parseLong(str[7].split("=")[1].trim()), str[8].split("=")[1].trim()));
				}
				else
				{
					throw new IllegalArgumentException();
				}
			}
    		catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e)
    		{
				throw new SuperCsvCellProcessorException(String.format("'%s' could not be parsed as an Person", value),
						context, this, e);
    		}
		}
		else
		{
			final String actualClassName = value.getClass().getName();
			throw new SuperCsvCellProcessorException(String.format(
				"the input value should be of type String but is of type %s", actualClassName), context, this);
		}
		
		return next.execute(person, context);
	}
	
	
}
