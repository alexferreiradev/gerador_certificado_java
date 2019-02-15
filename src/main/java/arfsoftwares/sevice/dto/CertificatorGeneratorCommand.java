package arfsoftwares.sevice.dto;

import arfsoftwares.data.model.Event;
import arfsoftwares.data.model.Participant;

import java.util.List;

public class CertificatorGeneratorCommand {

	private List<Participant> participantList;
	private Event event;

	public List<Participant> getParticipantList() {
		return participantList;
	}

	public void setParticipantList(List<Participant> participantList) {
		this.participantList = participantList;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
