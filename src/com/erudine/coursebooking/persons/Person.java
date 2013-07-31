package com.erudine.coursebooking.persons;

/**
 * Abstract superclass for all persons.
 *
 * @author Adam Harries
 * @version 31 Jul 2013
 */
public abstract class Person {

    /**
     * The name of the person.
     */
    protected final String name;

    /**
     * Constructor.
     *
     * @param name The name of the person.
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * Returns the person's name.
     *
     * @return {@link Person#name name}.
     */
    public String getName() {
        return name;
    }
}
