package HTTP;


import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.util.ArrayList;

public class Response {
    private static Logger LOGGER = LoggerFactory.getLogger(Response.class);
    private static final String HTTP_VERSION = "HTTP/1.1";
    private ArrayList<String> headers = new ArrayList<String>();
    private byte[] bodyContent;


    public Response(){}

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String header : headers) {
            stringBuilder.append(header+"\r\n");
        }
        stringBuilder.append("\r\n");
        if(bodyContent != null){
            stringBuilder.append(bodyContent+"\r\n");
        }
        return stringBuilder.toString();
    }

    public void setContentType(String URI, ArrayList<String> list) {
        try {
            String contentType = URI.substring(URI.indexOf(".") + 1);
            list.add(HttpContentType.valueOf(contentType.toUpperCase()).toString());
        } catch (Exception e) {
            LOGGER.error("Content Type was not found: "+ e);
        }
    }


    public static String getHttpVersion() { return HTTP_VERSION; }

    public ArrayList<String> getHeaders() { return headers; }

    public byte[] getBodyContent() { return bodyContent; }


    public void setHeaders(StatusCode statusCode){
        headers.add(HTTP_VERSION + " " + statusCode.toString());
        headers.add("Connection: keep-alive");
        //headers.add("Server: JavaWebServer");
    }

    public void setBodyContent(String bodyResponse) {
        bodyContent = bodyResponse.getBytes();
    }

    public void setBodyContent(byte[] bodyResponse) {
        bodyContent = bodyResponse;
    }
}



