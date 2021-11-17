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
        response.setHeaders(StatusCode.STATUS_200_OK);
        assertEquals(response.toString(),"HTTP/1.1 STATUS_200_OK\r\nConnection: keep-alive\r\n\r\n");
    }

    @Test
    void invalidTestToString() {
        response.setHeaders(StatusCode.STATUS_200_OK);
        assertNotEquals(response.toString(),"HTTP/1.1 STATUS_200_OK\r\nConnection: keep-alive");
    }

    @Test
    void setContentType() {

        ArrayList<String> headers = new ArrayList<String>();
        headers.add("HTTP/1.1 CLIENT_ERROR_404_FILE_NOT_FOUND");
        headers.add("Connection: keep-alive");
        response.setContentType("file.html", headers);
        assertEquals(response.getHeaders(), headers);
    }

    @Test
    void setHeaders() {
    }
}