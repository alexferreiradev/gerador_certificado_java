package arfsoftwares.sevice.dto;

import arfsoftwares.data.model.Participant;

import java.util.List;

public class CertificatorGeneratorCommand {

	private List<Participant> participantList;
	private String backgroundFileName;

	public List<Participant> getParticipantList() {
		return participantList;
	}

	public void setParticipantList(List<Participant> participantList) {
		this.participantList = participantList;
	}

	public String getBackgroundFileName() {
		return backgroundFileName;
	}

	public void setBackgroundFileName(String backgroundFileName) {
		this.backgroundFileName = backgroundFileName;
	}
}
