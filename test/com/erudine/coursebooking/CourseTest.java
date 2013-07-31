package com.erudine.coursebooking;

import com.erudine.coursebooking.persons.Teacher;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the Course Class.
 *
 * @author Adam Harries
 * @version 31 July 2013
 */
public class CourseTest {

    public final static String MY_COURSE = "MY_COURSE";
    public final static Course MATHS = new Course("Mathematics");
    public final static Course ENGLISH = new Course("English");
    public final static Course SCIENCE = new Course("Science");
    public final static Course HISTORY = new Course("History");
    public final static Course FRENCH = new Course("French");
    private Course testCourse;
    private Set<Course> preRequisites;

    @Before
    public void setUp() {
        preRequisites = new HashSet<Course>();
        preRequisites.add(MATHS);
        preRequisites.add(ENGLISH);
        preRequisites.add(SCIENCE);
        testCourse = new Course(MY_COURSE, preRequisites);
    }

    /**
     * Constructor test.
     */
    @Test
    public void testConstructor1() {
        assertEquals(testCourse.getName(), MY_COURSE);
    }

    /**
     * Constructor test.
     */
    @Test
    public void testConstructor2() {
        assertEquals(testCourse.getPreRequisites(), preRequisites);
    }

    /**
     * Constructor test.
     */
    @Test
    public void testConstructor3() {
        Course newCourse = new Course(MY_COURSE);
        assertEquals(newCourse.getName(), MY_COURSE);
    }

    /**
     * Test of addPreRequisite method, of class Course.
     */
    @Test
    public void testAddPreRequisite() {
        testCourse.addPreRequisite(HISTORY);
        assertTrue(testCourse.getPreRequisites().contains(HISTORY));
    }

    /**
     * Test of addPreRequisites method, of class Course.
     */
    @Test
    public void testAddPreRequisites() {
        Collection<Course> additionalPreRequisites = new HashSet<Course>();
        additionalPreRequisites.add(HISTORY);
        additionalPreRequisites.add(FRENCH);
        testCourse.addPreRequisites(additionalPreRequisites);
        assertTrue(testCourse.getPreRequisites().containsAll(additionalPreRequisites));
    }

    /**
     * Test of isPreRequisite method, of class Course.
     */
    @Test
    public void testIsPreRequisite1() {
        assertTrue(testCourse.isPreRequisite(MATHS));
    }

    /**
     * Test of isPreRequisite method, of class Course.
     */
    @Test
    public void testIsPreRequisite2() {
        assertFalse(testCourse.isPreRequisite(FRENCH));
    }

    /**
     * Test of getName method, of class Course.
     */
    @Test
    public void testGetName() {
        assertEquals(testCourse.getName(), MY_COURSE);
    }

    /**
     * Test of getPreRequisites method, of class Course.
     */
    @Test
    public void testGetPreRequisites() {
        assertEquals(testCourse.getPreRequisites(), preRequisites);
    }

    /**
     * Test of equals method, of class Course.
     */
    @Test
    public void testEquals1() {
        assertTrue(testCourse.equals(testCourse));
    }

    /**
     * Test of equals method, of class Course.
     */
    @Test
    public void testEquals2() {
        assertFalse(testCourse.equals(null));
    }

    /**
     * Test of equals method, of class Course.
     */
    @Test
    public void testEquals3() {
        Teacher testTeacher = new Teacher(MY_COURSE);
        assertFalse(testCourse.equals(testTeacher));
    }

    /**
     * Test of equals method, of class Course.
     */
    @Test
    public void testEquals4() {
        Course differentCourse = new Course("Different Course");
        assertFalse(testCourse.equals(differentCourse));
    }

    /**
     * Test of equals method, of class Course.
     */
    @Test
    public void testEquals5() {
        Course sameCourse = new Course(MY_COURSE);
        assertTrue(testCourse.equals(sameCourse));
    }
}
