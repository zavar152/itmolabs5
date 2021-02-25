package itmo.labs.zavar.csv;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import itmo.labs.zavar.studygroup.Person;

/**
 * Class to create a string from person object for {@link CellProcessor}.
 * 
 * @author Zavar
 * @version 1.0
 */
public class FmtPerson extends CellProcessorAdaptor implements StringCellProcessor {
	public FmtPerson() {
		super();
	}

	public FmtPerson(final StringCellProcessor next) {
		super(next);
	}

	@Override
	public <T> T execute(final Object value, final CsvContext context) {
		validateInputNotNull(value, context);

		if (!(value instanceof Person)) {
			throw new SuperCsvCellProcessorException(Person.class, value, context, this);
		}

		final Person person = (Person) value;

		String result = "name=" + person.getName() + ", passportID=" + person.getPassportID() + ", eyeColor="
				+ person.getEyeColor() + ", hairColor=" + person.getHairColor() + ", nationality="
				+ person.getNationality() + ", locationX=" + person.getLocation().getX() + ", locationY="
				+ person.getLocation().getY() + ", locationZ=" + person.getLocation().getZ() + ", locationName="
				+ person.getLocation().getName();

		return next.execute(result, context);
	}

}
