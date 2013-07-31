package com.erudine.coursebooking;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Class representation of a single college course which contains an course name
 * and a collection of prerequisite courses that students must have already
 * taken before enrolling.
 *
 * @author Adam Harries
 * @version 31 Jul 2013
 */
public class Course {

    /**
     * The course name.
     */
    private final String name;
    /**
     * A set of prerequisite courses that have been taken before enrolling.
     */
    private Set<Course> preRequisites;

    /**
     * Constructor.
     *
     * @param name The course name to assign.
     */
    public Course(String name) {
        this.name = name;
        this.preRequisites = new HashSet<Course>();
    }

    /**
     * Constructor.
     *
     * @param name The course name to assign.
     * @param preRequisites A collection of prerequisite courses that must have
     * already been taken before enrolling.
     */
    public Course(String name, Collection<Course> preRequisites) {
        this(name);
        addPreRequisites(preRequisites);
    }

    /**
     * Adds a single prerequisite course to the underlying set.
     *
     * @param preRequisiteCourse The prerequisite course to add to the set.
     * @return The boolean result of attempting to add the prerequisite course.
     */
    public final boolean addPreRequisite(Course preRequisiteCourse) {
        return preRequisites.add(preRequisiteCourse);
    }

    /**
     * Adds a collection of prerequisite courses to the underlying set.
     *
     * @param preRequisites The collection of prerequisite courses to add.
     * @return The boolean result of attempting to add the prerequisite courses.
     */
    public final boolean addPreRequisites(Collection<Course> preRequisites) {
        return this.preRequisites.addAll(preRequisites);
    }

    /**
     * Logical check of whether the specified course is a prerequisite. Loops
     * over all of the prerequisite courses and checks for equality.
     *
     * @param course The course to check.
     * @return The boolean result of the prerequisite check.
     */
    public boolean isPreRequisite(Course course) {
        for (Course preRequisite : preRequisites) {
            //Note that the overridden equals method is called here.
            if (course.equals(preRequisite)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the course name.
     *
     * @return {@link Course#name name}.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the set of prerequisite courses.
     *
     * @return {@link Course#preRequisites preRequisites}.
     */
    public Set<Course> getPreRequisites() {
        return preRequisites;
    }

    /**
     * Overridden equality method: Course are considered equal if they have the
     * same name.
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
        final Course course = (Course) obj;
        //Check if course names are the same.
        if (this.name == null ? course.getName() != null : !this.name.equals(course.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.preRequisites != null ? this.preRequisites.hashCode() : 0);
        return hash;
    }
}
