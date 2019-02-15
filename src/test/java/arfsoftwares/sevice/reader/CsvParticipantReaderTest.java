package arfsoftwares.sevice.reader;

import arfsoftwares.data.model.Participant;
import arfsoftwares.sevice.dto.ReaderCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CsvParticipantReaderTest {

	private ParticipantsReader reader;

	@Before
	public void setUp() {
		reader = new CsvParticipantReader("test.csv");
	}

	@Test
	public void leiaArquivoCsv_crieListaParticipantes() {
		reader = new CsvParticipantReader("/reader/test_list1.csv");

		ReaderCommand command = new ReaderCommand();
		List<Participant> participants = reader.readParticipant(command);

		Assert.assertEquals(participants.size(), 8);
		Assert.assertEquals(participants.get(0).getName(), "Alex");
		Assert.assertEquals(participants.get(0).getLastName(), "Rabelo Ferreira");
		Assert.assertEquals(participants.get(0).getHour(), "4");
		Assert.assertEquals(participants.get(0).getEvent().getName(), "GoJava");
	}
}