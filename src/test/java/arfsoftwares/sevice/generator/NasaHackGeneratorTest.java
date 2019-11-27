package arfsoftwares.sevice.generator;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.data.model.Participant;
import arfsoftwares.helper.RegexHelper;
import arfsoftwares.sevice.dto.CertificatorGeneratorCommand;
import arfsoftwares.sevice.generator.token.TokenGenerator_SHA256;
import arfsoftwares.sevice.reader.CsvParticipantReader;
import arfsoftwares.sevice.reader.ParticipantsReader;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NasaHackGeneratorTest {

	private CertificateGenerator generator;

	@Before
	public void setUp() {
		generator = new NasaHackGenerator(new TokenGenerator_SHA256());
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

	@Test
	public void test_output() throws URISyntaxException, IOException {
		CertificatorGeneratorCommand command = createValidCommand();
		List<Participant> participantList = new ArrayList<>();
		participantList.add(command.getParticipantList().get(0));
		command.setParticipantList(participantList);
		List<Certificate> certificateList = generator.generateCertificates(command);
		Certificate certificate = certificateList.get(0);
		byte[] fileContent = certificate.getFileContent();
		FileOutputStream fileOutputStream = new FileOutputStream(new File("teste_output.pdf"));
		fileOutputStream.write(fileContent);
		fileOutputStream.flush();
		fileOutputStream.close();
	}

	private CertificatorGeneratorCommand createValidCommand() throws URISyntaxException {
		CertificatorGeneratorCommand command = new CertificatorGeneratorCommand();
		String fileNameRes = "/reader/test_list_nasa_hack.csv";
		File file = new File(getClass().getResource(fileNameRes).toURI());

		ParticipantsReader reader = new CsvParticipantReader(file.getAbsolutePath());
		command.setParticipantList(reader.readParticipant(null));
		String backgroundFileName = "Nasa_hack_1.0.0.jpeg";
		URI rootProject = this.getClass().getResource("/").toURI();
		File file1 = new File(rootProject);
		Path path = file1.toPath().getParent().getParent().getParent().resolve(backgroundFileName);
		command.setBackgroundFileName(path.toAbsolutePath().toString());

		return command;
	}

	private byte[] createExpectedCertificateContent() throws URISyntaxException, IOException {
		return FileUtils.readFileToByteArray(new File(getClass().getResource("/generator/certificado_expected.pdf").toURI()));
	}
}