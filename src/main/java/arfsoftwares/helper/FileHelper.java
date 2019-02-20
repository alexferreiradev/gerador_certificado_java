package arfsoftwares.helper;

import java.io.File;
import java.io.FileInputStream;

public final class FileHelper {

	public static boolean isValidFile(String fileName) {
		File file = new File(fileName);

		try (FileInputStream fileInputStream = new FileInputStream(file)) {
			return fileInputStream.read() > 0;
		} catch (java.io.IOException e) {
			return false;
		}
	}
}
