package arfsoftwares.itext;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ItextAPITest {

	private File outputTestFile;

	@Before
	public void setUp() {
		String pathname = "teste_output.pdf";
		outputTestFile = new File(pathname);
	}

	@Test
	public void createTextEmColuna_comCollunmText() throws IOException, DocumentException {
		OutputStream os = new FileOutputStream(outputTestFile);

		PdfReader pdfReader = new PdfReader("src/test/resources/itext/collumn_text.pdf");

		PdfStamper stamper = new PdfStamper(pdfReader, os);
		PdfContentByte cb = stamper.getOverContent(1);
		ColumnText ct = new ColumnText(cb);
		ct.setSimpleColumn(120f, 48f, 200f, 600f);
		Font f = new Font();
		Paragraph pz = new Paragraph(new Phrase(20, "Hello World!", f));
		ct.addElement(pz);
		ct.go();
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", BaseFont.EMBEDDED);
		f = new Font(bf, 13);
		ct = new ColumnText(cb);
		float right = cb.getPdfDocument().getPageSize().getRight();
		float top = cb.getPdfDocument().getPageSize().getTop();

		float llx = right - 445f;
		float lly = top - 34f;
		pz = new Paragraph("Gojava", f);
//		ct.setSimpleColumn(llx, lly, llx + pz.getContent().length()*10, lly + pz.getFont().getSize());
		/*
		 * Uma boa forma de calcular é usar o rectangle para mostrar os pontos x e y e ai acertar o x e y do lower coner e o up corner
		 */
		ct.setSimpleColumn(150f, 808f, 300f, 0f);
		ct.addElement(pz);
		ct.go();
		cb.rectangle(145f, 808f, 100, -25);
		cb.stroke();
		ct.go();

		stamper.flush();
		stamper.close();
		pdfReader.close();

		File file = new File(outputTestFile.getAbsolutePath());
		Assert.assertTrue(file.exists());
	}

	@Test
	public void createDocument() throws FileNotFoundException, DocumentException {
		Document document = new Document(PageSize.A4.rotate());
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputTestFile));
		document.open();

		document.add(Chunk.NEWLINE);
		document.close();
	}
}
