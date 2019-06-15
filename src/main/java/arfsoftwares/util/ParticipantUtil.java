package arfsoftwares.util;

import arfsoftwares.data.model.Participant;

public final class ParticipantUtil {

	public static String participantCompleteName(Participant participant) {
		return participant.getName() + " " + participant.getLastName();
	}

	public static String createIdentifier(Participant participant) {
		String cpf = participant.getCpf();
		String rg = participant.getRg();
		return cpf != null ? cpf : rg;
	}
}
