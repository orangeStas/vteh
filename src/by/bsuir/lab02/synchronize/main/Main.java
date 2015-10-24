package by.bsuir.lab02.synchronize.main;


import by.bsuir.lab02.synchronize.logic.callcenter.CallCenter;
import by.bsuir.lab02.synchronize.logic.client.Client;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //initializing CallCenter with number of operators
        CallCenter callCenter = new CallCenter(2);

        //initializing clients with name and call center
        Client client = new Client("Stas", callCenter);
        Client client2 = new Client("Maks", callCenter);
        Client client3 = new Client("Oleg", callCenter);
        Client client4 = new Client("Nikita", callCenter);
        Client client5 = new Client("Petya", callCenter);

        //run a client's threads
        client.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();

        Thread.sleep(20000);

        //stop a client's thread
        client.stopThread();
        client2.stopThread();
        client3.stopThread();
        client4.stopThread();
        client5.stopThread();

        //waiting while client's threads done work
        client.join();
        client2.join();
        client3.join();
        client4.join();
        client5.join();
    }
}
