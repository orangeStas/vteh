package by.bsuir.lab02.concurrent.logic.client;

import by.bsuir.lab02.concurrent.logic.callcenter.CallCenter;
import by.bsuir.lab02.concurrent.logic.callcenter.Operator;
import by.bsuir.lab02.concurrent.logic.exception.CallCenterException;
import org.apache.log4j.Logger;

import java.util.Random;


public class Client extends Thread {
    private static final Logger LOGGER = Logger.getLogger(Client.class.getPackage().getName());
    private volatile boolean stopThread = false;

    private int patienceTime;

    private String clientName;
    private CallCenter callCenter;

    //initializing client
    public Client(String clientName, CallCenter callCenter) {
        this.clientName = clientName;
        this.callCenter = callCenter;
        this.patienceTime = new Random().nextInt(2);
    }

    public String getClientName() {
        return clientName;
    }

    public int getPatienceTime() {
        return patienceTime;
    }

    public void stopThread(){
        stopThread = true;
    }

    //method of starting thread
    public void run(){
        while (!stopThread) {
            try {
                callBackLater();
                callToCallCenter();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //cap for client's operation when client can't get operator
    private void callBackLater() throws InterruptedException {
        Random random = new Random();
        Thread.sleep(random.nextInt(3000));
    }


    private void callToCallCenter() throws InterruptedException {
        Operator operator = null;

        try {
            boolean isOperatorAvailable = callCenter.takeFreeOperator(this);//trying to get free operator

            if (!isOperatorAvailable) //if patience time expires
            {
                LOGGER.debug("Client " + clientName + " left.");
            }
            else
            {
                operator = callCenter.getOperator(this); //get operator, that can help to client
                LOGGER.debug("Client " + clientName + " is connected with Operator #" + operator.getId());
                operator.helpToClient(this); //operator talks with client
                callCenter.freeOperator(this); //free operator, that was getting
                LOGGER.debug("Client " + clientName + " stop talking with Operator #" + operator.getId());
            }
        } catch (CallCenterException e) {
            LOGGER.debug("CallCenter Exception", e);
        }
    }

}
