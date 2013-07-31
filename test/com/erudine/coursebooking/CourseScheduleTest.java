package com.erudine.coursebooking;

import com.erudine.coursebooking.persons.Student;
import com.erudine.coursebooking.persons.Teacher;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the CourseSchedule class.
 *
 * @author Adam Harries
 * @version 31 July 2013
 */
public class CourseScheduleTest {

    public static final Course MATHS = new Course("Maths");
    public static final Course FRENCH = new Course("French");
    public static final int COURSE_CAPACITY = 2;
    public static final Teacher TEACHER = new Teacher("Mr Harries");
    public static final Student STUDENT1 = new Student("Student 1");
    public static final Student STUDENT2 = new Student("Student 2");
    public static final Student STUDENT3 = new Student("Student 3");
    public static final Student STUDENT4 = new Student("Student 4");
    private Date startDate;
    private Date endDate;
    private Course course;
    private CourseSchedule testCourseSchedule;

    @Before
    public void setUp() throws ParseException {
        course = new Course("Java Data Structures");
        startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2013-01-01");
        endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("20013-12-31");
        testCourseSchedule = new CourseSchedule(course, COURSE_CAPACITY, startDate, endDate, TEACHER);
    }

    /**
     * Constructor Test.
     */
    @Test
    public void testConstructor1() {
        assertEquals(testCourseSchedule.getCourse(), course);
    }

    /**
     * Constructor Test.
     */
    @Test
    public void testConstructor2() {
        assertEquals(testCourseSchedule.getCourseCapacity(), COURSE_CAPACITY);
    }

    /**
     * Constructor Test.
     */
    @Test
    public void testConstructor3() {
        assertEquals(testCourseSchedule.getStartDate(), startDate);
    }

    /**
     * Constructor Test.
     */
    @Test
    public void testConstructor4() {
        assertEquals(testCourseSchedule.getEndDate(), endDate);
    }

    /**
     * Constructor Test.
     */
    @Test
    public void testConstructor5() {
        assertEquals(testCourseSchedule.getTeacher(), TEACHER);
    }

    /**
     * Constructor Test.
     */
    @Test
    public void testConstructor6() {
        assertTrue(testCourseSchedule.getRegisteredStudents().isEmpty());
    }

    /**
     * Constructor Test.
     */
    @Test
    public void testConstructor7() {
        assertTrue(testCourseSchedule.getWaitListedStudents().isEmpty());
    }

    /**
     * Constructor Test.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorErrorTrap1() {
        CourseSchedule error = new CourseSchedule(course, COURSE_CAPACITY, endDate, startDate, TEACHER);
    }

    /**
     * Constructor Test.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorErrorTrap2() {
        CourseSchedule error = new CourseSchedule(course, 0, startDate, endDate, TEACHER);
    }

    /**
     * Test of isCourseFull method, of class CourseSchedule.
     */
    @Test
    public void testIsCourseFull1() {
        assertFalse(testCourseSchedule.isCourseFull());
    }

    /**
     * Test of isCourseFull method, of class CourseSchedule.
     */
    @Test
    public void testIsCourseFull2() {
        testCourseSchedule.getRegisteredStudents().add(STUDENT1);
        testCourseSchedule.getRegisteredStudents().add(STUDENT2);
        assertTrue(testCourseSchedule.isCourseFull());
    }

    /**
     * Test of isStudentRegistered method, of class CourseSchedule.
     */
    @Test
    public void testIsStudentRegistered1() {
        assertFalse(testCourseSchedule.isStudentRegistered(STUDENT1));
    }

    /**
     * Test of isStudentRegistered method, of class CourseSchedule.
     */
    @Test
    public void testIsStudentRegistered2() {
        testCourseSchedule.getRegisteredStudents().add(STUDENT1);
        assertTrue(testCourseSchedule.isStudentRegistered(STUDENT1));
    }

