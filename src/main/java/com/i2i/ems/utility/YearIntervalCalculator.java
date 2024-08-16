package com.i2i.ems.utility;

import java.time.LocalDate;
import java.time.Period;

/**
 * This class is responsible for calculate the interval between
 * current year and given year.
 */
public class YearIntervalCalculator {

    /**
     * This method is responsible for calculate the interval between
     * current year and given year.
     *
     * @param date - accept the date
     * @return year and month in the form of string.
     */
    public static String calculateAge(LocalDate date) {
        return Period.between(date, LocalDate.now()).getYears()
                + "y " + Period.between(date, LocalDate.now()).getMonths() + "m";
    }

}
