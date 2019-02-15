package arfsoftwares.sevice.generator;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.helper.RegexHelper;
import arfsoftwares.sevice.dto.CertificatorGeneratorCommand;
import arfsoftwares.sevice.reader.CsvParticipantReader;
import arfsoftwares.sevice.reader.ParticipantsReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GoJavaGeneratorTest {

	private CertificateGenerator generator;

	@Before
	public void setUp() {
		generator = new GoJavaGenerator();
	}

	@Test
	public void generateCertificate() {
		CertificatorGeneratorCommand command = createValidCommand();
		List<Certificate> certificateList = generator.generateCertificates(command);

		Assert.assertEquals(certificateList.size(), 8);
		String fileName = certificateList.get(0).getFileName();
		Assert.assertNotNull(RegexHelper.find(fileName, "certificado_"));
		Assert.assertEquals(certificateList.get(0).getFileExtension(), "pdf");

		byte[] expectedBytes = createExpectedCertificateContent();
		Assert.assertEquals(certificateList.get(0).getFileContent(), expectedBytes);
	}

	private CertificatorGeneratorCommand createValidCommand() {
		CertificatorGeneratorCommand command = new CertificatorGeneratorCommand();

		ParticipantsReader reader = new CsvParticipantReader("/reader/test_list1.csv");
		command.setParticipantList(reader.readParticipant(null));

		return command;
	}

	private byte[] createExpectedCertificateContent() {
		return new byte[0];
	}
}