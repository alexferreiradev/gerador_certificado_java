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
		validateParams(args);

		System.out.println("Carregando lista de participantes de CSV");
		ParticipantsReader participantReader = new CsvParticipantReader(args[1]);
		List<Participant> participantList = createParticipantList(participantReader);
		System.out.println(participantList.size() + " participantes encontrados");

		System.out.println("Criando lista de certificados");
		List<Certificate> certificateList = createCertificateList(participantList);
		System.out.println(certificateList.size() + " participantes encontrados");

		System.out.println("Exportando certificados como PDF");
		exportCertificates(certificateList);
		System.out.println("Todos certificados exportados com sucesso");
	}

	private static void exportCertificates(List<Certificate> certificateList) {
		CertificateExporter pdfExporter = new PdfExporter();
		for (Certificate certificate : certificateList) {
			System.out.println("Exportando certificado: " + certificate.getFileName());
			pdfExporter.export(certificate);
			System.out.println("Certificado exportado");
		}
	}

	private static List<Certificate> createCertificateList(List<Participant> participantList) {
		CertificateGenerator generator = new GoJavaGenerator();
		CertificatorGeneratorCommand generatorCommand = new CertificatorGeneratorCommand();
		generatorCommand.setParticipantList(participantList);
		generatorCommand.setEvent(participantList.get(0).getEvent());
		return generator.generateCertificates(generatorCommand);
	}

	private static List<Participant> createParticipantList(ParticipantsReader participantReader) {
		ReaderCommand readerCommand = new ReaderCommand();
		return participantReader.readParticipant(readerCommand);
	}

	private static void validateParams(String[] args) {
		if (args == null || args.length < 1) {
			System.out.println("Utilize: java -jar Main file.csv\n\nSendo que file.csv é um arquivo com os dados dos participantes");
			throw new IllegalArgumentException("Argumentos não estão válidos");
		}
	}
}
