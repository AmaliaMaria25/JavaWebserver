package ServerThread;

import HTTP.Request;
import HTTP.RequestParser;
import HTTP.Response;
import HTTP.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionThread extends Thread {

    private Socket socket;
    private String path;
    private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionThread.class);

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

            RequestParser requestParser = new RequestParser();
            Request request = requestParser.parseRequest(input) ;
            LOGGER.debug("/*/* request-"+request.getURI(),"---"+request.getMethod());
            ResponseHandler responseHandler = new ResponseHandler(path);
            Response response = responseHandler.handleResponse(request);
            LOGGER.debug(response.toString());

            output.write(response.toString().getBytes());

            LOGGER.info("Connection finished");
        } catch (IOException e) {

            LOGGER.error("Communication error ", e);
            e.printStackTrace();

        }finally{
            if(input!=null){ try { input.close();} catch (IOException e) {} }
            if(output!=null) { try { output.close(); } catch (IOException e) {} }
            if(socket!=null) { try { socket.close();} catch (IOException e) {} }
        }
    }
}
