package atm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AfterLoginTest {

    @Test
    void loadPersons() {
        AfterLogin al = new AfterLogin();
        al.loadPersons();

        assertTrue(al.customerlist.size() != 0);
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