package HTTP;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.util.ArrayList;

public class Response {
    private static Logger LOGGER = LoggerFactory.getLogger(Response.class);
    private static final String HTTP_VERSION = "HTTP/1.1";
    private String responseLine;
    private ArrayList<String> headers = new ArrayList<String>();
    private String contentType;
    private byte[] bodyContent;


    public Response(){}

    public static String getHttpVersion() { return HTTP_VERSION; }

    public ArrayList<String> getHeaders() { return headers; }

    public void setHeaders(StatusCode statusCode){
        headers.add("Connection: keep-alive\r\n");
    }

    public byte[] getBodyContent() { return bodyContent; }

    public String getResponseLine() { return responseLine; }

    public void setResponseLine(String responseLine) { this.responseLine = HTTP_VERSION+" "+responseLine+"\r\n"; }

    public String getContentType() { return contentType; }

    public void setContentType(String URI) {
        try {
            String contentType = URI.substring(URI.indexOf(".") + 1);
            this.contentType  = HttpContentType.valueOf(contentType.toUpperCase()).toString()+"\r\n\r\n";
            this.headers.add(this.contentType);
        } catch (Exception e) {
            LOGGER.error("Content Type was not found: "+ e);
        }
    }

    public void setBodyContent(String bodyResponse) { bodyContent = bodyResponse.getBytes(); }

    public void setBodyContent(byte[] bodyResponse) { bodyContent = bodyResponse; }
}