    /**
     * Test of isStudentOnWaitingList method, of class CourseSchedule.
     */
    @Test
    public void testIsStudentOnWaitingList1() {
        assertFalse(testCourseSchedule.isStudentOnWaitingList(STUDENT1));
    }

    /**
     * Test of isStudentOnWaitingList method, of class CourseSchedule.
     */
    @Test
    public void testIsStudentOnWaitingList2() {
        testCourseSchedule.getWaitListedStudents().add(STUDENT1);
        assertTrue(testCourseSchedule.isStudentOnWaitingList(STUDENT1));
    }

    /**
     * Test successful course booking for an eligible student.
     */
    @Test
    public void testBookCourse1() {
        boolean result = testCourseSchedule.bookCourse(STUDENT1);
        assertTrue(result);
        assertTrue(testCourseSchedule.isStudentRegistered(STUDENT1));
        assertFalse(testCourseSchedule.isStudentOnWaitingList(STUDENT1));
    }

    /**
     * Test the waiting list populates when the course is full.
     */
    @Test
    public void testBookCourse2() {
        testCourseSchedule.bookCourse(STUDENT1);
        testCourseSchedule.bookCourse(STUDENT2);
        testCourseSchedule.bookCourse(STUDENT3);
        testCourseSchedule.bookCourse(STUDENT4);

        assertTrue(testCourseSchedule.isStudentRegistered(STUDENT1));
        assertTrue(testCourseSchedule.isStudentRegistered(STUDENT2));
        assertFalse(testCourseSchedule.isStudentRegistered(STUDENT3));
        assertFalse(testCourseSchedule.isStudentRegistered(STUDENT4));

        assertFalse(testCourseSchedule.isStudentOnWaitingList(STUDENT1));
        assertFalse(testCourseSchedule.isStudentOnWaitingList(STUDENT2));
        assertTrue(testCourseSchedule.isStudentOnWaitingList(STUDENT3));
        assertTrue(testCourseSchedule.isStudentOnWaitingList(STUDENT4));
    }

    /**
     * Test that attempting to register an already-registered student fails.
     */
    @Test
    public void testBookCourse3() {
        testCourseSchedule.bookCourse(STUDENT1);
        assertFalse(testCourseSchedule.bookCourse(STUDENT1));
        assertTrue(testCourseSchedule.isStudentRegistered(STUDENT1));
    }

    /**
     * Test attempting to register a wait-listed student fails.
     */
    @Test
    public void testBookCourse4() {
        testCourseSchedule.bookCourse(STUDENT1);
        testCourseSchedule.bookCourse(STUDENT2);
        testCourseSchedule.bookCourse(STUDENT3);

        assertFalse(testCourseSchedule.bookCourse(STUDENT3));

        assertFalse(testCourseSchedule.isStudentRegistered(STUDENT3));
        assertTrue(testCourseSchedule.isStudentOnWaitingList(STUDENT3));
    }

    /**
     * Test attempting to register a student who doesn't have the required
     * prerequisites fails.
     */
    @Test
    public void testBookCourse5() {
        testCourseSchedule.getCourse().addPreRequisite(MATHS);
        testCourseSchedule.getCourse().addPreRequisite(FRENCH);

        STUDENT1.addCourseTaken(MATHS);

        assertFalse(testCourseSchedule.bookCourse(STUDENT1));
    }

    /**
     * Test attempting to register a student who does have the required
     * prerequisites succeeds.
     */
    @Test
    public void testBookCourse6() {
        testCourseSchedule.getCourse().addPreRequisite(MATHS);
        testCourseSchedule.getCourse().addPreRequisite(FRENCH);

        STUDENT1.addCourseTaken(MATHS);
        STUDENT1.addCourseTaken(FRENCH);

        assertTrue(testCourseSchedule.bookCourse(STUDENT1));
    }

