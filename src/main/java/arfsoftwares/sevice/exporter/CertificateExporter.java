package arfsoftwares.sevice.exporter;

import arfsoftwares.data.model.Certificate;

import java.util.List;

public interface CertificateExporter {

	void export(List<Certificate> certificates);

}
