package arfsoftwares.util;

import arfsoftwares.data.model.Participant;

public final class ParticipantUtil {

	public static String participantCompleteName(Participant participant) {
		return participant.getName() + " " + participant.getLastName();
	}
}
