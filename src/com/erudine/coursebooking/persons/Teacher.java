package com.erudine.coursebooking.persons;

/**
 * Person subclass for a teacher.
 *
 * @author Adam Harries
 * @version 31 Jul 2013
 */
public class Teacher extends Person {

    /**
     * Constructor.
     *
     * @param name The name of the teacher.
     */
    public Teacher(String name) {
        super(name);
    }

    /**
     * Overridden equality method: Teachers are considered equal if they have
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
        final Teacher teacher = (Teacher) obj;
        //Check if teachers' names are the same.
        if (this.name == null ? teacher.getName() != null : !this.name.equals(teacher.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
}
