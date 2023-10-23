package telran.monitoring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.monitoring.entities.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {

}
