package com.example.demo.service;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Appointment;
import com.example.demo.models.Doctor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class DoctorController {
	
	private final static List<Doctor> allDoctors = new ArrayList<>();
	private final static List<Appointment> allAppointments = new ArrayList<>();
	
	static {
		Doctor doc1 = new Doctor("John", "Doe");
		Doctor doc2 = new Doctor("Jane", "Doe");
		Doctor doc3 = new Doctor("Tom", "Jerry");
		allDoctors.add(doc1);
		allDoctors.add(doc2);
		allDoctors.add(doc3);
	}
	
	@GetMapping("/doctor")
	public List<Doctor> getAllDoctors()  {
		return allDoctors;
	}
	
	@GetMapping("/doctor/{doctorId}")
	public Doctor getDoctor(
			@PathVariable(name="doctorId") long doctorId)  {
		Optional<Doctor> finder =  allDoctors.stream().filter(d -> d.getId() == doctorId).findFirst();
		return finder.orElse(null);
	}
	
	@PostMapping("/doctor/{doctorId}/appointment")
	public Appointment createAppointment(
			@PathVariable(name="doctorId") long doctorId,
			@RequestBody Appointment appointment) {
		if(!isDateTimeValid(appointment.getTime())) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Invalid DateTime Passed, Cannot create Appointment");
		}
		Doctor doc = this.getDoctor(doctorId);
		doc.getAppointments().add(appointment);
		allAppointments.add(appointment);
		return appointment;
	}
	
	@DeleteMapping("/doctor/{doctorId}/appointment/{appointmentId}")
	public void deleteAppointment(
			@PathVariable(name="doctorId") long doctorId,
			@PathVariable(name="appointmentId") long appointmentId) {
		Doctor doc = this.getDoctor(doctorId);
		for(Appointment app : doc.getAppointments()) {
			if(app.getAppointmentID() == appointmentId) {
				doc.getAppointments().remove(app);
				this.deleteAppointment(app);
			}
		}
	}
	
	@GetMapping("/appointment")
	public List<Appointment> getAllAppointments() {
		return allAppointments;
	}
	
	public boolean isDateTimeValid(LocalDateTime time) {
		return time.getMinute() % 15 == 0;
	}
	
	public void deleteAppointment(Appointment app) {
		allAppointments.remove(app);
	}
}
