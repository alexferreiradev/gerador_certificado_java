package arfsoftwares.data.model;

import java.util.Arrays;
import java.util.Objects;

public class Certificate extends BaseModel {

	private String fileName;
	private String fileExtension;
	private byte[] fileContent;


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Certificate that = (Certificate) o;
		return Objects.equals(fileName, that.fileName) &&
				Objects.equals(fileExtension, that.fileExtension) &&
				Arrays.equals(fileContent, that.fileContent);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(fileName, fileExtension);
		result = 31 * result + Arrays.hashCode(fileContent);
		return result;
	}

	@Override
	public String toString() {
		return "Certificate{" +
				"fileName='" + fileName + '\'' +
				", fileExtension='" + fileExtension + '\'' +
				", fileContent=" + Arrays.toString(fileContent) +
				'}';
	}
}
