package arfsoftwares.sevice.generator;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.data.model.Event;
import arfsoftwares.data.model.Participant;
import arfsoftwares.helper.StreamHelper;
import arfsoftwares.sevice.dto.CertificatorGeneratorCommand;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoJavaGenerator implements CertificateGenerator {
	@Override
	public List<Certificate> generateCertificates(CertificatorGeneratorCommand command) {
		List<Certificate> certificateList = new ArrayList<>();

		Event event = command.getEvent();
		List<Participant> participantList = command.getParticipantList();

		for (Participant participant : participantList) {
			Certificate certificate = new Certificate();

			certificate.setFileName(createCertName(participant));
			certificate.setFileExtension("pdf");
			certificate.setFileContent(buildPdfFileContent(participant));

			certificateList.add(certificate);
		}

		return certificateList;
	}

	private byte[] buildPdfFileContent(Participant participant) {
		FileOutputStream fileOutputStream = null;
		Document document = null;

		try {
			File certificateTemp = File.createTempFile("certificateTemp", ".pdf");
			fileOutputStream = new FileOutputStream(certificateTemp);
			document = new Document();
			PdfWriter.getInstance(document, fileOutputStream);
			document.open();
			document.add(new Phrase("Certificado de Teste"));
			document.add(new Chunk(Chunk.NEWLINE));
			document.add(new Phrase("Nome do participante: "));
			document.add(new Phrase(participant.getName() + " " + participant.getLastName()));

			document.close();
			fileOutputStream.flush();
			certificateTemp.deleteOnExit();

			return FileUtils.readFileToByteArray(certificateTemp);
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		} finally {
			if (document != null) {
				document.close();
			}
			StreamHelper.closeSafeOutput(fileOutputStream);
		}

		return null;
	}

	private String createCertName(Participant participant) {
		return "certificado_" + participant.getName();
	}
}
