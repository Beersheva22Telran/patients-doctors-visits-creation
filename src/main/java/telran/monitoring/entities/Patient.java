package telran.monitoring.entities;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor

public class Patient extends Person {
	public Patient(long id, String email, String name) {
		super(id, email, name);
	}

}
