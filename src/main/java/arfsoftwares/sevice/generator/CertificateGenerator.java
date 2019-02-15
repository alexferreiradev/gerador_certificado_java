package arfsoftwares.sevice.generator;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.sevice.dto.CertificatorGeneratorCommand;

import java.util.List;

public interface CertificateGenerator {

	List<Certificate> generateCertificates(CertificatorGeneratorCommand command);

}
