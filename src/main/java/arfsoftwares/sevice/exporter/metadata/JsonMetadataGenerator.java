package arfsoftwares.sevice.exporter.metadata;

import arfsoftwares.data.model.Certificate;
import arfsoftwares.data.model.Participant;
import arfsoftwares.data.model.metadata.CertificateMetadata;
import arfsoftwares.data.model.metadata.JsonMetadata;
import arfsoftwares.data.model.metadata.ParticipantMetadata;
import arfsoftwares.helper.DateHelper;
import arfsoftwares.helper.FileHelper;
import arfsoftwares.util.ParticipantUtil;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonMetadataGenerator implements MetadataGenerator {

	@Override
	public byte[] createMetadataBytes(List<Certificate> certificateList) {
		JsonMetadata jsonMetadata = new JsonMetadata();
		// TODO - adicionar em validador primeiro
		jsonMetadata.setIntegrityKey("");
		List<CertificateMetadata> certificatesMetadata = new ArrayList<>();
		certificateList.forEach(certificate -> {
			CertificateMetadata certificateData = new CertificateMetadata();
			certificateData.setToken(certificate.getUuid());
			ParticipantMetadata participantData = new ParticipantMetadata();
			Participant participant = certificate.getParticipant();
			participantData.setIdentifier(ParticipantUtil.createIdentifier(participant));
			participantData.setName(ParticipantUtil.participantCompleteName(participant));
			certificateData.setParticipant(participantData);

			certificatesMetadata.add(certificateData);
		});
		jsonMetadata.setCertificates(certificatesMetadata);

		String json = new Gson().toJson(jsonMetadata);
		return json.getBytes();
	}

	/**
	 * Cria o nome de arquivo utilizado pelo validador. Ao alterar, altere no validador.
	 *
	 * @param certificate model
	 * @return nome de arquivo json valido
	 */
	@Override
	public String getFileName(Certificate certificate) {
		Date createdDate = new Date();
		String createdDateFormat = DateHelper.format(createdDate, DateFormat.SHORT);
		String eventName = certificate.getParticipant().getEvent().getName();
		String eventExecutor = certificate.getParticipant().getEvent().getExecutor();

		eventName = FileHelper.formatToValidFileName(eventName);
		createdDateFormat = FileHelper.formatToValidFileName(createdDateFormat);

		return String.format("%s_%s_%s.json", eventName, eventExecutor, createdDateFormat);
	}
}
