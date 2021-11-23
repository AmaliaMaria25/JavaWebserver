package HTTP;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RequestParserTest {
    RequestParser requestParser;
    InputStream input;

    @BeforeAll
    public void beforeClass(){
        requestParser= new RequestParser();
    }

    @Test
    public void validParseMethod_HEAD(){
        try {
            input = new ByteArrayInputStream("HEAD / HTTP/1.1\n\n".getBytes());
            Request request = requestParser.parseRequest(input);
            assertNotNull(request.getMethod());
            assertEquals(request.getMethod(),HttpMethod.HEAD);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void validParseMethod_GET(){
        try {
            input = new ByteArrayInputStream("GET / HTTP/1.1\n\n".getBytes());
            Request request = requestParser.parseRequest(input);
            assertNotNull(request.getMethod());
            assertEquals(request.getMethod(),HttpMethod.GET);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void validParseMethod_HEADwithSpaceCharacter(){
        try {
            input = new ByteArrayInputStream("Head / HTTP/1.1\n\n".getBytes());
            Request request = requestParser.parseRequest(input);
            assertNotNull(request.getMethod());
            assertEquals(request.getMethod(),HttpMethod.HEAD);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void invalidParseMethod_UNKNOWN(){
        try {
            input = new ByteArrayInputStream("Hea d / HTTP/1.1\n\n".getBytes());
            Request request = requestParser.parseRequest(input);
            assertNotNull(request.getMethod());
            assertEquals(request.getMethod(),HttpMethod.UNKNOWN);
        } catch (IOException e) {
            fail();
        }
    }


    @Test
    public void validParseVersion(){
        try {
            input = new ByteArrayInputStream("GET / HTTP/1.1\n\n".getBytes());
            Request request = requestParser.parseRequest(input);
            assertNotNull(request.getVersion());
            assertEquals(request.getVersion(),"HTTP/1.1");
        } catch (IOException e) {
            fail();
        }
    }


    @Test
    public void invalidParseVersion(){
        try {
            input = new ByteArrayInputStream("GET / HTP/1.1\n\n".getBytes());
            Request request = requestParser.parseRequest(input);
            assertNotNull(request.getVersion());
            assertNotEquals(request.getVersion(),"HTTP/1.1");
        } catch (IOException e) {
            fail();
        }
    }

}