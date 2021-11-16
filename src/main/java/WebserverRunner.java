
import Configuration.Config;
import Configuration.ConfigMapper;
import ServerThread.ServerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WebserverRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebserverRunner.class);

    public static void main(String[] args){
        LOGGER.info("Starting server..");
        ConfigMapper.getConfigMapper().loadConfigFile("src/main/resources/configFile.json");
        Config config = ConfigMapper.getConfigMapper().getCurrentConfig();

        LOGGER.info("The port is: "+ config.getPort());
        LOGGER.info("The path is: "+ config.getPath());
        ServerListener serverListener = null;
        try {
            serverListener = new ServerListener(config.getPort(), config.getPath());
            serverListener.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
