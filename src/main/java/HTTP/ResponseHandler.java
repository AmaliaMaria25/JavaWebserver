package HTTP;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ResponseHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(ResponseHandler.class);
    private String path;
    private final String INDEX_FILE_PATH = "/a.html";

    public ResponseHandler(String path) {
        this.path = path;
    }

    public Response handleResponse(final Request request) throws IOException {
        Response response = new Response();
        switch (request.getMethod()) {
            case HEAD: {
                response.setHeaders(StatusCode.STATUS_200_OK);
                break;
            }
            case GET: {
                File file = new File(path);
                if (file.isDirectory()) {
                    response.setHeaders(StatusCode.STATUS_200_OK);
                    response.getHeaders().add(HttpContentType.HTML.toString());
                    File indexFile = new File(path + INDEX_FILE_PATH);
                    response.setBodyContent(transformToBytes(indexFile));
                } else if (file.exists()) {
                    response.setHeaders(StatusCode.STATUS_200_OK);
                    response.setContentType(request.getURI(), request.getHeaders());
                    response.setBodyContent(transformToBytes(file));
                } else {
                    LOGGER.debug("File was not found:" + request.getURI());
                    response.setHeaders(StatusCode.CLIENT_ERROR_404_FILE_NOT_FOUND);
                    response.setBodyContent(StatusCode.CLIENT_ERROR_404_FILE_NOT_FOUND.MESSAGE);
                }
                break;
            }
            case UNkNOWN: {
                response.setHeaders(StatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                response.setBodyContent(StatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE);
                break;
            }
            default: {
                response.setHeaders(StatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
                response.setBodyContent(StatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED.MESSAGE);
            }
        }
        return response;
    }

    private byte[] transformToBytes(File file) throws IOException {
        int length = (int) file.length();
        byte[] array = new byte[length];
        InputStream in = new FileInputStream(file);
        int offset = 0;
        while (offset < length) {
            int count = in.read(array, offset, (length - offset));
            offset += count;
        }
        in.close();
        return array;
    }

}
