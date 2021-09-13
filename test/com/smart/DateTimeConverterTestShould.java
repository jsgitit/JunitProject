package com.smart;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeConverterTestShould {
    @Test
    void convertTodayStringCorrectly() {
        // arrange

        // act
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm",
                LocalDate.of(2021, 9, 12));

        // assert
        assertEquals(result, LocalDateTime.of(2021, 9, 12, 13, 0));
    }

    @Test
    void convertCorrectPatternToDateTime() {
        // arrange

        // act
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("9/12/2021 1:00 pm",
                LocalDate.of(2021, 9, 12));

        // assert
        assertEquals(result, LocalDateTime.of(2021, 9, 12, 13, 0));
    }

    @Test
    void throwExceptionIfIncorrectPatternProvided() {
        // arrange
        //
        // act
        Throwable err = assertThrows(RuntimeException.class, () ->
            DateTimeConverter.convertStringToDateTime("9/12/2021 100 pm",
                    LocalDate.of(2021, 9, 12)));

        // assert
        // shows the actual error message vs. simple "RuntimeException"
        assertEquals("Unable to create date time from: [9/12/2021 100 pm], " +
                "please enter with format [M/d/yyyy h:mm a], " +
                "Text '9/12/2021 100 PM' could not be parsed at index 13", err.getMessage());
    }
}