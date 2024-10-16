package utilities;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void testdaysinFeb_Nonleap() {
        Date date = new Date(2011, 2, 29);
        assertFalse(date.isValidDate());
        date = new Date(2011, 2, 28);
        assertTrue(date.isValidDate());
    }
    @Test
    public void testBeforeToday() {
        Date date = new Date(2018, 2, 24);
        assertTrue(date.isBeforeToday());
    }
    @Test
    public void testWithinSixMonths() {
        Date date = new Date(2025, 2, 20);
        assertTrue(date.isWithinSixMonths());
        date = new Date(2026, 2, 21);
        assertFalse(date.isWithinSixMonths());
    }
    @Test
    public void testIsWeekend() {
        Date date = new Date(2024, 10, 19);
        assertFalse(date.isWeekday());
    }
}