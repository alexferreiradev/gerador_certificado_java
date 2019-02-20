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
		Assert.assertEquals("09876543212", participants.get(0).getCpf());
		Assert.assertNull(participants.get(0).getRg());
		Assert.assertEquals("4", participants.get(0).getHour());
		Assert.assertEquals("BrasilTurJavaJug", participants.get(0).getEvent().getName());
		Assert.assertEquals("GoJava", participants.get(0).getEvent().getExecutor());
		Assert.assertEquals("GoJava", participants.get(0).getEvent().getExecutor());
		Assert.assertEquals("desenvolvimento ágil, carreira de TI e micro-profile", participants.get(0).getEvent().getTalkerTopics());
		Assert.assertEquals(1550631600000L, participants.get(0).getEvent().getDateStarted().getTime());
		Assert.assertEquals(1550631600000L, participants.get(0).getEvent().getDateEnded().getTime());
	}
}