package arfsoftwares.sevice.exporter.metadata;

import arfsoftwares.data.model.Certificate;

import java.util.List;

public interface MetadataGenerator {

	byte[] createMetadataBytes(List<Certificate> certificateList);

	String getFileName(Certificate certificate);
}
