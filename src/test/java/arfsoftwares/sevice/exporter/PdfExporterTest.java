package arfsoftwares.sevice.exporter;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.helper.CertificateTestHelper;
import arfsoftwares.helper.StreamHelper;
import arfsoftwares.sevice.exporter.metadata.JsonMetadataGenerator;
import arfsoftwares.util.CertificateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PdfExporterTest {

	private CertificateExporter exporter;
	private JsonMetadataGenerator metadataGenerator;

	@Before
	public void setUp() {
		metadataGenerator = new JsonMetadataGenerator();
		exporter = new PdfExporter(metadataGenerator);
	}

	@Test
	public void export() throws IOException, URISyntaxException {
		Certificate certificate = CertificateTestHelper.createFakeCertificate("/export/expected_file.pdf");
		certificate.setUuid("0a6e74dd3cdd7066ab7975210bdcb714256cec454ffd0a54828b2aad5c1c202e");
		List<Certificate> certificates = new ArrayList<>();
		certificates.add(certificate);
		exporter.export(certificates);

		validateCertificateContent(certificate);
		validateCertificateMetadata(certificate);
	}

	private void validateCertificateMetadata(Certificate certificate) throws IOException {
		File file = new File("./certificados/" + metadataGenerator.getFileName(certificate));
		Assert.assertTrue(file.exists());
		Assert.assertTrue(file.isFile());
		Assert.assertTrue(file.getName().endsWith(".json"));

		validateUUIDCertificate(certificate, file);
	}

	private void validateUUIDCertificate(Certificate certificate, File file) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(file);
		String jsonText = StreamHelper.parseStream(fileInputStream);
		JsonObject jsonObject = new Gson().fromJson(jsonText, JsonObject.class);
		String uuidFromJson = jsonObject.get("certificates").getAsJsonArray().get(0).getAsJsonObject().get("token").getAsString();

		Assert.assertEquals(certificate.getUuid(), uuidFromJson);
	}

	private void validateCertificateContent(Certificate certificate) throws IOException {
		byte[] fileContentExpected = certificate.getFileContent();
		File file = new File("./certificados/" + CertificateUtil.createFileName(certificate));

		Assert.assertTrue(Arrays.equals(FileUtils.readFileToByteArray(file), fileContentExpected));
	}
}