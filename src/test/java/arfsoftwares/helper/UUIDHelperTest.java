package arfsoftwares.helper;

import org.junit.Assert;
import org.junit.Test;

public class UUIDHelperTest {

	@Test
	public void generateUUIDToValidation() {
		String text = "Teste";
		String uuid = UUIDHelper.generateUUID_SHA256(text);

		Assert.assertNotNull(uuid);
		Assert.assertEquals("89f308210c7c7820bad0974f31e751bfa433d2066a93e808947c3188dedba6e3", uuid);
	}

	@Test
	public void uuidMuda_comInformacoesDiferentes() {
		String text = "Teste-123";
		String text2 = "Teste-1";
		String uuid = UUIDHelper.generateUUID_SHA256(text);
		String uuid2 = UUIDHelper.generateUUID_SHA256(text2);

		Assert.assertNotEquals(uuid, uuid2);
		Assert.assertEquals("8cc930ab05977ca76edb656c40a6ca9a29ddedaacb3d69923acd0e30717f5e96", uuid);
		Assert.assertEquals("6484d77b1a9edd63b7a3b68be3823dc98c12a63d080d31e0ef1aaaa2aa9edde2", uuid2);
	}

	@Test
	public void uuidCompativelComInformacoesDeCertificado() {
		String text = "5432456-NomeDoEvento-12/08/19-GOJava";
		String uuid = UUIDHelper.generateUUID_SHA256(text);

		Assert.assertEquals("0a6e74dd3cdd7066ab7975210bdcb714256cec454ffd0a54828b2aad5c1c202e", uuid);
	}
}