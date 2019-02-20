package arfsoftwares.sevice.generator;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.data.model.Event;
import arfsoftwares.data.model.Participant;
import arfsoftwares.helper.DateHelper;
import arfsoftwares.helper.StreamHelper;
import arfsoftwares.sevice.dto.CertificatorGeneratorCommand;
import arfsoftwares.util.ParticipantUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			File certificateTemp = File.createTempFile("certificateTemp_", ".pdf");
			fileOutputStream = new FileOutputStream(certificateTemp);

			document = new Document(PageSize.A4.rotate());
			PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
			document.open();

			ColumnText ct = new ColumnText(writer.getDirectContent());
			ct.setSimpleColumn(75, 200, 775, 425);

			Font paragraphFont = createFontToCertText();
			Paragraph paragraph = createParagraphToCertText(participant, paragraphFont);
			ct.addElement(paragraph);
			ct.go();

			addBackgroundImage(writer.getDirectContentUnder());

			document.close();
			certificateTemp.deleteOnExit();

			return FileUtils.readFileToByteArray(certificateTemp);
		} catch (IOException | DocumentException | URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (document != null) {
				document.close();
			}
			StreamHelper.closeSafeOutput(fileOutputStream);
		}

		return null;
	}

	private void addBackgroundImage(PdfContentByte background) throws IOException, URISyntaxException, DocumentException {
		Image image = Image.getInstance(getClass().getResource("/img/gojava_certificado-1-0-0.png").toURI().toURL());
		image.setAbsolutePosition(0, 0);
		image.scaleAbsolute(PageSize.A4.rotate());
		background.saveState();
		PdfGState pdfGState = new PdfGState();
		pdfGState.setFillOpacity(0.8f);
		background.setGState(pdfGState);
		background.addImage(image);
		background.restoreState();
	}

	private Paragraph createParagraphToCertText(Participant participant, Font paragraphFont) {
		Paragraph paragraph = new Paragraph();
		paragraph.setLeading(0f, 1.1f);
		paragraph.setAlignment(Paragraph.ALIGN_JUSTIFIED_ALL);
		paragraph.setFont(paragraphFont);
		paragraph.add(generateCertificateText(participant));
		return paragraph;
	}

	private Font createFontToCertText() {
		Font paragraphFont = new Font();
		paragraphFont.setSize(26);
		paragraphFont.setColor(BaseColor.WHITE);
		paragraphFont.setFamily(BaseFont.TIMES_ROMAN);
		paragraphFont.setStyle(Font.NORMAL);
		return paragraphFont;
	}

	private String generateCertificateText(Participant participant) {
		String finalText = "Certificamos que NOME_PARTICIPANTE com CPF CPF_PARTICIPANTE participou do evento NOME_EVENTO durante HORAS_EVENTO horas no dia DATA_EVENTO promovido pelo Gojava - Grupo de usuários Java de Goiás. O evento foi sobre ASSUNTO_EVENTO dentro da área de TI.";
		finalText = finalText.replaceAll("NOME_PARTICIPANTE", ParticipantUtil.participantCompleteName(participant).toUpperCase());
		finalText = finalText.replaceAll("CPF_PARTICIPANTE", "");
		finalText = finalText.replaceAll("NOME_EVENTO", participant.getEvent().getName());
		finalText = finalText.replaceAll("HORAS_EVENTO", participant.getHour());
		finalText = finalText.replaceAll("DATA_EVENTO", DateHelper.format(new Date(), DateFormat.SHORT));
		finalText = finalText.replaceAll("ASSUNTO_EVENTO", "");

		return finalText;
	}

	private String createCertName(Participant participant) {
		return "certificado_" + participant.getName();
	}
}
