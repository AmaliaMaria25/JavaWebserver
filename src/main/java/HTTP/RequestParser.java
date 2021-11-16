package HTTP;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RequestParser {

    private final static Logger LOGGER  = LoggerFactory.getLogger(RequestParser.class);

    public Request parseRequest(InputStream input) throws IOException {
        Request request = new Request();

        InputStreamReader inputStreamReader = new InputStreamReader(input);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();

        String line = bufferedReader.readLine();
        String[] splitedLine = line.split("\\s+");
        try {
            request.setMethod(HttpMethod.valueOf(splitedLine[0]));
        } catch (Exception e) {
            request.setMethod(HttpMethod.UNkNOWN);
        }
        request.setURI(splitedLine[1]);
        request.setVersion(splitedLine[2]);
        stringBuilder.append(line + "\r\n");

        ArrayList<String> headers = new ArrayList<String>();
        while (!(line = bufferedReader.readLine()).isEmpty()) {
            headers.add(line);
            stringBuilder.append(line + "\r\n");
        }
        request.setHeaders(headers);
        String requestString = stringBuilder.toString();
        LOGGER.debug("/*/*" + requestString);
        return request;
    }

}
