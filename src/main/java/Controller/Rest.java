package Controller;
import lombok.Data;

/**
 * _) Klasa w której podawane są odpowiedzi (zawsze)
 * _) Prawdziwa treść odpowiedzi w "result"
 */


@Data
public class Rest {
    String status;      //OK or FAILED
    String comment;     //Reason for FAILED
    Object result;      //List of Objects or similar

    public Rest() {
        status = "OK";
    }

    public Rest(String failReason) {
        status = "FAILED";
        comment = failReason;
    }
}
