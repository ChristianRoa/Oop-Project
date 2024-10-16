package mypackage;

import org.junit.Test;
import utilities.Date;

import static org.junit.Assert.*;

/**
 * This class tests the functionality of the {@code Profile} class, specifically the {@code compareTo()} method.
 * The tests cover cases where the method returns 1, -1, and 0 when comparing two {@code Profile} objects.
 */
public class ProfileTest {

    /**
     * Tests that {@code compareTo()} returns 1 when the first {@code Profile}'s last name
     * is lexicographically greater than the second {@code Profile}'s last name.
     */
    @Test
    public void testCompareTo_ReturnOne() {
        Profile p1 = new Profile("Bob", "Smith", new Date(1990, 1, 1));
        Profile p2 = new Profile("Bob", "Fith", new Date(1990, 1, 1));
        assertEquals(1, p1.compareTo(p2));

        p1 = new Profile("Kim", "Kardashian", new Date(1980, 10, 21));
        p2 = new Profile("Kim", "Jenner", new Date(1980, 10, 21));
        assertEquals(1, p1.compareTo(p2));

        p1 = new Profile("Will", "Smith", new Date(1968, 9, 25));
        p2 = new Profile("Will", "Adams", new Date(1968, 9, 25));
        assertEquals(1, p1.compareTo(p2));
    }

    /**
     * Tests that {@code compareTo()} returns -1 when the first {@code Profile}'s last name
     * is lexicographically less than the second {@code Profile}'s last name.
     */
    @Test
    public void testCompareTo_ReturnsMinusOne() {
        Profile p1 = new Profile("Alice", "Smith", new Date(1990, 1, 1));
        Profile p2 = new Profile("Bob", "Jones", new Date(1992, 2, 2));
        assertEquals(-1, p1.compareTo(p2));

        p1 = new Profile("Alice", "Smith", new Date(1980, 10, 21));
        p2 = new Profile("Alice", "Taylor", new Date(1980, 10, 21));
        assertEquals(-1, p1.compareTo(p2));

        p1 = new Profile("Will", "Adams", new Date(1968, 9, 25));
        p2 = new Profile("Will", "Brown", new Date(1968, 9, 25));
        assertEquals(-1, p1.compareTo(p2));
    }

    /**
     * Tests that {@code compareTo()} returns 0 when both {@code Profile} objects have the same first name,
     * last name, and date of birth.
     */
    @Test
    public void testCompareTo_ReturnsZero() {
        Profile p1 = new Profile("Bob", "Marley", new Date(1999, 1, 1));
        Profile p2 = new Profile("Bob", "Marley", new Date(1999, 1, 1));
        assertEquals(0, p1.compareTo(p2));
    }
}
