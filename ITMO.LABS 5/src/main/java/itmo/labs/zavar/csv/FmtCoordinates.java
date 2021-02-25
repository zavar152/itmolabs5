package itmo.labs.zavar.csv;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import itmo.labs.zavar.studygroup.Coordinates;

/**
 * Class to create a string from coordinates object for {@link CellProcessor}.
 * 
 * @author Zavar
 * @version 1.0
 */
public class FmtCoordinates extends CellProcessorAdaptor implements StringCellProcessor {
	public FmtCoordinates() {
		super();
	}

	public FmtCoordinates(final StringCellProcessor next) {
		super(next);
	}

	@Override
	public <T> T execute(final Object value, final CsvContext context) {
		validateInputNotNull(value, context);

		if (!(value instanceof Coordinates)) {
			throw new SuperCsvCellProcessorException(Coordinates.class, value, context, this);
		}

		final Coordinates coord = (Coordinates) value;

		String result = "x=" + coord.getX() + ", y=" + coord.getY();

		return next.execute(result, context);
	}

}
