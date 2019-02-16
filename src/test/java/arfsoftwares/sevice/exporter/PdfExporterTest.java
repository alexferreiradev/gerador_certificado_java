package arfsoftwares.sevice.exporter;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.util.CertificateUtiltest;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class PdfExporterTest {

	private CertificateExporter exporter;

	@Before
	public void setUp() {
		exporter = new PdfExporter();
	}

	@Test
	public void export() throws IOException, URISyntaxException {
		Certificate certificate = createCertificateToTest();

		exporter.export(certificate);

		validateCertificateContent(certificate);
	}

	private void validateCertificateContent(Certificate certificate) throws IOException {
		byte[] fileContentExpected = certificate.getFileContent();
		File file = new File("./certificados/" + CertificateUtiltest.createFileName(certificate));

		Assert.assertTrue(Arrays.equals(FileUtils.readFileToByteArray(file), fileContentExpected));
	}

	private Certificate createCertificateToTest() throws URISyntaxException, IOException {
		Certificate certificate = new Certificate();
		certificate.setFileName("test_alex");
		certificate.setFileExtension("pdf");
		File fileExpected = new File(getClass().getResource("/export/expected_file.pdf").toURI());
		certificate.setFileContent(FileUtils.readFileToByteArray(fileExpected));

		return certificate;
	}
}