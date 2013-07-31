package com.erudine.coursebooking;

import com.erudine.coursebooking.persons.Student;
import com.erudine.coursebooking.persons.Teacher;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class defining the schedule for a single college course. Parameters defined
 * include the maximum capacity of the course, the start and end dates, the
 * teacher taking the course, the students which are registered, and a queue of
 * students wishing to register but are unable to due to the course being full.
 *
 * @author Adam Harries
 * @version 31 Jul 2013
 */
public class CourseSchedule {

    /**
     * The course being scheduled.
     */
    private final Course course;
    /**
     * The maximum capacity of the course.
     */
    private final int courseCapacity;
    /**
     * The start date of the course.
     */
    private final Date startDate;
    /**
     * The end date of the course.
     */
    private final Date endDate;
    /**
     * The teacher taking the course.
     */
    private final Teacher teacher;
    /**
     * The set of students registered on the course.
     */
    private Set<Student> registeredStudents;
    /**
     * The queue of students wishing to register on the course.
     */
    private ArrayDeque<Student> waitListedStudents;

    /**
     * Constructor.
     *
     * @param course The course.
     * @param courseCapacity The maximum capacity of the course.
     * @param startDate The start date of the course.
     * @param endDate The end date of the course.
     * @param teacher The teacher of the course.
     */
    public CourseSchedule(Course course, int courseCapacity, Date startDate, Date endDate, Teacher teacher) {
        //Error checks
        if (courseCapacity <= 0) {
            throw new IllegalArgumentException("The maximum capacity of the course must be greater than 0.");
        }
        if (!startDate.before(endDate)) {
            throw new IllegalArgumentException("The course start date MUST be before the end date.");
        }
        this.course = course;
        this.courseCapacity = courseCapacity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacher = teacher;
        registeredStudents = new HashSet<Student>();
        waitListedStudents = new ArrayDeque<Student>();
    }

    /**
     * Checks whether the course is full.
     *
     * @return The boolean result of the check.
     */
    public boolean isCourseFull() {
        if (getNumberOfRegisteredStudents() < courseCapacity) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether a student is already registered on the course.
     *
     * @param student The student to check.
     * @return The boolean result of the check.
     */
    public boolean isStudentRegistered(Student student) {
        //Note that the overridden equals method of Student is called here.
        return registeredStudents.contains(student);
    }

    /**
     * Checks whether a student is already on the waiting list for the course.
     *
     * @param student The student to check.
     * @return The boolean result of the check.
     */
    public boolean isStudentOnWaitingList(Student student) {
        //Note that the overridden equals method of Student is called here.
        return waitListedStudents.contains(student);
    }

    /**
     * Checks whether the current time is a valid time to register.
     *
     * @return The boolean result of the check.
     */
    private boolean isValidRegistrationPeriod() {
        Date currentDate = new Date();

        if (currentDate.before(startDate)) {
            return true;
        }
        return false;
    }

    /**
     * *
     * Attempts to register a student on the course.
     *
     * @param studentWantingToJoinCourse The prospective student.
     * @return true if the student is successfully registered.
     */
    public boolean bookCourse(Student studentWantingToJoinCourse) {
        //Optional functionality: ensure that the registration attempt occurs at
        //a valid time.
//        if (!isValidRegistrationPeriod()) {
//            return false;
//        }

        //Check that the student is not already registered or waiting
        if (isStudentRegistered(studentWantingToJoinCourse)
                || isStudentOnWaitingList(studentWantingToJoinCourse)) {
            //This result is debatable - could arguably return true since the 
            //student is ALREADY registered / waiting
            return false;
        }

        //Check that the student has completed the required prerequisites
        for (Course preRequisite : course.getPreRequisites()) {
            if (!studentWantingToJoinCourse.hasTakenCourse(preRequisite)) {
                return false;
            }
        }

        //Student is eligible - attempt to register
        if (isCourseFull()) {
            //The course is full so add student to end of waiting list
            waitListedStudents.addLast(studentWantingToJoinCourse);
            return false;
        } else {
            //There is space on the course - attempt to register the student
            return registeredStudents.add(studentWantingToJoinCourse);
        }
    }

    /**
     * Removes a student from the course register or waiting list.
     *
     * @param student The student to de-register.
     */
    public void cancelBooking(Student student) {
        if (isStudentRegistered(student)) {
            //Remove the student if registered
            registeredStudents.remove(student);
            //Add the firt student in the queue if there is one
            if (isWaitingList()) {
                Student waitingStudent = waitListedStudents.poll();
                registeredStudents.add(waitingStudent);
            }
        } else if (isStudentOnWaitingList(student)) {
            //Remove the student from the waiting list if they are on it
            waitListedStudents.remove(student);
        }
    }

    /**
     * Checks whether there are students on the waiting list.
     *
     * @return The boolean result of the check.
     */
    public boolean isWaitingList() {
        if (waitListedStudents.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of students currently registered on the course.
     *
     * @return The number of registered students.
     */
    public int getNumberOfRegisteredStudents() {
        return registeredStudents.size();
    }

    /**
     * Returns the course being scheduled.
     *
     * @return {@link CourseSchedule#course course}
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Returns the maximum capacity of the course.
     *
     * @return {@link CourseSchedule#courseCapacity course capacity}
     */
    public int getCourseCapacity() {
        return courseCapacity;
    }

    /**
     * Returns the end date of the course.
     *
     * @return {@link CourseSchedule#endDate end date}
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Returns the set of students registered on the course.
     *
     * @return {@link CourseSchedule#registeredStudents registered students}
     */
    public Set<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    /**
     * Returns the start date of the course.
     *
     * @return {@link CourseSchedule#startDate start date}
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Returns the teacher of the course.
     *
     * @return {@link CourseSchedule#teacher teacher}
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Returns the waiting list of students for the course.
     *
     * @return {@link CourseSchedule#waitListedStudents wait listed students}
     */
    public ArrayDeque<Student> getWaitListedStudents() {
        return waitListedStudents;
    }
}
