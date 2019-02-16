package arfsoftwares.sevice.reader;

import arfsoftwares.data.model.Participant;
import arfsoftwares.sevice.dto.ReaderCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

public class CsvParticipantReaderTest {

	private ParticipantsReader reader;

	@Before
	public void setUp() {
		reader = new CsvParticipantReader("test.csv");
	}

	@Test
	public void leiaArquivoCsv_crieListaParticipantes() throws URISyntaxException {
		String fileNameRes = "/reader/test_list1.csv";
		File file = new File(getClass().getResource(fileNameRes).toURI());
		reader = new CsvParticipantReader(file.getAbsolutePath());

		ReaderCommand command = new ReaderCommand();
		List<Participant> participants = reader.readParticipant(command);

		Assert.assertEquals(8, participants.size());
		Assert.assertEquals("Alex", participants.get(0).getName());
		Assert.assertEquals("Rabelo Ferreira", participants.get(0).getLastName());
		Assert.assertEquals("4", participants.get(0).getHour());
		Assert.assertEquals("GoJava", participants.get(0).getEvent().getName());
	}
}