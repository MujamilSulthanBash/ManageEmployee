package com.i2i.ems.utility;

import java.time.LocalDate;
import java.time.Period;

public class YearIntervalCalculator {

    public static String calculateAge(LocalDate date) {
        return Period.between(date, LocalDate.now()).getYears()
                + "y " + Period.between(date, LocalDate.now()).getMonths() + "m";
    }
}
