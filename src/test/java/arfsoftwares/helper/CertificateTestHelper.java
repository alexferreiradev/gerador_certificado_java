package arfsoftwares.helper;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.data.model.Participant;
import arfsoftwares.sevice.dto.ReaderCommand;
import arfsoftwares.sevice.reader.CsvParticipantReader;
import arfsoftwares.sevice.reader.ParticipantsReader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public final class CertificateTestHelper {

	public static Certificate createFakeCertificate(String expectedFilePath) throws IOException, URISyntaxException {
		Certificate certificate = new Certificate();

		certificate.setFileName("fake_file");
		certificate.setFileExtension("pdf");
		certificate.setFileExtension("pdf");
		File fileExpected = new File(CertificateTestHelper.class.getResource(expectedFilePath).toURI());
		if (expectedFilePath != null && !expectedFilePath.isEmpty()) {
			certificate.setFileContent(FileUtils.readFileToByteArray(fileExpected));
		}

		File participantListFile = new File(CertificateTestHelper.class.getResource("/helper/certificate/valid_participat_list.csv").toURI());
		ParticipantsReader csvParticipantReader = new CsvParticipantReader(participantListFile.getAbsolutePath());
		ReaderCommand readerCommand = new ReaderCommand();
		List<Participant> participantList = csvParticipantReader.readParticipant(readerCommand);
		certificate.setParticipant(participantList.get(0));

		return certificate;
	}
}
