
import Configuration.Config;
import Configuration.ConfigMapper;
import ServerThread.ServerListener;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

import java.io.IOException;

import static java.lang.System.exit;

public class WebserverRunner {

    //private final static Logger LOGGER = LoggerFactory.getLogger(WebserverRunner.class);

    public static void main(String[] args){
        //LOGGER.info("Starting server..");
        System.out.println("The server is connected !");
        ConfigMapper.getConfigMapper().loadConfigFile("src/main/resources/configFile.json");
        Config config = ConfigMapper.getConfigMapper().getCurrentConfig();

        //LOGGER.info("The port is: "+ config.getPort());
        //LOGGER.info("The path is: "+ config.getPath());
        ServerListener serverListener = null;
        try {
            serverListener = new ServerListener(config.getPort(), System.getProperty("user.dir")+config.getPath());
            serverListener.start();
        } catch (IOException e) {
            //LOGGER.error("Could not listen on port: 1008.");
            exit(1);
        }

    }
    public static void stop(){
        exit(2);
    }
}
