package arfsoftwares.sevice.generator;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.sevice.dto.CertificatorGeneratorCommand;

public interface CertificateGenerator {

	Certificate generateCertificate(CertificatorGeneratorCommand command);

}
