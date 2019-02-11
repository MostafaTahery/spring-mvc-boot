package co.nilin.mvc.data.dto;

import java.io.Serializable;

public class RegisterDto implements Serializable {

    String receptor;
    String message;
   // String sender;
    //String LocalMessageId;

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    @Override
    public String toString() {
        return  "?receptor=" + this.receptor +
                "&message=" + this.message ;

    }
}
