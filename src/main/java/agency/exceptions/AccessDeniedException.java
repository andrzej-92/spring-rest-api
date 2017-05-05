package agency.exceptions;

public class AccessDeniedException extends Exception {

    public String detailMessage = "Access Danied.";

    public String getMessage() {
        return this.detailMessage;
    }
}
