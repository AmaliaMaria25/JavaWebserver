package HTTP;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Request {

    private final static Logger LOGGER  = LoggerFactory.getLogger(Request.class);
    private String URI;
    private HttpMethod method;
    private String version;
    private ArrayList<String> headers;

    public Request(InputStream input) {
        InputStreamReader inputStreamReader = new InputStreamReader(input);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
            parseRequest(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void parseRequest(BufferedReader input) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while (!(line = input.readLine()).isEmpty()) {
            stringBuilder.append(line + "\r\n");
        }
        String requestString = stringBuilder.toString();
        LOGGER.debug("/*/*" + requestString);
    }


    public String getURI() { return URI; }

    public void setURI(String URI) { this.URI = URI; }

    public HttpMethod getMethod() { return method; }

    public void setMethod(HttpMethod method) { this.method = method; }

    public String getVersion() { return version; }

    public void setVersion(String version) { this.version = version; }

    public ArrayList<String> getHeaders() { return headers; }

    public void setHeaders(ArrayList<String> headers) { this.headers = headers; }


}
