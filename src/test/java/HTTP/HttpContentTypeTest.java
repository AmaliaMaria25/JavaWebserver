package HTTP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpContentTypeTest {

    @Test
    void validTestToString() {
        assertEquals(HttpContentType.HTML.toString(), "Content-Type: text/html");
    }

    @Test
    void valueOf() {
        assertEquals(HttpContentType.HTML,HttpContentType.valueOf("HTML"));
    }
}