package arfsoftwares.sevice.reader;

import arfsoftwares.data.model.Event;
import arfsoftwares.data.model.Participant;
import arfsoftwares.helper.StreamHelper;
import arfsoftwares.sevice.dto.ReaderCommand;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CsvParticipantReader implements ParticipantsReader {

	private String fileName;

	public CsvParticipantReader(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public List<Participant> readParticipant(ReaderCommand command) {
		InputStream inputStream = getClass().getResourceAsStream(fileName);
		try {
			String text = StreamHelper.parseStream(inputStream);

			return buildParticipantList(text);
		} catch (IndexOutOfBoundsException | IOException e) {
			e.printStackTrace();
		} finally {
			StreamHelper.closeSafeInput(inputStream);
		}

		return null;
	}

	private List<Participant> buildParticipantList(String text) {
		List<Participant> participantList = new ArrayList<>();
		StringTokenizer stringTokenizer = new StringTokenizer(text, "\n");
		while (stringTokenizer.hasMoreTokens()) {
			String line = stringTokenizer.nextToken();
			Participant participant = buildParticipant(line);

			participantList.add(participant);
		}

		return participantList;
	}

	private Participant buildParticipant(String line) {
		Participant participant = new Participant();

		String[] colunms = line.split("[,;\t]");
		participant.setName(colunms[0]);
		participant.setLastName(colunms[1]);
		participant.setHour(colunms[2]);
		participant.setEvent(buildEvent(colunms));

		return participant;
	}

	private Event buildEvent(String[] colunm) {
		Event event = new Event();
		event.setName(colunm[3]);

		return event;
	}
}
