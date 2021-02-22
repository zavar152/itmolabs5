package itmo.labs.zavar.csv;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import itmo.labs.zavar.studygroup.Coordinates;

/**
 * Class to parse coordinates object for {@link CellProcessor}.
 * 
 * @author Zavar
 * @version 1.1
 */
public class ParseCoordinates extends CellProcessorAdaptor implements StringCellProcessor
{
	public ParseCoordinates()
	{
		super();
	}
	
	public ParseCoordinates(final CellProcessor next) 
	{
		super(next);
	}
	
	@Override
	public Object execute(final Object value, final CsvContext context)
	{
		validateInputNotNull(value, context);
		
		final Coordinates coordinates;
		
		if(value instanceof String)
		{
			String[] str = ((String) value).split(",");
			if(str[0].contains("x=") && str[1].contains("y="))
			{
	    		str[0] = str[0].split("=")[1].trim();
	    		str[1] = str[1].split("=")[1].trim();
			}
			else
			{
				throw new IllegalArgumentException();
			}
    		try
    		{
    			coordinates = new Coordinates(Double.parseDouble(str[0]), Float.parseFloat(str[1]));
    		}
    		catch(IllegalArgumentException e)
    		{
				throw new SuperCsvCellProcessorException(String.format("'%s' could not be parsed as an Coordinates", value),
						context, this, e);
    		}
		}
		else
		{
			final String actualClassName = value.getClass().getName();
			throw new SuperCsvCellProcessorException(String.format(
				"the input value should be of type String but is of type %s", actualClassName), context, this);
		}
		
		return next.execute(coordinates, context);
	}
}
