package arfsoftwares.helper;

import org.junit.Assert;
import org.junit.Test;

public class UUIDHelperTest {

	@Test
	public void generateUUIDToValidation() {
		String text = "Teste";
		String uuid = UUIDHelper.generateUUIDToValidation(text);

		Assert.assertNotNull(uuid);
	}
}