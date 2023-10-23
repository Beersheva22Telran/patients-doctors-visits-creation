package telran.monitoring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.monitoring.entities.*;

public interface PatientRepo extends JpaRepository<Patient, Long> {

}
