package HTTP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpContentTypeTest {

    @Test
    void validTestToString() {
        assertEquals(HttpContentType.HTML.toString(), "Content-Type: text/html; charset=UTF-8");
    }

    @Test
    void valueOf() {
        assertEquals(HttpContentType.HTML,HttpContentType.valueOf("HTML"));
    }
}