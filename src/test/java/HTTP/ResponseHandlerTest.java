package HTTP;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

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
        request.setURI("/testFiles/file.html");
        request.setVersion("HTTP/1.1");
        try {
            Response response = responseHandler.handleResponse(request);
            fail();
        } catch (NoSuchFileException e) {
            assertEquals(e.getMessage(),"\\testFiles\\fileNotFound.html");
        }catch (IOException e){
            fail();
        }
    }
}