package arfsoftwares.sevice.generator;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.helper.RegexHelper;
import arfsoftwares.sevice.dto.CertificatorGeneratorCommand;
import arfsoftwares.sevice.reader.CsvParticipantReader;
import arfsoftwares.sevice.reader.ParticipantsReader;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class GoJavaGeneratorTest {

	private CertificateGenerator generator;

	@Before
	public void setUp() {
		generator = new GoJavaGenerator();
	}

	@Test
	public void generateCertificate() throws URISyntaxException, IOException {
		CertificatorGeneratorCommand command = createValidCommand();
		List<Certificate> certificateList = generator.generateCertificates(command);

		Assert.assertEquals(certificateList.size(), 8);
		String fileName = certificateList.get(0).getFileName();
		Assert.assertNotNull(RegexHelper.find(fileName, "certificado_"));
		Assert.assertEquals(certificateList.get(0).getFileExtension(), "pdf");

		// TODO - Alterar forma de validacao de conteudo de certificado criado com arquivo esperado
		byte[] expectedBytes = createExpectedCertificateContent();
//		Assert.assertTrue(ByteArrayHelper.isSameByteArray(certificateList.get(0).getFileContent(), expectedBytes));
	}

	private CertificatorGeneratorCommand createValidCommand() throws URISyntaxException {
		CertificatorGeneratorCommand command = new CertificatorGeneratorCommand();
		String fileNameRes = "/reader/test_list1.csv";
		File file = new File(getClass().getResource(fileNameRes).toURI());

		ParticipantsReader reader = new CsvParticipantReader(file.getAbsolutePath());
		command.setParticipantList(reader.readParticipant(null));

		return command;
	}

	private byte[] createExpectedCertificateContent() throws URISyntaxException, IOException {
		return FileUtils.readFileToByteArray(new File(getClass().getResource("/generator/certificado_expected.pdf").toURI()));
	}
}