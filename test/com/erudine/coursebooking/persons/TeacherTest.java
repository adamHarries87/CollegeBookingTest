package com.erudine.coursebooking.persons;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the {@link Teacher} class.
 *
 * @author Adam Harries
 * @version 31 July 2013
 */
public class TeacherTest {

    public final static String MR_HARRIES = "Mr Harries";
    private Teacher testTeacher;

    @Before
    public void setUp() {
        testTeacher = new Teacher(MR_HARRIES);
    }

    /**
     * Constructor test.
     */
    @Test
    public void testConstructor() {
        assertEquals(testTeacher.name, MR_HARRIES);
    }

    /**
     * Test of equals method, of class Teacher.
     */
    @Test
    public void testEquals1() {
        assertTrue(testTeacher.equals(testTeacher));
    }

    /**
     * Test of equals method, of class Teacher.
     */
    @Test
    public void testEquals2() {
        assertFalse(testTeacher.equals(null));
    }

    /**
     * Test of equals method, of class Teacher.
     */
    @Test
    public void testEquals3() {
        Student testStudent = new Student(MR_HARRIES);
        assertFalse(testTeacher.equals(testStudent));
    }

    /**
     * Test of equals method, of class Teacher.
     */
    @Test
    public void testEquals4() {
        Teacher differentTeacher = new Teacher("Ms Davies");
        assertFalse(testTeacher.equals(differentTeacher));
    }

    /**
     * Test of equals method, of class Teacher.
     */
    @Test
    public void testEquals5() {
        Teacher sameTeacher = new Teacher(MR_HARRIES);
        assertTrue(testTeacher.equals(sameTeacher));
    }
}
