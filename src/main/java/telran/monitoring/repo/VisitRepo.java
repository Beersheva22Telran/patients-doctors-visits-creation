package telran.monitoring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.monitoring.entities.*;

public interface VisitRepo extends JpaRepository<Visit, Long> {
	@Query(value="select doctor_id as id from visits where patient_id=:patientId order by date desc limit 1", nativeQuery = true)
DoctorId findDoctorIdLastVisit(long patientId);
}
