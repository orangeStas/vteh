package by.bsuir.lab02.synchronize.logic.callcenter;

import by.bsuir.lab02.synchronize.logic.client.Client;
import by.bsuir.lab02.synchronize.logic.exception.CallCenterException;
import org.apache.log4j.Logger;

import java.util.*;


public class CallCenter {

    private final static Logger LOGGER = Logger.getLogger(CallCenter.class.getPackage().getName()); //getting LOGGER

    private Queue<Operator> operatorsQueue;
    private final Object lock = new Object();
    private Map<Client, Operator> busyOperators; //map with operators, that are currently talking with own clients
    //private Lock lock;

    public CallCenter(int countOperators) {
        //initializing all operators of call center and adding them to queue
        operatorsQueue = new ArrayDeque<>();
        for (int i = 0; i < countOperators; i++) {
            operatorsQueue.add(new Operator(i + 1));
        }

        //initializing map for busy operators (empty)
        busyOperators = new HashMap<>();

        //lock = new ReentrantLock();

        LOGGER.debug("Call center has been created");
    }

    public boolean takeFreeOperator(Client client) throws CallCenterException {
        boolean result;
        Operator operator;

        try {
            synchronized (lock) {
                if (operatorsQueue.isEmpty()) {
                    lock.wait(client.getPatienceTime());
                }
                operator = operatorsQueue.poll();
            }

            if (operator != null) {
                busyOperators.put(client, operator);
                result = true;
            }
            else {
                result = false;
                LOGGER.debug("All operators are busy now");
            }
        } catch (InterruptedException e) {
            throw new CallCenterException("Getting free operator exception", e);
        }

        return result;
    }

    //when client done talking with operator
    public void freeOperator(Client client) throws CallCenterException {
        Operator operator = busyOperators.get(client); //get busy operator from map

        try {
            synchronized (lock) {
                operatorsQueue.add(operator);
                busyOperators.remove(client); //remove current operator from map with busy operators
                lock.notifyAll();
            }
        } catch (Exception ex) {
            LOGGER.debug("Operator ¹" + operator.getId() + " can't become free now");
            throw new CallCenterException("Adding operator to queue exception", ex);
        }
    }

    //get operator for current client
    public Operator getOperator(Client client) {
        return busyOperators.get(client);
    }

}
