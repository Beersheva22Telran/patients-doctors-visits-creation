package telran.monitoring.entities;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
@Entity
@Table(name="visits", indexes = {@Index(columnList = "patient_id")})
@Getter
public class Visit {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	long id;
	@ManyToOne
	@JoinColumn(name="doctor_id")
	Doctor doctor;
	@ManyToOne
	@JoinColumn(name="patient_id")
	Patient patient;
	@Temporal(TemporalType.DATE)
	LocalDate date;
	public Visit(Doctor doctor, Patient patient, LocalDate date) {
		this.doctor = doctor;
		this.patient = patient;
		this.date = date;
	}
	public Visit() {
	}
}
