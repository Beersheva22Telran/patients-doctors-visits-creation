package telran.monitoring;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import telran.monitoring.entities.*;
import telran.monitoring.repo.*;
@SpringBootApplication
@RequiredArgsConstructor
public class DoctorsPatientsDbCreaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorsPatientsDbCreaterApplication.class, args);
	}
	
	final DoctorRepo doctorRepo;
	final PatientRepo patientRepo;
	final VisitRepo visitRepo;
	@Value("${app.db.populator.patient.amount}")
	int nPatients;
	@Value("${app.db.populator.doctor.amount}")
	int nDoctors;
	@Value("${app.db.populator.visit.amount}")
	int nVisits;
	long[] patientIds;
	long[] doctorIds;
	@PostConstruct
	void dbCreation() {
		createPatientIds();
		createDoctorIds();
		createPatients();
		createDoctors();
		createVisits();
	}
	private void createVisits() {
		IntStream.rangeClosed(1, nVisits).forEach(i -> addVisit());
		
	}
	private void addVisit() {
		Patient patient = patientRepo.findById(getId(patientIds)).orElseThrow();
		Doctor doctor = doctorRepo.findById(getId(doctorIds)).orElseThrow();
		Visit visit = new Visit(doctor, patient, LocalDate.of(2024, 10, getRandomNumber(1, 32)));
		visitRepo.save(visit);
		
	}
	private int getRandomNumber(int min, int max) {
		
		return ThreadLocalRandom.current().nextInt(min, max);
	}
	private Long getId(long[] ids) {
		int index = getRandomNumber(0, ids.length);
		return ids[index];
	}
	private void createDoctors() {
		Arrays.stream(doctorIds).forEach(id -> doctorRepo.save(new Doctor(id, String.format("doctor%d@gmail.com", id),
				"doctor" + id)));
		
	}
	private void createPatients() {
		Arrays.stream(patientIds).forEach(id -> patientRepo.save(new Patient(id, String.format("patient%d@gmail.com", id),
				"patient" + id)));
		
	}
	private void createDoctorIds() {
		doctorIds = ThreadLocalRandom.current().longs(100000, 1000000).distinct().limit(nDoctors).toArray();
		
	}
	private void createPatientIds() {
		patientIds = LongStream.rangeClosed(1, nPatients).toArray();
		
	}
}
