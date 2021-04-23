package atm;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AfterLoginTest {

    @Test
    void loadPersonsTest_Valid() {
        // Arrange
        AfterLogin al = new AfterLogin();

        // Act
        al.loadPersons();

        // Assert
        assertTrue(al.customerlist.size() != 0);
    }

    @Test
    void loadPersonsTest_InvalidFileName() {

        // Arrange
        AfterLogin al = new AfterLogin();
        // Need to set to new Arraylist, since the constructor adds a record to the customer list
        al.customerlist = new ArrayList();
        al.defaultCustomerRecordFileName = "this filename does not exist.txt";

        // Act
        al.loadPersons();

        // Assert
        assertEquals(0, al.customerlist.size());
    }

    @Test
    void inquiry() {
    }

    @Test
    void transfer() {
    }

    @Test
    void withdraw() {
    }

    @Test
    void actionPerformed() {
    }
}