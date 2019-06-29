import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String... args) throws IOException, GeneralSecurityException, MessagingException {
        Controller controller = new Controller();
        controller.start();
    }
}