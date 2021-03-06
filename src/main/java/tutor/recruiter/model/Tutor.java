package tutor.recruiter.model;

import javax.persistence.*;

@Entity
@Table(name = "TUTOR")
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@JoinColumn(name = "SUBJECT", referencedColumnName = "ID")
	@ManyToOne
	private Subject subject;

	@Column(name = "rate", nullable = false)
	private double rate;

	@JoinColumn(name = "ZIP", referencedColumnName = "CODE")
	@ManyToOne
	private Zipcode zipcode;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Zipcode getZipcode() {
		return zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "Tutor [id=" + id + ", name=" + name + ", subject=" + subject.getName();
	}

}
