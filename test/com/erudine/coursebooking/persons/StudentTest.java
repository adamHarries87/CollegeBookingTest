package com.erudine.coursebooking.persons;

import com.erudine.coursebooking.Course;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the Student Class.
 *
 * @author Adam Harries
 * @version 31 July 2013
 */
public class StudentTest {

    public final static String ADAM_HARRIES = "Adam Harries";
    public final static Course MATHS = new Course("Mathematics");
    public final static Course ENGLISH = new Course("English");
    public final static Course SCIENCE = new Course("Science");
    public final static Course HISTORY = new Course("History");
    public final static Course FRENCH = new Course("French");
    private Student testStudent;
    private Set<Course> coursesTaken;

    @Before
    public void setUp() {
        coursesTaken = new HashSet<Course>();
        coursesTaken.add(MATHS);
        coursesTaken.add(ENGLISH);
        coursesTaken.add(SCIENCE);
        testStudent = new Student(ADAM_HARRIES, coursesTaken);
    }

    /**
     * Constructor test.
     */
    @Test
    public void testConstructor1() {
        assertEquals(testStudent.name, ADAM_HARRIES);
    }

    /**
     * Constructor test.
     */
    @Test
    public void testConstructor2() {
        assertEquals(testStudent.getCoursesTaken(), coursesTaken);
    }

    /**
     * Constructor test.
     */
    @Test
    public void testConstructor3() {
        Student newStudent = new Student(ADAM_HARRIES);
        assertEquals(newStudent.name, ADAM_HARRIES);
    }

    /**
     * Test of addCourseTaken method, of class Student.
     */
    @Test
    public void testAddCourseTaken() {
        testStudent.addCourseTaken(HISTORY);
        assertTrue(testStudent.getCoursesTaken().contains(HISTORY));
    }

    /**
     * Test of addCoursesTaken method, of class Student.
     */
    @Test
    public void testAddCoursesTaken() {
        Collection<Course> additionalCourses = new HashSet<Course>();
        additionalCourses.add(HISTORY);
        additionalCourses.add(FRENCH);
        testStudent.addCoursesTaken(additionalCourses);
        assertTrue(testStudent.getCoursesTaken().containsAll(additionalCourses));
    }

    /**
     * Test of hasTakenCourse method, of class Student.
     */
    @Test
    public void testHasTakenCourse1() {
        assertTrue(testStudent.hasTakenCourse(MATHS));
    }

    /**
     * Test of hasTakenCourse method, of class Student.
     */
    @Test
    public void testHasTakenCourse2() {
        assertFalse(testStudent.hasTakenCourse(HISTORY));
    }

    /**
     * Test of getCoursesTaken method, of class Student.
     */
    @Test
    public void testGetCoursesTaken() {
        assertEquals(testStudent.getCoursesTaken(), coursesTaken);
    }

    /**
     * Test of equals method, of class Student.
     */
    @Test
    public void testEquals1() {
        assertTrue(testStudent.equals(testStudent));
    }

    /**
     * Test of equals method, of class Student.
     */
    @Test
    public void testEquals2() {
        assertFalse(testStudent.equals(null));
    }

    /**
     * Test of equals method, of class Student.
     */
    @Test
    public void testEquals3() {
        Teacher testTeacher = new Teacher(ADAM_HARRIES);
        assertFalse(testStudent.equals(testTeacher));
    }

    /**
     * Test of equals method, of class Student.
     */
    @Test
    public void testEquals4() {
        Student differentStudent = new Student("Joe Bloggs");
        assertFalse(testStudent.equals(differentStudent));
    }

    /**
     * Test of equals method, of class Student.
     */
    @Test
    public void testEquals5() {
        Student sameStudent = new Student(ADAM_HARRIES);
        assertTrue(testStudent.equals(sameStudent));
    }
}
