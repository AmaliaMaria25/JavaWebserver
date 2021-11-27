package HTTP;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RequestParser {

    //private final static Logger LOGGER = LoggerFactory.getLogger(RequestParser.class);

    public RequestParser() {}

    public Request parseRequest(InputStream input) throws IOException {
        Request request = new Request();
        InputStreamReader inputStreamReader = new InputStreamReader(input);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line = bufferedReader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line);
        String method=tokenizer.nextToken().toUpperCase();
        if( Arrays.stream(HttpMethod.values()).anyMatch(e -> e.name().equals(method))){
            request.setMethod(HttpMethod.valueOf(method));
        }else{
            request.setMethod(HttpMethod.UNKNOWN);
        }

        request.setURI(tokenizer.nextToken().toLowerCase().replaceAll("%20"," "));
        request.setVersion(tokenizer.nextToken());

        ArrayList<String> headers = new ArrayList<>();
        while (!(line = bufferedReader.readLine()).isEmpty()) {
            headers.add(line);
        }
        request.setHeaders(headers);

        return request;
    }

}
