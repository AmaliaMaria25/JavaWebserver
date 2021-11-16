package HTTP;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.util.ArrayList;

public class Request {

    private final static Logger LOGGER  = LoggerFactory.getLogger(Request.class);
    private String URI;
    private HttpMethod method;
    private String version;
    private ArrayList<String> headers;

    public Request() { }

    public String getURI() { return URI; }

    public void setURI(String URI) { this.URI = URI; }

    public HttpMethod getMethod() { return method; }

    public void setMethod(HttpMethod method) { this.method = method; }

    public String getVersion() { return version; }

    public void setVersion(String version) { this.version = version; }

    public ArrayList<String> getHeaders() { return headers; }

    public void setHeaders(ArrayList<String> headers) { this.headers = headers; }


}
