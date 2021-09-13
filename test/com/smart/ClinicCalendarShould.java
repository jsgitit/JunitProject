package com.smart;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.function.LongConsumer;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {
    private ClinicCalendar calendar; // private field for ClinicCalendar instance.

    @BeforeAll
    static void testClassSetup() {
        System.out.println("Before all...");
    }

    @BeforeEach
    void init() {
        System.out.println("Before Each...");
        calendar = new ClinicCalendar(LocalDate.of(2021, Month.SEPTEMBER, 12));
    }

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
        // see init()

        // act
        calendar.addAppointment("Jim", "Weaver", "avery", "09/01/2021 2:00 pm");

        // assert
        assertTrue(calendar.hasAppointment(LocalDate.of(2021, 9, 1)));
    }

    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
        // arrange
        // see init()

        //act
        //Don't add any appointments

        //assert
        assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));  // assertTrue would be better practice
    }

    @Test
    void returnCurrentDaysAppointments() {
        // arrange
        // see init()

        // act
        calendar.addAppointment("Jim", "Weaver", "avery",
                "09/01/2018 2:00 pm");
        calendar.addAppointment("Jim", "Weaver", "avery",
                "09/12/2021 2:00 pm");
        this.calendar.addAppointment("Jim", "Weaver", "avery",
                "09/12/2021 3:00 pm");

        // assert
        assertEquals(2, calendar.getTodayAppointments().size());
        //assertIterableEquals(calendar.getTodayAppointments(), calendar.getAppointments());
    }

    @AfterEach
    void tearDownEachTest() {
        System.out.println("After Each...");
    }

    @AfterAll
    static void tearDownAllTests() {
        System.out.println("After All ...");
    }
}