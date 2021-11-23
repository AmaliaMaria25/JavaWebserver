package HTTP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ResponseTest {
    Response response;

    @BeforeEach
    public void beforeClass(){
        response = new Response();
    }

    @Test
    public void validTestToString() {
        response.setResponseLine(StatusCode.STATUS_200_OK.STATUS_CODE+" "+StatusCode.STATUS_200_OK.MESSAGE);
        assertEquals(response.getResponseLine(),"HTTP/1.1 200 OK\r\n");
    }

    @Test
    void invalidTestToString() {
        response.setHeaders(StatusCode.STATUS_200_OK);
        assertNotEquals(response.toString(),"HTTP/1.1 STATUS_200_OK\r\nConnection: keep-alive");
    }

    @Test
    void setContentType() {
        response.setContentType("file.html");
        assertEquals(response.getContentType(), "Content-Type: text/html; charset=UTF-8\r\n\r\n");
    }

    @Test
    void setHeaders() {
    }
}