package ar.com.codoacodo.entity;

import java.time.LocalDate;
public class Speaker {
	private Long id;
	private String name;
	private String lastName;
	private String email;
	private String topic;
	private LocalDate creationDate;
	public Speaker(String name, String lastName, String email, String topic, LocalDate creationDate) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.topic = topic;
		this.creationDate = creationDate;
		}
	public Speaker(Long id, String name, String lastName, String email, String topic, LocalDate creationDate) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.topic = topic;
		this.creationDate = creationDate;
		}
	public void setName(String name) {
		if(name != null) {
			this.name = name;
		} else {
			this.name ="N/D";
		}
	}
	public String getName() {
		return this.name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public LocalDate getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Orador [id=" + id + ", nombre=" + name + ", apellido=" + lastName + ", mail=" + email + ", tema="
				+ topic + ", fechaAlta=" + creationDate + "]";
	}
}
