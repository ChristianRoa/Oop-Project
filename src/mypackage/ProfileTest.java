package mypackage;

import org.junit.Test;
import utilities.Date;

import static org.junit.Assert.*;

public class ProfileTest {

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
    @Test
    public void testCompareTo_ReturnsZero() {
        Profile p1 = new Profile("Bob", "Marley", new Date(1999, 1, 1));
        Profile p2 = new Profile("Bob", "Marley", new Date(1999, 1, 1));
        assertEquals(0, p1.compareTo(p2));
    }
}