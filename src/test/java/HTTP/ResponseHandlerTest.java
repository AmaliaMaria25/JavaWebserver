package HTTP;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ResponseHandlerTest {
    ResponseHandler responseHandler;

    @BeforeAll
    public void beforeClass(){
        responseHandler =new ResponseHandler("/testFiles");
    }

    @Test
    void handleResponse() {
        Request request = new Request();
        request.setMethod(HttpMethod.GET);
        request.setURI("/testFiles");
        request.setVersion("HTTP/1.1");
        try {
            Response response = responseHandler.handleResponse(request);
            assertNotNull(response);
            ArrayList<String> headers = new ArrayList<String>();
            headers.add("HTTP/1.1 CLIENT_ERROR_404_FILE_NOT_FOUND");
            headers.add("Connection: keep-alive");
            assertEquals(response.getHeaders(),headers);
        } catch (IOException e) {
            fail();
        }
    }
}