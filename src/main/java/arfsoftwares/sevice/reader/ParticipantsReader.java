package arfsoftwares.sevice.reader;

import arfsoftwares.data.model.Participant;
import arfsoftwares.sevice.dto.ReaderCommand;

import java.util.List;

public interface ParticipantsReader {

	List<Participant> readParticipant(ReaderCommand command);
}
