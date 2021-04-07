package presentation;

import org.junit.jupiter.api.Test;
import presentation.UserLogin;

import static org.junit.jupiter.api.Assertions.*;
import static presentation.UserLogin.UserLoginFlag.*;

class UserLoginTest {


    UserLogin ul = new UserLogin();
    UserLogin.UserLoginFlag ulf;



    @Test
    void validateUserTest() {
        ulf = ul.ValidateUser("naren","naren");
        assertEquals(ulf, USER_VALID);
        ulf = ul.ValidateUser("","");
        assertEquals(ulf, USER_NULL);
        ulf = ul.ValidateUser("naren","dummyValue");
        assertEquals(ulf, USER_WRONGCREDENTIALS);

    }

    @org.junit.jupiter.api.Test
    void authenticateUserTest() {
        Boolean result = ul.AuthenticateUser("naren", "naren");
        assertEquals(true, result);
        result = ul.AuthenticateUser("naren", "dummyValue");
        assertNotEquals(true, result);

    }
}