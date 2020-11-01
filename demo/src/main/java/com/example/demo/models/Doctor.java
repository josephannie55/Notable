package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
	private static long idCounter = 1;
	private long id;
	private String firstName;
	private String lastName;
	private List<Appointment> appointments;
	
	public Doctor(String firstName, String lastName) {
		this.appointments = new ArrayList<>();
		this.id = idCounter++;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
}
