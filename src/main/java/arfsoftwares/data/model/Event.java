package arfsoftwares.data.model;

import java.util.Date;
import java.util.Objects;

public class Event extends BaseModel {

	private String name;
	private String description;
	private Date dateStarted;
	private Date dateEnded;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public Date getDateEnded() {
		return dateEnded;
	}

	public void setDateEnded(Date dateEnded) {
		this.dateEnded = dateEnded;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Event event = (Event) o;
		return Objects.equals(name, event.name) &&
				Objects.equals(description, event.description) &&
				Objects.equals(dateStarted, event.dateStarted) &&
				Objects.equals(dateEnded, event.dateEnded);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description, dateStarted, dateEnded);
	}

	@Override
	public String toString() {
		return "Event{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", dateStarted=" + dateStarted +
				", dateEnded=" + dateEnded +
				'}';
	}
}
