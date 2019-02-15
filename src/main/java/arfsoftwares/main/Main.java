package arfsoftwares.main;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.data.model.Participant;
import arfsoftwares.sevice.dto.CertificatorGeneratorCommand;
import arfsoftwares.sevice.dto.ReaderCommand;
import arfsoftwares.sevice.exporter.CertificateExporter;
import arfsoftwares.sevice.exporter.PdfExporter;
import arfsoftwares.sevice.generator.CertificateGenerator;
import arfsoftwares.sevice.generator.GoJavaGenerator;
import arfsoftwares.sevice.reader.CsvParticipantReader;
import arfsoftwares.sevice.reader.ParticipantsReader;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		ParticipantsReader participantReader = new CsvParticipantReader();
		ReaderCommand readerCommand = new ReaderCommand();
		List<Participant> participantList = participantReader.readParticipant(readerCommand);
		CertificateGenerator generator = new GoJavaGenerator();
		CertificatorGeneratorCommand generatorCommand = new CertificatorGeneratorCommand();
		Certificate certificate = generator.generateCertificate(generatorCommand);
		CertificateExporter pdfExporter = new PdfExporter();

		pdfExporter.export(certificate);
	}
}
