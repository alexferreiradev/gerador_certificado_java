package arfsoftwares.util;

import arfsoftwares.data.model.Certificate;

public final class CertificateUtil {

	public static String createFileName(Certificate certificate) {
		if (certificate == null) {
			throw new IllegalArgumentException("Certificado não é válido, não possue nome de arquivo ou extensão");
		}

		return String.format("%s.%s", certificate.getFileName(), certificate.getFileExtension());
	}
}
