/**
 * JUnit5Demo.java
 * 30 jan. 2018
 */
package test.junit5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * @author jlnmsi
 *
 */
@SuppressWarnings("all")
class JUnit5Demo {

	 
    @BeforeAll
    public static void oneTimeSetUp() {
        // one-time initialization code   
    	System.out.println("@BeforeAll - oneTimeSetUp");
    }
 
    @AfterAll
    public static void oneTimeTearDown() {
        // one-time cleanup code
    	System.out.println("@AfterAll - oneTimeTearDown");
    }
 
    @BeforeEach
    public void setUp() {
    	// Executed before each test method
        System.out.println("@BeforeEach - setUp");
    }
 
    @AfterEach
    public void tearDown() {
    	// Executed after each test method
        System.out.println("@AfterEach - tearDown");
    }
 
    @Test
    public void testMethod1() {
    	assertTrue(5==2+3);
        System.out.println("@Test - testMethod1");
    }
    
    @Test
    public void testMethod2() {
    	assertEquals("Hello","Hello");   // Uses equals() to compare
        System.out.println("@Test - testMethod2");
    }
    
    @Test
    public void testMethod3() {
    	assertNotSame(new Integer(5),new Integer(5));  // Uses != to compare
        System.out.println("@Test - testMethod3");
    }
 

}
