package by.bsuir.lab02.concurrent.logic.callcenter;


import by.bsuir.lab02.concurrent.logic.client.Client;
import by.bsuir.lab02.concurrent.logic.exception.CallCenterException;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class CallCenter {

    private final static Logger LOGGER = Logger.getLogger(CallCenter.class.getPackage().getName()); //getting root LOGGER

    private BlockingQueue<Operator> operators; //thread-safe queue, that contains free operators
    private Map<Client, Operator> busyOperators; //map with operators, that are currently talking with own clients
    private Lock lock;

    public CallCenter(int countOperators) {
        //initializing all operators of call center and adding them to queue
        operators = new ArrayBlockingQueue<Operator>(countOperators);
        for (int i = 0 ; i < countOperators ; i++) {
            operators.add(new Operator(i+1));
        }

        //initializing map for busy operators (empty)
        busyOperators = new HashMap<>();

        lock = new ReentrantLock();

        LOGGER.debug("Call center has been created");
    }

    public boolean takeFreeOperator(Client client) throws CallCenterException {
        boolean result;
        Operator operator;

        try {
            operator = operators.poll(client.getPatienceTime(), TimeUnit.SECONDS); //trying to get free operator from queue for client
            if (operator != null) {
                busyOperators.put(client, operator); //if operator was found, he putting to map with busy operators
                result = true;
            }
            else {
                result = false;
            }
        } catch (InterruptedException e) {
            //something going wrong
            LOGGER.debug("All operators are busy now");
            throw new CallCenterException("Can't take operator", e);
        }
        return result;
    }

    //when client done talking with operator
    public void freeOperator(Client client) throws CallCenterException {
        Operator operator = busyOperators.get(client); //get busy operator from map
        lock.tryLock(); //makes putting Operator to queue with free operators
                        // and removing this Operator from map with busy operators like as atomic operation
        try {
            operators.put(operator); //return current operator to queue with free operators
            busyOperators.remove(client); //remove current operator from map with busy operators
        } catch (InterruptedException e) {
            LOGGER.debug("Operator ¹" + operator.getId() + " can't become free now");
            throw new CallCenterException("Can't put operator", e);
        }
        finally {
            lock.unlock();
        }

    }

    //get operator for current client
    public Operator getOperator(Client client) {
        return busyOperators.get(client);
    }

}
