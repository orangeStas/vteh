package by.bsuir.lab02.synchronize.logic.callcenter;

import by.bsuir.lab02.synchronize.logic.client.Client;
import org.apache.log4j.Logger;

import java.util.Random;

public class Operator {

    private final static Logger LOGGER = Logger.getLogger(Operator.class.getPackage().getName());

    private int id; //operator's id

    public Operator(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    //cap for operator's operation
    public void helpToClient(Client client) throws InterruptedException {
        LOGGER.debug("Operator #" + id + " is talking with Client " + client.getClientName());
        Random random = new Random();
        Thread.sleep(1000 + random.nextInt(3000));
    }
}
