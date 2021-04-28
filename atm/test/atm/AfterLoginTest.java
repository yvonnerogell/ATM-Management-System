package atm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
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
    void inquiryValidPin() {

        AfterLogin test = new AfterLogin();

        AccountData testAccount = new AccountData("1234", "Ken", "Checking", "123456789", "500");

        test.customerlist.add(testAccount);

        Assertions.assertTrue(test.inquiry("1234"));

    }

    @Test
    void inquiryInvalidPin() {

        AfterLogin test = new AfterLogin();

        AccountData testAccount = new AccountData("1234", "Ken", "Checking", "123456789", "500");

        test.customerlist.add(testAccount);

        Assertions.assertFalse(test.inquiry("12"));

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