    /**
     * Test that cancelling a booking removes the student from the register.
     */
    @Test
    public void testCancelBooking1() {
        testCourseSchedule.bookCourse(STUDENT1);

        testCourseSchedule.cancelBooking(STUDENT1);
        assertFalse(testCourseSchedule.isStudentRegistered(STUDENT1));
    }

    /**
     * Test that cancelling a booking removes a wait-listed student.
     */
    @Test
    public void testCancelBooking2() {
        testCourseSchedule.bookCourse(STUDENT1);
        testCourseSchedule.bookCourse(STUDENT2);
        testCourseSchedule.bookCourse(STUDENT3);

        testCourseSchedule.cancelBooking(STUDENT3);

        assertFalse(testCourseSchedule.isStudentOnWaitingList(STUDENT3));
        assertFalse(testCourseSchedule.isStudentRegistered(STUDENT3));
    }

    /**
     * Test the first come first serve queue works when cancelling a booking.
     */
    @Test
    public void testCancelBooking3() {
        testCourseSchedule.bookCourse(STUDENT1);
        testCourseSchedule.bookCourse(STUDENT2);
        testCourseSchedule.bookCourse(STUDENT3);
        testCourseSchedule.bookCourse(STUDENT4);

        testCourseSchedule.cancelBooking(STUDENT1);

        assertTrue(testCourseSchedule.isStudentRegistered(STUDENT3));
        assertFalse(testCourseSchedule.isStudentOnWaitingList(STUDENT3));
    }

    /**
     * Test of isWaitingList method, of class CourseSchedule.
     */
    @Test
    public void testIsWaitingList1() {
        assertFalse(testCourseSchedule.isWaitingList());
    }

    /**
     * Test of isWaitingList method, of class CourseSchedule.
     */
    @Test
    public void testIsWaitingList2() {
        testCourseSchedule.getWaitListedStudents().add(STUDENT1);
        assertTrue(testCourseSchedule.isWaitingList());
    }

    /**
     * Test of getNumberOfRegisteredStudents method, of class CourseSchedule.
     */
    @Test
    public void testGetNumberOfRegisteredStudents1() {
        assertEquals(testCourseSchedule.getNumberOfRegisteredStudents(), 0);
    }

    /**
     * Test of getNumberOfRegisteredStudents method, of class CourseSchedule.
     */
    @Test
    public void testGetNumberOfRegisteredStudents2() {
        testCourseSchedule.getRegisteredStudents().add(STUDENT1);
        assertEquals(testCourseSchedule.getNumberOfRegisteredStudents(), 1);
    }

    /**
     * Test of getCourse method, of class CourseSchedule.
     */
    @Test
    public void testGetCourse() {
        assertEquals(testCourseSchedule.getCourse(), course);
    }

    /**
     * Test of getCourseCapacity method, of class CourseSchedule.
     */
    @Test
    public void testGetCourseCapacity() {
        assertEquals(testCourseSchedule.getCourseCapacity(), COURSE_CAPACITY);
    }

    /**
     * Test of getEndDate method, of class CourseSchedule.
     */
    @Test
    public void testGetEndDate() {
        assertEquals(testCourseSchedule.getEndDate(), endDate);
    }

    /**
     * Test of getRegisteredStudents method, of class CourseSchedule.
     */
    @Test
    public void testGetRegisteredStudents() {
        assertTrue(testCourseSchedule.getRegisteredStudents().isEmpty());
    }

    /**
     * Test of getStartDate method, of class CourseSchedule.
     */
    @Test
    public void testGetStartDate() {
        assertEquals(testCourseSchedule.getStartDate(), startDate);
    }

    /**
     * Test of getTeacher method, of class CourseSchedule.
     */
    @Test
    public void testGetTeacher() {
        assertEquals(testCourseSchedule.getTeacher(), TEACHER);
    }

    /**
     * Test of getWaitListedStudents method, of class CourseSchedule.
     */
    @Test
    public void testGetWaitListedStudents() {
        assertTrue(testCourseSchedule.getWaitListedStudents().isEmpty());
    }
}
