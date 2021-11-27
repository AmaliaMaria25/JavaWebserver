package ServerThread;

import HTTP.Request;
import HTTP.RequestParser;
import HTTP.Response;
import HTTP.ResponseHandler;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionThread extends Thread {

    private Socket socket;
    private String path;
    //private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionThread.class);

    public ConnectionThread(Socket socket, String path){
        this.socket = socket;
        this.path = path;
    }

    @Override
    public void run() {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = socket.getInputStream();
            output = socket.getOutputStream();

            //parse the request
            RequestParser requestParser = new RequestParser();
            Request request = requestParser.parseRequest(input) ;

            //handle the response
            ResponseHandler responseHandler = new ResponseHandler(path);
            Response response = responseHandler.handleResponse(request);

            //transform response into byte array
            byte[] outputResponse = new byte[response.getResponseLine().getBytes().length + response.getContentType().getBytes().length + response.getBodyContent().length];

            System.arraycopy(response.getResponseLine().getBytes(), 0, outputResponse, 0, response.getResponseLine().getBytes().length);
            System.arraycopy(response.getContentType().getBytes(), 0, outputResponse, response.getResponseLine().getBytes().length, response.getContentType().getBytes().length);
            System.arraycopy(response.getBodyContent(), 0, outputResponse, response.getResponseLine().getBytes().length + response.getContentType().getBytes().length, response.getBodyContent().length);

            output.write(outputResponse, 0, outputResponse.length);

            //LOGGER.info("Connection finished");
            input.close();
            output.close();
        } catch (IOException e) {
            //LOGGER.error("Communication error ", e);
            System.exit(1);

        }finally{
            if(input!=null) {
                try {
                    input.close();
                } catch (IOException e) {
                    //LOGGER.error("Could not close input");
                    System.exit(1);
                }
            }

            if(output!=null) {
                try {
                    output.flush();
                    output.close();
                } catch (IOException e) {
                    //LOGGER.error("Problem with Communication Server");
                    System.exit(1);
                }
            }

            if(socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    //LOGGER.error("Problem with Communication Server");
                    System.exit(1);
                }
            }
        }
    }
}
