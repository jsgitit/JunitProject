package com.smart;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.LongConsumer;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {
    @Test
    public void allowEntryOfAppointment() {
        // Arrange/Given
        var cal = new ClinicCalendar();

        // Act/When
        cal.addAppointment("Jim", "Weaver", "avery", "09/01/2021 2:00 pm");
        var appointments = cal.getAppointments();
        var enteredAppt = appointments.get(0);

        // Assert/Then
        assertNotNull(appointments, "Appointments list cannot be null");
        assertEquals(1, appointments.size(), "There should only be one appt.");
        assertEquals("Jim", enteredAppt.getPatientFirstName(), "First name should be Jim");
        assertEquals("Weaver", enteredAppt.getPatientLastName(), "Last name should be Weaver");
        assertSame(Doctor.avery, enteredAppt.getDoctor(), "Doctor enum should point to the same enum in memory");
        assertEquals("9/1/2021 02:00 PM", enteredAppt.getAppointmentDateTime()
                .format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")), "The date/time should be 9/1/2021 at 2 pm");
    }

    @Test
    void returnTrueForHasAppointmentsIfThereAreNoAppointments() {
        // arrange
        var calendar = new ClinicCalendar(LocalDate.now());

        //act
        calendar.addAppointment("Jim", "Weaver", "avery", "09/01/2021 2:00 pm");

        //assert
        assertTrue(calendar.hasAppointment(LocalDate.of(2021, 9, 1)));
    }

    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
        // arrange
        var calendar = new ClinicCalendar(LocalDate.now());

        //act
        //Don't add any appointments

        //assert
        assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));  // assertTrue would be better practice
    }


    @Test
    void returnCurrentDaysAppointments() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        calendar.addAppointment("Jim", "Weaver", "avery",
                "09/12/2021 2:00 pm");
        calendar.addAppointment("Jim", "Weaver", "avery",
                "09/12/2021 3:00 pm");
        calendar.addAppointment("Jim", "Weaver", "avery",
                "09/01/2018 2:00 pm");
        assertEquals(2, calendar.getTodayAppointments().size());
        //assertIterableEquals(calendar.getTodayAppointments(), calendar.getAppointments());
    }
}