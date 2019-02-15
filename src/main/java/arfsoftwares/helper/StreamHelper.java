package arfsoftwares.helper;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public final class StreamHelper {

	public static String parseStream(InputStream stream) throws IOException {
		StringBuilder content = new StringBuilder();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);
		byte[] readBytes = new byte[100];
		while (bufferedInputStream.available() > 0) {
			int read = bufferedInputStream.read(readBytes, 0, readBytes.length);
			if (read < readBytes.length) {
				readBytes = Arrays.copyOf(readBytes, read);
			}
			content.append(new String(readBytes, StandardCharsets.UTF_8));
		}

		return content.toString();
	}

	public static void closeSafe(InputStream inputStream) {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
