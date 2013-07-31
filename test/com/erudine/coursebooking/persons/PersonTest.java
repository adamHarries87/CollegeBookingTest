package com.erudine.coursebooking.persons;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the {@link Person} class.
 *
 * @author Adam Harries
 * @version 31 July 2013
 */
public class PersonTest {

    public static final String ADAM = "Adam";
    private PersonImpl testPerson;

    /**
     * Fixture setup method.
     */
    @Before
    public void setUp() {
        testPerson = new PersonImpl();
    }

    /**
     * Constructor test.
     */
    @Test
    public void testConstructor() {
        assertEquals(testPerson.name, ADAM);
    }

    /**
     * Test of getName method, of class Person.
     */
    @Test
    public void testGetName() {
        assertEquals(testPerson.getName(), ADAM);
    }

    /**
     * Mock implementation of {@link Person}.
     */
    public class PersonImpl extends Person {

        public PersonImpl() {
            super(ADAM);
        }
    }
}
