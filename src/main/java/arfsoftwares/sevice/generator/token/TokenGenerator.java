package arfsoftwares.sevice.generator.token;

import arfsoftwares.data.model.Certificate;

public interface TokenGenerator {

	String generateToken(Certificate certificate);

}
