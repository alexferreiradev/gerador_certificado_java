package arfsoftwares.helper;

import org.junit.Assert;
import org.junit.Test;

public class FileHelperTest {

	@Test
	public void isValidFile() {
	}

	@Test
	public void formatToValidFileName() {
		String validFileName = FileHelper.formatToValidFileName("nome teste/12/12/12.json");

		Assert.assertEquals("nome_teste-12-12-12.json", validFileName);
	}
}