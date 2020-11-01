package com.example.demo.models;

import java.time.LocalDateTime;

public class Appointment {
	private static long idCounter = 1;
	private long appointmentID;
	private String patientFirstName;
	private String patientLastName;
	private LocalDateTime time;
	private AppointmentType type;
	
	public Appointment(String patientFirstName, String patientLastName, LocalDateTime time, AppointmentType type) {
		this.appointmentID = idCounter++;
		this.patientFirstName = patientFirstName;
		this.patientLastName = patientLastName;
		this.time = time;
		this.type = type;
	}

	public static long getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(long idCounter) {
		Appointment.idCounter = idCounter;
	}

	public long getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(long appointmentID) {
		this.appointmentID = appointmentID;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public AppointmentType getType() {
		return type;
	}

	public void setType(AppointmentType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (appointmentID ^ (appointmentID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (appointmentID != other.appointmentID)
			return false;
		return true;
	}

	enum AppointmentType {
		NEW, FOLLOWUP
	}
}
