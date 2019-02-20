package arfsoftwares.helper;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateHelper {

	public static String format(Date date, int style) {
		DateFormat dateInstance = DateFormat.getDateInstance(style, Locale.getDefault());
		return dateInstance.format(date);
	}
}
