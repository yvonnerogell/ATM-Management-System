package atm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void addPersons() {

    }

    @Test
    void savePerson() {

        // Arrange
        Admin test = new Admin();
        AccountData testAccount = new AccountData("1234", "Danni", "Checking", "123456789", "500");
        test.customerlist.add(testAccount);

        // Act
        test.savePerson();

        // Assert
        AfterLogin al = new AfterLogin();
        al.loadPersons();
        assertTrue(al.customerlist.contains(testAccount));
    }

    @Test
    void delete() {

        // Arrange
        Admin test = new Admin();
        AccountData testAccount = new AccountData("1234", "Danni", "Checking", "123456789", "500");
        test.customerlist.add(testAccount);

        // Act
        test.delete("1234");

        // Assert
        AfterLogin al = new AfterLogin();
        al.loadPersons();
        assertTrue(!al.customerlist.contains(testAccount));
    }

    @Test
    void edit() {

        // Arrange
        Admin test = new Admin();
        AccountData testAccount = new AccountData("1234", "Danni", "Checking", "123456789", "500");
        test.customerlist.add(testAccount);

        // Act
        test.edit("1234");

        // Assert
        AfterLogin al = new AfterLogin();
        al.loadPersons();
        for (Object atm : al.customerlist) {
            assertNotEquals(testAccount, atm);
        }
    }

    @Test
    void actionPerformed() {
    }
}