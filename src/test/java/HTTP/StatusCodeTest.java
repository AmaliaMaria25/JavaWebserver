package HTTP;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusCodeTest {
    StatusCode statusCode;

    @Test
    void validMessage() {
        statusCode = StatusCode.STATUS_200_OK;
        assertEquals(statusCode.MESSAGE, "OK");
    }

    @Test
    void invalidMessage() {
        statusCode = StatusCode.STATUS_200_OK;
        assertNotEquals(statusCode.MESSAGE, "not ok");
    }
}