package arfsoftwares.util;

import arfsoftwares.data.model.Certificate;

public final class CertificateUtiltest {

	public static String createFileName(Certificate certificate) {
		if (certificate == null) {
			throw new IllegalArgumentException("Certificado não é válido, não possue nome de arquivo ou extensão");
		}

		return certificate.getFileName() + "." + certificate.getFileExtension();
	}
}
