package HTTP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
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
    void setContentType_validTest() {
        response.setContentType("file.html");
        assertNotNull(response.getContentType());
        assertEquals(response.getContentType(), "Content-Type: text/html; charset=UTF-8\r\n\r\n");
    }

    @Test
    void setContentType_invalidTest() {
        response.setContentType("file.jpg");
        assertNotNull(response.getContentType());
        assertNotEquals(response.getContentType(), "Content-Type: text/html; charset=UTF-8\r\n\r\n");
    }


    @Test
    void validResponseTest() {
        ResponseHandler responseHandler = new ResponseHandler(System.getProperty("user.dir")+"\\testFiles");
        Request request = new Request();
        request.setMethod(HttpMethod.GET);
        request.setURI("/a.html");
        request.setVersion("HTTP/1.1");
        try {
            Response response = responseHandler.handleResponse(request);
            assertNotNull(response);
            assertEquals(response.getResponseLine(),"HTTP/1.1 200 OK\r\n");
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void invalidResponseTest() {
        ResponseHandler responseHandler = new ResponseHandler(System.getProperty("user.dir")+"\\testFiles");
        Request request = new Request();
        request.setMethod(HttpMethod.GET);
        request.setURI("/noSuchFile.html");
        request.setVersion("HTTP/1.1");
        try {
            Response response = responseHandler.handleResponse(request);
            assertEquals(response.getResponseLine(),"HTTP/1.1 404 File Not Found\r\n");
        } catch (IOException e) {
            fail();
        }
    }

}