package arfsoftwares.util;

import arfsoftwares.data.model.Certificate;
import org.junit.Assert;
import org.junit.Test;

public class CertificateUtilTest {

	@Test
	public void createFileName() {
		Certificate cert = new Certificate();
		cert.setFileName("teste");
		cert.setFileExtension("pdf");
		String fileName = CertificateUtil.createFileName(cert);

		Assert.assertEquals("teste.pdf", fileName);
	}
}