package HTTP;

/*import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;*/

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResponseHandler {

    //private final static Logger LOGGER = LoggerFactory.getLogger(ResponseHandler.class);
    private String path;
    private final String INDEX_FILE_PATH = "\\a.html";
    private final String FILE_NOT_FOUND_PATH = "\\fileNotFound.html";

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
                File file = new File(path+request.getURI());
                if (file.isDirectory()) {
                    response.setResponseLine(StatusCode.STATUS_200_OK.STATUS_CODE+" "+StatusCode.STATUS_200_OK.MESSAGE);
                    response.setContentType(path + INDEX_FILE_PATH);
                    response.setBodyContent(Files.readAllBytes(Paths.get(path + INDEX_FILE_PATH)));
                } else if (file.exists()) {
                    response.setResponseLine(StatusCode.STATUS_200_OK.STATUS_CODE+" "+StatusCode.STATUS_200_OK.MESSAGE);
                    response.setContentType(path+request.getURI());
                    response.setBodyContent(Files.readAllBytes(Paths.get(path+request.getURI())));
                } else {
                    //LOGGER.debug("File was not found:" + path+request.getURI());
                    response.setResponseLine(StatusCode.CLIENT_ERROR_404_FILE_NOT_FOUND.STATUS_CODE+" "+StatusCode.CLIENT_ERROR_404_FILE_NOT_FOUND.MESSAGE);
                    response.setContentType(path+FILE_NOT_FOUND_PATH);
                    response.setBodyContent(Files.readAllBytes(Paths.get(path+FILE_NOT_FOUND_PATH)));
                }
                break;
            }
            case UNKNOWN: {
                response.setResponseLine(StatusCode.CLIENT_ERROR_400_BAD_REQUEST.STATUS_CODE+" "+StatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE);
                response.setContentType(path+FILE_NOT_FOUND_PATH);
                response.setBodyContent(Files.readAllBytes(Paths.get(path+FILE_NOT_FOUND_PATH)));
                break;
            }
            default: {
                response.setResponseLine(StatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED.STATUS_CODE+" "+StatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED.MESSAGE);
                response.setContentType(path+FILE_NOT_FOUND_PATH);
                response.setBodyContent(Files.readAllBytes(Paths.get(path+FILE_NOT_FOUND_PATH)));
            }
        }
        return response;
    }

}
