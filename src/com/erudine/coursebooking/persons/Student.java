package com.erudine.coursebooking.persons;

import com.erudine.coursebooking.Course;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Person subclass for a student.
 *
 * @author Adam Harries
 * @version 31 Jul 2013
 */
public class Student extends Person {

    /**
     * A set of courses which the student has already taken.
     */
    private Set<Course> coursesTaken;

    /**
     * Constructor.
     *
     * @param name The name of the student.
     */
    public Student(String name) {
        super(name);
        coursesTaken = new HashSet<Course>();
    }

    /**
     * Constructor.
     *
     * @param name The name of the student.
     * @param coursesTaken A collection of {@link Course Courses} which the
     * student has already taken.
     */
    public Student(String name, Collection<Course> coursesTaken) {
        this(name);
        addCoursesTaken(coursesTaken);
    }

    /**
     * Adds a single course which the student has taken to the underlying set.
     *
     * @param courseTaken A course which the student has already taken.
     * @return The boolean result of attempting to add the course to the set.
     */
    public final boolean addCourseTaken(Course courseTaken) {
        return coursesTaken.add(courseTaken);
    }

    /**
     * Adds a collection of courses which the student has taken to the
     * underlying set.
     *
     * @param coursesTaken A collection of courses which the student has taken.
     * @return The boolean result of attempting to add the collection to the
     * underlying set.
     */
    public final boolean addCoursesTaken(Collection<Course> coursesTaken) {
        return this.coursesTaken.addAll(coursesTaken);
    }

    /**
     * Logical check of whether the student has already taken the specified
     * {@link Course course}: Loops over the underlying set of courses taken and
     * compares for equality.
     *
     * @param course The course to check.
     * @return The boolean result of the check.
     */
    public boolean hasTakenCourse(Course course) {
        for (Course courseTaken : coursesTaken) {
            //Note that the overridden equals method is called here.
            if (courseTaken.equals(course)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the set of courses which the student has already taken.
     *
     * @return {@link Student#coursesTaken coursesTaken}.
     */
    public Set<Course> getCoursesTaken() {
        return coursesTaken;
    }

    /**
     * Overridden equality method: students are considered equal if they have
     * the same name.
     *
     * @param obj The object to check for equality.
     * @return The boolean result of the equality check.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student student = (Student) obj;
        if (this.name == null ? student.getName() != null : !this.name.equals(student.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.coursesTaken != null ? this.coursesTaken.hashCode() : 0);
        return hash;
    }
}
