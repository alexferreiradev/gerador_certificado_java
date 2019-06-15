package arfsoftwares.sevice.exporter.metadata;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.helper.CertificateTestHelper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class JsonMetadataGeneratorTest {

	private JsonMetadataGenerator generator = new JsonMetadataGenerator();

	@Test
	public void createMetadataBytes() throws IOException, URISyntaxException {
		List<Certificate> fakeCertificateList = new ArrayList<>();
		fakeCertificateList.add(CertificateTestHelper.createFakeCertificate(""));

		byte[] metadataBytes = generator.createMetadataBytes(fakeCertificateList);

		Assert.assertNotNull(metadataBytes);
		Assert.assertEquals("{\"IntegrityKey\":\"\",\"certificates\":[{\"participant\":{\"identifier\":\"09876543212\",\"name\":\"Alex Rabelo Ferreira\"}}]}", new String(metadataBytes));
	}

	@Test
	public void getFileName() throws IOException, URISyntaxException {
		Certificate fakeCertificate = CertificateTestHelper.createFakeCertificate("");
		String fileName = generator.getFileName(fakeCertificate);

		Assert.assertEquals("BrasilTurJavaJug_GoJava_15-06-19.json", fileName);
	}
}