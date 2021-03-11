package presentation;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static presentation.UserCreation.UserCreateFlag.USER_NULL;
import static presentation.UserCreation.UserCreateFlag.USER_PASSWORDMISMATCH;

class UserCreationTest {
    UserCreation uc = new UserCreation();
    UserCreation.UserCreateFlag ucf;
    @Test
    void checkUserAlreadyExistsTest() {
        Boolean result = uc.checkUserAlreadyExists("naren");
        assertEquals(true,result);
        result = uc.checkUserAlreadyExists("chandy");
        assertEquals(false,result);
    }

    void ValidateDetailsTest() throws IOException {
        ucf = uc.ValidateDetails("", "","");
        assertEquals(ucf, USER_NULL);
        ucf = uc.ValidateDetails("phil", "phil","phil1");
        assertEquals(ucf, USER_PASSWORDMISMATCH);
    }
}