package arfsoftwares.helper;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

	public static void closeSafeInput(InputStream stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void closeSafeOutput(OutputStream stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
