package com.smart;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ClinicCalendar {

   private List<PatientAppointment> appointments;
   private LocalDate specificDay;

   public ClinicCalendar() {
      this.appointments = new ArrayList<>();
   }

   public ClinicCalendar(LocalDate specificDay) {
      this.specificDay = specificDay;
      this.appointments = new ArrayList<>();
   }

   public void addAppointment(String patientFirstName, String patientLastName, String doctorKey,
                              String dateTime) {
      Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
      LocalDateTime localDateTime = DateTimeConverter.convertStringToDateTime(dateTime, specificDay);
      PatientAppointment appointment = new PatientAppointment(patientFirstName, patientLastName,
              localDateTime, doc);
      appointments.add(appointment);
   }



   public List<PatientAppointment> getAppointments() {
      return this.appointments;
   }
   public List<PatientAppointment> getTodayAppointments() {
      return appointments.stream()
              .filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(specificDay))
              .collect(Collectors.toList());
   }

   /**
    *
    * @param date - date to check
    * @return true if there are any appointments scheduled for the date
    */
   public boolean hasAppointment(LocalDate date) {

      return appointments.stream()
              .anyMatch(appt -> appt.getAppointmentDateTime().toLocalDate().equals(date));
   }
}
