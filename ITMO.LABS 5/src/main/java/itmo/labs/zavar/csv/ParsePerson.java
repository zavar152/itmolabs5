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
				person = new Person(str[0].substring(5), str[1].substring(12), Color.valueOf(str[2].substring(10)), Color.valueOf(str[3].substring(11)), Country.valueOf(str[4].substring(13)), new Location(Float.parseFloat(str[5].substring(11)), Float.parseFloat(str[6].substring(11)), Long.parseLong(str[7].substring(11)), str[8].substring(14)));
			}
    		catch(IllegalArgumentException e)
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
