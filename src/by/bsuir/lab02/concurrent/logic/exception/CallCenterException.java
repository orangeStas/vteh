package by.bsuir.lab02.concurrent.logic.exception;


public class CallCenterException extends Exception {
    public CallCenterException(String message) {
        super(message);
    }

    public CallCenterException(String message, Exception ex) {
        super(message, ex);
    }
}
