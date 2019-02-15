package arfsoftwares.sevice.exporter;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.helper.StreamHelper;
import arfsoftwares.util.CertificateUtiltest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfExporter implements CertificateExporter {

	private static final String CERT_DIR_NAME = "certificados";

	@Override
	public void export(Certificate certificate) {
		File file = new File("./" + CERT_DIR_NAME + "/" + CertificateUtiltest.createFileName(certificate));
		FileOutputStream fileOutputStream = null;
		try {
			boolean mkdirs = createCertDir();
			boolean newFile = file.createNewFile();
			if (!newFile || !mkdirs) {
				throw new IOException("Não foi possível criar o arquivo: " + file.toString());
			}

			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(certificate.getFileContent());
			fileOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			StreamHelper.closeSafeOutput(fileOutputStream);
		}
	}

	private boolean createCertDir() {
		File file = new File("./" + CERT_DIR_NAME);
		return file.exists() || file.mkdirs();
	}
}
