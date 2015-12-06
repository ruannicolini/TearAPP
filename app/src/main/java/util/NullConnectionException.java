package util;

/**
 * Created by Ruan on 06/12/2015.
 */
public class NullConnectionException extends Exception {
    private String msg;

    public NullConnectionException(String msg){
        super(msg);
    }


    public NullConnectionException(String msg, String cause){
        super(msg, new Exception(cause));
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
