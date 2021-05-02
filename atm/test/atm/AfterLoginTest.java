package atm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
    void inquiryTest_InvalidPin() {

        AfterLogin test = new AfterLogin();

        AccountData testAccount = new AccountData("1234", "Ken", "Checking", "123456789", "500");

        test.customerlist.add(testAccount);

        Assertions.assertFalse(test.inquiry("12"));

    }
    
    @Test
    void withdrawTest_SufficientFunds() {

        // Arrange
        AfterLogin test = new AfterLogin();

        AccountData testAccount = new AccountData("1234", "Yvonne", "Checking", "123456789", "500");

        test.customerlist.add(testAccount);

        // Act
        boolean result = test.withdraw("400");

        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    void withdrawTest_InsufficientFunds() {

        // Arrange
        AfterLogin test = new AfterLogin();

        AccountData testAccount = new AccountData("1234", "Yvonne", "Checking", "123456789", "500");

        test.customerlist.add(testAccount);

        // Act
        boolean result = test.withdraw("600");

        // Assert
        Assertions.assertFalse(result);
    }
}