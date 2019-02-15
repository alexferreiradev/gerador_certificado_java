package arfsoftwares.data.model;

import java.util.Objects;

public class Participant extends BaseModel {

	private String name;
	private String lastName;
	private String hour;
	private Event event;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Participant that = (Participant) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(lastName, that.lastName) &&
				Objects.equals(hour, that.hour) &&
				Objects.equals(event, that.event);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, lastName, hour, event);
	}

	@Override
	public String toString() {
		return "Participant{" +
				"name='" + name + '\'' +
				", lastName='" + lastName + '\'' +
				", hour='" + hour + '\'' +
				", event=" + event +
				'}';
	}
}
