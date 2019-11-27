package arfsoftwares.util;

import arfsoftwares.data.model.Participant;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParticipantUtilTest {

	@Test
	public void participantCompleteName() {
		Participant participant = new Participant();
		participant.setName("Nome");
		participant.setLastName("Grande Para Caramba De Last");
		Assert.assertEquals("Nome G. P. C. D. Last", ParticipantUtil.participantCompleteNameSummarized(participant) );
	}
}