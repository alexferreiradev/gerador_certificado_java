package arfsoftwares.helper;

import arfsoftwares.data.model.Certificate;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class CertificateTestHelperTest {

	@Test
	public void createFakeCertificate() throws IOException, URISyntaxException {
		String expectedFilePath = "";
		Certificate certificate = CertificateTestHelper.createFakeCertificate(expectedFilePath);

		Assert.assertNotNull(certificate);
		Assert.assertEquals("fake_file", certificate.getFileName());
		Assert.assertEquals("pdf", certificate.getFileExtension());
		Assert.assertEquals("09876543212", certificate.getParticipant().getCpf());
	}
}