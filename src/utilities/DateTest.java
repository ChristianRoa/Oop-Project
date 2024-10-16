package utilities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class tests the functionality of the Date class, including validation of dates,
 * checks for past dates, checks for dates within six months, and whether a date falls on a weekend.
 */
public class DateTest {

    /**
     * Tests the validity of dates in February for non-leap years.
     * Ensures that February 29 is invalid in a non-leap year and that February 28 is valid.
     */
    @Test
    public void testdaysinFeb_Nonleap() {
        Date date = new Date(2011, 2, 29);
        assertFalse(date.isValidDate());
        date = new Date(2011, 2, 28);
        assertTrue(date.isValidDate());
    }

    /**
     * Tests if a given date is before the current date (today).
     * Ensures that a past date (February 24, 2018) is recognized as before today.
     */
    @Test
    public void testBeforeToday() {
        Date date = new Date(2018, 2, 24);
        assertTrue(date.isBeforeToday());
    }

    /**
     * Tests whether a given date is within six months from the current date.
     * Ensures that February 20, 2025 is within six months and February 21, 2026 is not.
     */
    @Test
    public void testWithinSixMonths() {
        Date date = new Date(2025, 2, 20);
        assertTrue(date.isWithinSixMonths());
        date = new Date(2026, 2, 21);
        assertFalse(date.isWithinSixMonths());
    }

    /**
     * Tests if a given date falls on a weekday or weekend.
     * Ensures that October 19, 2024 is recognized as a weekend day (not a weekday).
     */
    @Test
    public void testIsWeekend() {
        Date date = new Date(2024, 10, 19);
        assertFalse(date.isWeekday());
    }
}
