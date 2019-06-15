package arfsoftwares.sevice.exporter;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.helper.StreamHelper;
import arfsoftwares.sevice.exporter.metadata.MetadataGenerator;
import arfsoftwares.util.CertificateUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfExporter implements CertificateExporter {

	private static final String CERT_DIR_NAME = "certificados";
	private MetadataGenerator metadataGenerator;

	public PdfExporter(MetadataGenerator metadataGenerator) {
		this.metadataGenerator = metadataGenerator;
	}

	@Override
	public void export(List<Certificate> certificates) {
		for (Certificate certificate : certificates) {
			System.out.println("Exportando certificado: " + certificate.getFileName());
			exportPDF(certificate);
			System.out.println("Certificado exportado");
		}

		exportMetadata(certificates);

		System.out.println("Meta dados gerados");
	}

	private void exportMetadata(List<Certificate> certificates) {
		Certificate certificateExample = certificates.get(0);
		String jsonFileName = metadataGenerator.getFileName(certificateExample);
		System.out.println("Meta dados de exportação serão gerados para arquivo: " + jsonFileName);

		File file = new File("./" + CERT_DIR_NAME + "/" + jsonFileName);
		FileOutputStream fileOutputStream = null;
		try {
			boolean mkdirs = createCertDir();
			boolean newFile = file.createNewFile();
			if (!newFile || !mkdirs) {
				if (!file.exists()) {
					throw new IOException("Não foi possível criar o arquivo: " + file.toString());
				} else {
					System.out.println("Arquivo já existe, será ignorado a geração");
				}
			}

			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(metadataGenerator.createMetadataBytes(certificates));
			fileOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			StreamHelper.closeSafeOutput(fileOutputStream);
		}
	}

	private void exportPDF(Certificate certificate) {
		File file = new File("./" + CERT_DIR_NAME + "/" + CertificateUtil.createFileName(certificate));
		FileOutputStream fileOutputStream = null;
		try {
			boolean mkdirs = createCertDir();
			boolean newFile = file.createNewFile();
			if (!newFile || !mkdirs) {
				if (!file.exists()) {
					throw new IOException("Não foi possível criar o arquivo: " + file.toString());
				} else {
					System.out.println("Arquivo já existe, será ignorado a geração");
				}
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
