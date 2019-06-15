package arfsoftwares.main;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.data.model.Participant;
import arfsoftwares.sevice.dto.CertificatorGeneratorCommand;
import arfsoftwares.sevice.dto.ReaderCommand;
import arfsoftwares.sevice.exporter.CertificateExporter;
import arfsoftwares.sevice.exporter.PdfExporter;
import arfsoftwares.sevice.exporter.metadata.JsonMetadataGenerator;
import arfsoftwares.sevice.generator.CertificateGenerator;
import arfsoftwares.sevice.generator.GoJavaGenerator;
import arfsoftwares.sevice.generator.token.TokenGenerator_SHA256;
import arfsoftwares.sevice.reader.CsvParticipantReader;
import arfsoftwares.sevice.reader.ParticipantsReader;

import java.io.File;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		validateParams(args);

		System.out.println("Carregando lista de participantes de CSV");
		ParticipantsReader participantReader = new CsvParticipantReader(createFilePathFromArgs(args));
		List<Participant> participantList = createParticipantList(participantReader);
		System.out.println(participantList.size() + " participantes encontrados");

		System.out.println("Criando lista de certificados");
		List<Certificate> certificateList = createCertificateList(participantList, args);
		System.out.println(certificateList.size() + " participantes encontrados");

		System.out.println("Exportando certificados como PDF");
		exportCertificates(certificateList);
		System.out.println("Todos certificados exportados com sucesso");
	}

	private static String createFilePathFromArgs(String[] args) {
		File file = new File("./");
		return file.toPath().resolve(args[1]).toFile().getAbsolutePath();
	}

	private static void exportCertificates(List<Certificate> certificateList) {
		CertificateExporter pdfExporter = new PdfExporter(new JsonMetadataGenerator());
		pdfExporter.export(certificateList);
	}

	private static List<Certificate> createCertificateList(List<Participant> participantList, String[] args) {
		/*
		  Cuidado ao alterar o gerador de token, pois é uma alteração que influencia na validação de futuros
		  Certificados. Deve ser alterado no validador também.
		 */
		CertificateGenerator generator = new GoJavaGenerator(new TokenGenerator_SHA256());
		CertificatorGeneratorCommand generatorCommand = new CertificatorGeneratorCommand();
		generatorCommand.setParticipantList(participantList);
		String param = args.length >= 2 ? args[2] : "";
		if (param != null && !param.isEmpty()) {
			generatorCommand.setBackgroundFileName(param);
		}

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
