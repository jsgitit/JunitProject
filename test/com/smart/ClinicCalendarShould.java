package com.smart;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {
    @Test
    public void allowEntryOfAppointment() {
        // Arrange/Given
        var cal = new ClinicCalendar();

        // Act/When
        cal.addAppointment("Jim", "Weaver", "avery", "09/01/2021 2:00 pm");
        var appointments = cal.getAppointments();

        // Assert/Then
        assertNotNull(appointments, "Appointments list cannot be null");
        assertEquals(1, appointments.size());

        var enteredAppt = appointments.get(0);
        assertEquals("Jim", enteredAppt.getPatientFirstName());
        assertEquals("Weaver", enteredAppt.getPatientLastName());
        assertEquals(Doctor.avery, enteredAppt.getDoctor());
        assertEquals("9/1/2021 02:00 PM", enteredAppt.getAppointmentDateTime()
                .format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));



    }

}